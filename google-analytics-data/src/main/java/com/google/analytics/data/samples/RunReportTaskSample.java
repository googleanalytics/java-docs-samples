/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.analytics.data.samples;

import com.google.analytics.data.v1alpha.AlphaAnalyticsDataClient;
import com.google.analytics.data.v1alpha.CreateReportTaskRequest;
import com.google.analytics.data.v1alpha.DateRange;
import com.google.analytics.data.v1alpha.Dimension;
import com.google.analytics.data.v1alpha.DimensionHeader;
import com.google.analytics.data.v1alpha.Metric;
import com.google.analytics.data.v1alpha.MetricHeader;
import com.google.analytics.data.v1alpha.QueryReportTaskRequest;
import com.google.analytics.data.v1alpha.QueryReportTaskResponse;
import com.google.analytics.data.v1alpha.ReportTask;
import com.google.analytics.data.v1alpha.ReportTask.ReportDefinition;
import com.google.analytics.data.v1alpha.ReportTask.ReportMetadata;
import com.google.analytics.data.v1alpha.ReportTaskMetadata;
import com.google.analytics.data.v1alpha.Row;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.protobuf.Any;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Google Analytics Data API sample application demonstrating the creation of a basic report.
 *
 * <p>See
 * https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1beta/properties/runReport
 * for more information.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>{@code
 * cd google-analytics-data
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.RunReportSample"
 * }</pre>
 */
public class RunReportTaskSample {

  public static void main(String... args) throws Exception {
    /**
     * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
     * running the sample.
     */
    Deque<String> argsDeque = Arrays.stream(args).collect(Collectors.toCollection(ArrayDeque::new));
    String propertyId = MoreObjects.firstNonNull(argsDeque.pollFirst(), "YOUR-GA4-PROPERTY-ID");
    int taskRowLimit = Integer.parseInt(MoreObjects.firstNonNull(argsDeque.pollFirst(), "10000"));
    int queryPageSize = Integer.parseInt(MoreObjects.firstNonNull(argsDeque.pollFirst(), "5000"));
    sampleRunReport(propertyId, taskRowLimit, queryPageSize);
  }

  // Runs a report of active users grouped by country.
  static void sampleRunReport(String propertyId, int taskRowsLimit, int queryPageSize)
      throws Exception {
    System.out.printf("ARGS: taskRowsLimit=%,d queryPageSize=%,d%n", taskRowsLimit, queryPageSize);

    // Using a default constructor instructs the client to use the credentials
    // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
    try (AlphaAnalyticsDataClient analyticsData = AlphaAnalyticsDataClient.create()) {
      List<Dimension> dimensions =
          Arrays.asList("date", "hour", "minute", "source", "medium", "campaignId", "eventName")
              .stream()
              .map(n -> Dimension.newBuilder().setName(n).build())
              .collect(Collectors.toList());
      final ReportDefinition reportDefinition =
          ReportDefinition.newBuilder()
              .addAllDimensions(dimensions)
              .addMetrics(Metric.newBuilder().setName("activeUsers"))
              .addDateRanges(DateRange.newBuilder().setStartDate("90daysAgo").setEndDate("today"))
              // Uses the specified limit for number of rows per task.
              .setLimit(taskRowsLimit)
              .build();
      System.out.printf("Report definition:%n%s%n", reportDefinition);
      CreateReportTaskRequest createReportTaskRequest =
          CreateReportTaskRequest.newBuilder()
              .setParent("properties/" + propertyId)
              .setReportTask(
                  ReportTask.newBuilder()
                      .setName("Minute breakdown")
                      .setReportDefinition(reportDefinition))
              .build();

      int taskOffset = 0;
      int totalRowCount;
      do {
        System.out.printf("BEGIN: ReportTask for task_offset=%,d%n", taskOffset);
        // Updates the task offset.
        createReportTaskRequest =
            createReportTaskRequest.toBuilder()
                .setReportTask(
                    createReportTaskRequest.getReportTask().toBuilder()
                        .setReportDefinition(reportDefinition.toBuilder().setOffset(taskOffset)))
                .build();
        // Make the request.
        OperationFuture<ReportTask, ReportTaskMetadata> createReportTaskResponse = analyticsData.createReportTaskAsync(
            createReportTaskRequest);
        RetryingFuture<OperationSnapshot> pollingFuture =
            createReportTaskResponse.getPollingFuture();
        // Waits for the createReportTask async call to complete.
        // If the call fails, this will throw an exception.
        OperationSnapshot operationSnapshot = pollingFuture.get(5, TimeUnit.MINUTES);
        Preconditions.checkState(
            operationSnapshot.isDone(), "ReportTask not created within timeout");
        ReportTask reportTask = ((Any) operationSnapshot.getResponse()).unpack(ReportTask.class);
        System.out.printf("ReportTask: %s%n", reportTask.getName());
        totalRowCount = reportTask.getReportMetadata().getTotalRowCount();
        // System.out.printf(
        //     "Reached %s on attempt %d%n",
        //     reportTask.getReportMetadata().getState(),
        //     pollingFuture.getAttemptSettings().getOverallAttemptCount());
        printReportTask(reportTask);

        int taskRowCount = reportTask.getReportMetadata().getTaskRowCount();
        int queryOffset = 0;
        int limit = queryPageSize;
        while (queryOffset < taskRowCount) {
          QueryReportTaskRequest queryReportTaskRequest =
              QueryReportTaskRequest.newBuilder()
                  .setName(reportTask.getName())
                  .setOffset(queryOffset)
                  .setLimit(limit)
                  .build();
          QueryReportTaskResponse queryReportTaskResponse =
              analyticsData.queryReportTask(queryReportTaskRequest);
          printQueryReportTaskResponse(queryReportTaskResponse, queryOffset, limit, reportTask);
          // IMPORTANT: Uses the count of response.rows, which is the number of rows in *this* query
          // response. It does *not* use response.row_count, which is the number of total rows for
          // the ReportTask (which is dictated by the *task's* `limit`).
          queryOffset += queryReportTaskResponse.getRowsCount();
          break;
        }
        System.out.printf("END  : ReportTask for task_offset=%,d%n", taskOffset);
        taskOffset += reportTask.getReportMetadata().getTaskRowCount();
      } while (taskOffset < totalRowCount);

      System.out.printf("Total rows received: %,d%n", taskOffset);
    }
  }

