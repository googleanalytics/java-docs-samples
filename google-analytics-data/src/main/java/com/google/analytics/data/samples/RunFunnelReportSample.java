/*
 * Copyright 2023 Google LLC
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

// [START analyticsdata_run_funnel_report]
import com.google.analytics.data.v1alpha.AlphaAnalyticsDataClient;
import com.google.analytics.data.v1alpha.DateRange;
import com.google.analytics.data.v1alpha.Dimension;
import com.google.analytics.data.v1alpha.DimensionHeader;
import com.google.analytics.data.v1alpha.FunnelBreakdown;
import com.google.analytics.data.v1alpha.FunnelEventFilter;
import com.google.analytics.data.v1alpha.FunnelFieldFilter;
import com.google.analytics.data.v1alpha.FunnelFilterExpression;
import com.google.analytics.data.v1alpha.FunnelFilterExpressionList;
import com.google.analytics.data.v1alpha.FunnelStep;
import com.google.analytics.data.v1alpha.FunnelSubReport;
import com.google.analytics.data.v1alpha.MetricHeader;
import com.google.analytics.data.v1alpha.Row;
import com.google.analytics.data.v1alpha.RunFunnelReportRequest;
import com.google.analytics.data.v1alpha.RunFunnelReportResponse;
import com.google.analytics.data.v1alpha.SamplingMetadata;
import com.google.analytics.data.v1alpha.StringFilter;
import com.google.analytics.data.v1alpha.StringFilter.MatchType;

/* Google Analytics Data API sample application demonstrating the creation
of a funnel report.

See https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1alpha/properties/runFunnelReport
for more information.

Before you start the application, please review the comments starting with
"TODO(developer)" and update the code to use correct values.

To run this sample using Maven:
  cd google-analytics-data
  mvn compile
  mvn exec:java -Dexec.mainClass="com.example.analytics.RunFunnelReportSample"
 */
public class RunFunnelReportSample {

  public static void main(String... args) throws Exception {
    /**
     * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
     * running the sample.
     */
    String propertyId = "YOUR-GA4-PROPERTY-ID";
    sampleRunFunnelReport(propertyId);
  }

