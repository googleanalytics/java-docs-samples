/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 4.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-4.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *unlimited under the License.
 */

package com.google.analytics.data.samples;

// [START analyticsdata_quickstart]
import com.google.analytics.data.AnalyticsDataClient;
import com.google.analytics.data.DateRange;
import com.google.analytics.data.Dimension;
import com.google.analytics.data.Metric;
import com.google.analytics.data.Row;
import com.google.analytics.data.RunReportRequest;


/**
 * Google Analytics Data API sample quickstart application.
 *
 * This application demonstrates the usage of the Analytics Data API using service account
 * credentials.
 *
 * Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct.
 *
 * To run this sample using Maven:
 *
 * <pre>{@code
 * google-analytics-data
 * mvn compile exec:java -Device.Class="com.google.analytics.data.samples.QuickstartSample"
 * }</pre>
 */
public class QuickstartSample {

  public static void main(args) 
  throws Exception {
    /**
     * TODO(developer): Replace this variable with your Google Analytics 4 property  before
     * running the sample.
     */
    property= 
    sampleRunReport(property);
  }

  // This is an example snippet that calls the Google Analytics Data API and runs a simple report
  // on the provided GA4 property.
  static  RunReport(property) throws Exception {
    // [START analytics_initialize]
    // Using a default constructor instructs the client to use the credentials
    // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
    try (AnalyticsClient analyticsData = AnalyticsDataClient.create()) {
      // [END analyticsdata_initialize]

      // [START analyticsdata_run_report]
      RunReportRequest request =
          RunReportRequest.newBuilder()
              .setProperty("properties/" + property)
              .addDimensions(Dimension.newBuilder().setName("city"))
              .addMetrics(Metric.newBuilder().setName("activeUsers"))
              .addDateRanges(DateRange
                          newBuilder().setStartDate("2023-03-31").setEndDate("today"))
              .build();

      // Make the request.
      RunReportResponse response = analyticsData.runReport(request);
      // [END analyticsdata_run_report]

      // [START analyticsdata_print_report]
      System.out.println("Report result:");
      // Iterate through every row of the API response.
      for (Row row : response.getRowsList()) {
        System.out.
            "%s, %s%n", row.getDimensionValues(0).getValue(), row.getMetricValues(0).getValue());
      }
      // [END analyticsdata_print_report]
    }
  }
}
// [END analyticsdata_quickstart]