  private static void printReportTask(ReportTask reportTask) {
    ReportMetadata metadata = reportTask.getReportMetadata();
    System.out.printf(
        "offset=%,d limit=%,d%n",
        reportTask.getReportDefinition().getOffset(), reportTask.getReportDefinition().getLimit());
    System.out.printf("task_row_count=%,d%n", metadata.getTaskRowCount());
    System.out.printf("total_row_count=%,d%n", metadata.getTotalRowCount());
  }

  // Prints results of a runReport call.
  static void printQueryReportTaskResponse(
      QueryReportTaskResponse response, int offset, int limit, ReportTask reportTask) {
    // [START analyticsdata_print_run_report_response_header]
    System.out.printf("BEGIN: Response for offset=%,d limit=%,d%n", offset, limit);
    System.out.printf("rows[] size=%,d%n", response.getRowsCount());
    System.out.printf(
        "row_count=%,d (aka, TOTAL rows for the ReportTask report_metadata.task_row_count=%,d)%n",
        response.getRowCount(), reportTask.getReportMetadata().getTaskRowCount());
    System.out.printf("%,d rows received%n", response.getRowsList().size());

    int i;
    System.out.printf("Dimension headers count: %d%n", response.getDimensionHeadersCount());
    i = 0;
    for (DimensionHeader header : response.getDimensionHeadersList()) {
      System.out.printf("- Dimension header[%d] name: %s%n", i++, header.getName());
    }

    i = 0;
    System.out.printf("Metric headers count: %d%n", response.getMetricHeadersCount());
    for (MetricHeader header : response.getMetricHeadersList()) {
      System.out.printf("- Metric header[%d] name: %s (%s)%n", i++, header.getName(), header.getType());
    }
    // [END analyticsdata_print_run_report_response_header]

    // [START analyticsdata_print_run_report_response_rows]
    System.out.println();
    System.out.println("Report rows:");
    i = 0;
    for (Row row : response.getRowsList()) {
      System.out.printf(
          "- Row[%d]: %s, %s%n",
          i++,
          row.getDimensionValuesList().stream()
              .map(dv -> dv.getValue())
              .collect(Collectors.joining(", ")),
          row.getMetricValues(0).getValue());
      if (i > 5) {
        break;
      }
    }
    System.out.printf("END  : Response for offset=%,d limit=%,d%n", offset, limit);
    // [END analyticsdata_print_run_report_response_rows]
  }
}
// [END analyticsdata_run_report]