  /**
   * Runs a funnel query to build a report with 5 funnel steps.
   *
   * <ol>
   *   <li>First open/visit (event name is `first_open` or `first_visit`).
   *   <li>Organic visitors (`firstUserMedium` dimension contains the term "organic").
   *   <li>Session start (event name is `session_start`).
   *   <li>Screen/Page view (event name is `screen_view` or `page_view`).
   *   <li>Purchase (event name is `purchase` or `in_app_purchase`).
   * </ol>
   *
   * The report configuration reproduces the default funnel report provided in the Funnel
   * Exploration template of the Google Analytics UI. See more at
   * https://support.google.com/analytics/answer/9327974
   */
  static void sampleRunFunnelReport(String propertyId) throws Exception {

    // Using a default constructor instructs the client to use the credentials
    // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
    try (AlphaAnalyticsDataClient analyticsData = AlphaAnalyticsDataClient.create()) {
      RunFunnelReportRequest.Builder requestBuilder =
          RunFunnelReportRequest.newBuilder()
              .setProperty("properties/" + propertyId)
              .addDateRanges(DateRange.newBuilder().setStartDate("30daysAgo").setEndDate("today"))
              .setFunnelBreakdown(
                  FunnelBreakdown.newBuilder()
                      .setBreakdownDimension(Dimension.newBuilder().setName("deviceCategory")));

      // Adds each step of the funnel.
      requestBuilder
          .getFunnelBuilder()
          .addSteps(
              FunnelStep.newBuilder()
                  .setName("First open/visit")
                  .setFilterExpression(
                      FunnelFilterExpression.newBuilder()
                          .setOrGroup(
                              FunnelFilterExpressionList.newBuilder()
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("first_open")))
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("first_visit"))))));
      requestBuilder
          .getFunnelBuilder()
          .addSteps(
              FunnelStep.newBuilder()
                  .setName("Organic visitors")
                  .setFilterExpression(
                      FunnelFilterExpression.newBuilder()
                          .setFunnelFieldFilter(
                              FunnelFieldFilter.newBuilder()
                                  .setFieldName("firstUserMedium")
                                  .setStringFilter(
                                      StringFilter.newBuilder()
                                          .setMatchType(MatchType.CONTAINS)
                                          .setCaseSensitive(false)
                                          .setValue("organic")))));
      requestBuilder
          .getFunnelBuilder()
          .addSteps(
              FunnelStep.newBuilder()
                  .setName("Session start")
                  .setFilterExpression(
                      FunnelFilterExpression.newBuilder()
                          .setFunnelEventFilter(
                              FunnelEventFilter.newBuilder().setEventName("session_start"))));

      requestBuilder
          .getFunnelBuilder()
          .addSteps(
              FunnelStep.newBuilder()
                  .setName("Screen/Page view")
                  .setFilterExpression(
                      FunnelFilterExpression.newBuilder()
                          .setOrGroup(
                              FunnelFilterExpressionList.newBuilder()
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("screen_view")))
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("page_view"))))));
      requestBuilder
          .getFunnelBuilder()
          .addSteps(
              FunnelStep.newBuilder()
                  .setName("Purchase")
                  .setFilterExpression(
                      FunnelFilterExpression.newBuilder()
                          .setOrGroup(
                              FunnelFilterExpressionList.newBuilder()
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("purchase")))
                                  .addExpressions(
                                      FunnelFilterExpression.newBuilder()
                                          .setFunnelEventFilter(
                                              FunnelEventFilter.newBuilder()
                                                  .setEventName("in_app_purchase"))))));

      // Make the request.
      RunFunnelReportResponse response = analyticsData.runFunnelReport(requestBuilder.build());
      printRunFunnelReportResponse(response);
    }
  }

  // [START analyticsdata_print_run_funnel_report_response]
  /** Prints results of a runFunnelReport call. */
  static void printRunFunnelReportResponse(RunFunnelReportResponse response) {
    System.out.println("Report result:");
    System.out.println("=== FUNNEL VISUALIZATION ===");
    printFunnelSubReport(response.getFunnelVisualization());

    System.out.println("=== FUNNEL TABLE ===");
    printFunnelSubReport(response.getFunnelTable());
  }

  /** Prints the contents of a FunnelSubReport object. */
  private static void printFunnelSubReport(FunnelSubReport funnelSubReport) {
    System.out.println("Dimension headers:");
    for (DimensionHeader dimensionHeader : funnelSubReport.getDimensionHeadersList()) {
      System.out.println(dimensionHeader.getName());
    }
    System.out.println();

    System.out.println("Metric headers:");
    for (MetricHeader metricHeader : funnelSubReport.getMetricHeadersList()) {
      System.out.println(metricHeader.getName());
    }
    System.out.println();

    System.out.println("Dimension and metric values for each row in the report:");
    for (int rowIndex = 0; rowIndex < funnelSubReport.getRowsCount(); rowIndex++) {
      Row row = funnelSubReport.getRows(rowIndex);
      for (int fieldIndex = 0; fieldIndex < row.getDimensionValuesCount(); fieldIndex++) {
        System.out.printf(
            "%s: '%s'%n",
            funnelSubReport.getDimensionHeaders(fieldIndex).getName(),
            row.getDimensionValues(fieldIndex).getValue());
      }
      for (int fieldIndex = 0; fieldIndex < row.getMetricValuesCount(); fieldIndex++) {
        System.out.printf(
            "%s: '%s'%n",
            funnelSubReport.getMetricHeaders(fieldIndex).getName(),
            row.getMetricValues(fieldIndex).getValue());
      }
    }
    System.out.println();

    System.out.println("Sampling metadata for each date range:");
    for (int metadataIndex = 0;
        metadataIndex < funnelSubReport.getMetadata().getSamplingMetadatasCount();
        metadataIndex++) {
      SamplingMetadata samplingMetadata =
          funnelSubReport.getMetadata().getSamplingMetadatas(metadataIndex);
      System.out.printf(
          "Sampling metadata for date range #%d: samplesReadCount=%d, samplingSpaceSize=%d%n",
          metadataIndex,
          samplingMetadata.getSamplesReadCount(),
          samplingMetadata.getSamplingSpaceSize());
    }
  }
  // [END analyticsdata_print_run_funnel_report_response]
}
// [END analyticsdata_run_funnel_report]
