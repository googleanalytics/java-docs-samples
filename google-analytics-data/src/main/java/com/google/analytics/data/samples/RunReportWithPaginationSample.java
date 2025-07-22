/*
 * Copyright 2022 Google LLC
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

// [START analyticsdata_run_report_with_pagination]

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

/**
 * Google Analytics Data API sample application demonstrating the use of pagination to retrieve
 * large result sets.
 *
 * <p>See
 * https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1beta/properties/runReport#body.request_body.FIELDS.offset
 * for more information.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>{@code
 * cd google-analytics-data
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.RunReportWithPaginationSample"
 * }</pre>
 */
public class RunReportWithPaginationSample {

  public static void main(String... args) throws Exception {
    // TODO(developer): Replace with your Google Analytics 4 property ID before running the sample.
    String propertyId = "YOUR-GA4-PROPERTY-ID";
    sampleRunReportWithPagination(propertyId);
  }

  // Runs a report several times, each time retrieving a portion of result using pagination.
  static void sampleRunReportWithPagination(String propertyId) throws Exception {
    // Creates the request builder that all requests will use. Each request will set the `offset` to
    // paginate through the pages of results.
    RunReportRequest.Builder requestBuilder =
        RunReportRequest.newBuilder()
            .setProperty("properties/" + propertyId)
            .addDateRanges(
                DateRange.newBuilder().setStartDate("365daysAgo").setEndDate("yesterday"))
            .addDimensions(Dimension.newBuilder().setName("firstUserSource"))
            .addDimensions(Dimension.newBuilder().setName("firstUserMedium"))
            .addDimensions(Dimension.newBuilder().setName("firstUserCampaignName"))
            .addMetrics(Metric.newBuilder().setName("sessions"))
            .addMetrics(Metric.newBuilder().setName("keyEvents"))
            .addMetrics(Metric.newBuilder().setName("totalRevenue"))
            .setLimit(100_000);

    // Initializes client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. Wraps creation of the client in a
    // try-with-resources block so that after completing all of your requests, the JVM will
    // automatically call the "close" method on the client to safely clean up any remaining
    // background resources.
    try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
      // [START analyticsdata_run_report_with_pagination]
      int offset = 0;
      int totalRowCount;
      do {
        RunReportRequest request = requestBuilder.setOffset(offset).build();

        // Makes the request.
        RunReportResponse response = analyticsData.runReport(request);
        // Prints the response.
        RunReportSample.printRunResponseResponse(response);

        // Gets the row count for the complete result set across all pages. This will be the same
        // for all requests.
        totalRowCount = response.getRowCount();
        // Increments the offset in preparation for the next request.
        offset += response.getRowsCount();
      } while (offset < totalRowCount);
      // [END analyticsdata_run_report_with_pagination]
    }
  }
}
// [END analyticsdata_run_report_with_pagination]
