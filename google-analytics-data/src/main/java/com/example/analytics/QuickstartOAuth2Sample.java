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

package com.example.analytics;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.BetaAnalyticsDataSettings;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.util.Key;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ClientId;
import com.google.auth.oauth2.UserAuthorizer;
import com.google.auth.oauth2.UserCredentials;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Google Analytics Data API sample quickstart application.
 *
 * <p>This application demonstrates the usage of the Analytics Data API using service account
 * credentials.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>
 * cd google-analytics-data
 * mvn compile
 * mvn exec:java -Dexec.mainClass="com.example.analytics.QuickstartOAuth2Sample"
 * </pre>
 */
// [START analyticsdata_quickstart_oauth2]
public class QuickstartOAuth2Sample {
  // Scopes for the generated OAuth2 credentials. The list here only contains the Google Ads API
  // scope, but you can add multiple scopes if you want to use the credentials for other Google
  // APIs.
  private static final ImmutableList<String> SCOPES =
      ImmutableList.<String>builder()
          .add("https://www.googleapis.com/auth/analytics.readonly")
          .build();
  private static final String OAUTH2_CALLBACK_BASE_URI = "http://127.0.0.1";

  public static void main(String... args) throws Exception {
    /**
     * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
     * running the sample.
     */
    String propertyId = "YOUR-GA4-PROPERTY-ID";
    sampleGetCredentialsAndRunReport(propertyId);
  }

  // This is an example snippet that calls the Google Analytics Data API and runs
  // a simple report
  // on the provided GA4 property id.
  static void sampleGetCredentialsAndRunReport(String propertyId) throws Exception {
    // Extracts the OAuth client information from the provided file.
    ClientId parsedClient = ClientId.fromStream(new FileInputStream("./oauth2.keys.json"));
    // Creates an anti-forgery state token as described here:
    // https://developers.google.com/identity/protocols/OpenIDConnect#createxsrftoken
    String state = new BigInteger(130, new SecureRandom()).toString(32);

    // Creates an HTTP server that will listen for the OAuth2 callback request.
    URI baseUri;
    UserAuthorizer userAuthorizer;
    AuthorizationResponse authorizationResponse = null;
    try (SimpleCallbackServer simpleCallbackServer = new SimpleCallbackServer()) {
      userAuthorizer =
          UserAuthorizer.newBuilder()
              .setClientId(parsedClient)
              .setScopes(SCOPES)
              // Provides an empty callback URI so that no additional suffix is added to the
              // redirect. By default, UserAuthorizer will use "/oauth2callback" if this is
              // either not set or set to null.
              .setCallbackUri(URI.create(""))
              .build();
      baseUri = URI.create(OAUTH2_CALLBACK_BASE_URI + ":" + simpleCallbackServer.getLocalPort());
      System.out.printf(
          "Paste this url in your browser:%n%s%n",
          userAuthorizer.getAuthorizationUrl(null, state, baseUri));

      // Waits for the authorization code.
      simpleCallbackServer.accept();
      authorizationResponse = simpleCallbackServer.authorizationResponse;
    }

    if (authorizationResponse == null || authorizationResponse.code == null) {
      throw new NullPointerException(
          "OAuth2 callback did not contain an authorization code: " + authorizationResponse);
    }

    // Confirms that the state in the response matches the state token used to
    // generate the
    // authorization URL.
    if (!state.equals(authorizationResponse.state)) {
      throw new IllegalStateException("State does not match expected state");
    }

    // Exchanges the authorization code for credentials and print the refresh token.
    UserCredentials userCredentials =
        userAuthorizer.getCredentialsFromCode(authorizationResponse.code, baseUri);
    System.out.printf("Successfully obtained user credentials for scope(s): %s%n", SCOPES);

    // Constructs a BetaAnalyticsDataClient using the UserCredentials obtained.
    try (BetaAnalyticsDataClient analyticsData =
        BetaAnalyticsDataClient.create(
            BetaAnalyticsDataSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(userCredentials))
                .build())) {
      RunReportRequest request =
          RunReportRequest.newBuilder()
              .setProperty("properties/" + propertyId)
              .addDimensions(Dimension.newBuilder().setName("city"))
              .addMetrics(Metric.newBuilder().setName("activeUsers"))
              .addDateRanges(DateRange.newBuilder().setStartDate("2020-03-31").setEndDate("today"))
              .build();

      // Make the request.
      RunReportResponse response = analyticsData.runReport(request);

      System.out.println("Report result:");
      // Iterate through every row of the API response.
      for (Row row : response.getRowsList()) {
        System.out.printf(
            "%s, %s%n", row.getDimensionValues(0).getValue(), row.getMetricValues(0).getValue());
      }
    }
  }

  /** Basic server that listens for the OAuth2 callback. */
  private static class SimpleCallbackServer extends ServerSocket {

    private AuthorizationResponse authorizationResponse;

    SimpleCallbackServer() throws IOException {
      // Passes a port # of zero so that a port will be automatically allocated.
      super(0);
    }

    /**
     * Blocks until a connection is made to this server. After this method completes, the
     * authorizationResponse of this server will be set, provided the request line is in the
     * expected format.
     */
    @Override
    public Socket accept() throws IOException {
      Socket socket = super.accept();

      try (BufferedReader in =
          new BufferedReader(
              new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
        String callbackRequest = in.readLine();
        // Uses a regular expression to extract the request line from the first line of
        // the
        // callback request, e.g.:
        // GET /?code=AUTH_CODE&state=XYZ&scope=https://www.googleapis.com/auth/adwords
        // HTTP/1.1
        Pattern pattern = Pattern.compile("GET +([^ ]+)");
        Matcher matcher = pattern.matcher(Strings.nullToEmpty(callbackRequest));
        if (matcher.find()) {
          String relativeUrl = matcher.group(1);
          authorizationResponse = new AuthorizationResponse(OAUTH2_CALLBACK_BASE_URI + relativeUrl);
        }
        try (Writer outputWriter = new OutputStreamWriter(socket.getOutputStream())) {
          outputWriter.append("HTTP/1.1 ");
          outputWriter.append(Integer.toString(HttpStatusCodes.STATUS_CODE_OK));
          outputWriter.append(" OK\n");
          outputWriter.append("Content-Type: text/html\n\n");

          outputWriter.append("<b>");
          if (authorizationResponse.code != null) {
            outputWriter.append("Authorization code was successfully retrieved.");
          } else {
            outputWriter.append("Failed to retrieve authorization code.");
          }
          outputWriter.append("</b>");
          outputWriter.append("<p>Please check the console output from <code>");
          outputWriter.append(QuickstartOAuth2Sample.class.getSimpleName());
          outputWriter.append("</code> for further instructions.");
        }
      }
      return socket;
    }
  }

  /** Response object with attributes corresponding to OAuth2 callback parameters. */
  static class AuthorizationResponse extends GenericUrl {

    /** The authorization code to exchange for an access token and (optionally) a refresh token. */
    @Key String code;

    /** Error from the request or from the processing of the request. */
    @Key String error;

    /** State parameter from the callback request. */
    @Key String state;

    /**
     * Constructs a new instance based on an absolute URL. All fields annotated with the {@link Key}
     * annotation will be set if they are present in the URL.
     *
     * @param encodedUrl absolute URL with query parameters.
     */
    public AuthorizationResponse(String encodedUrl) {
      super(encodedUrl);
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(getClass())
          .add("code", code)
          .add("error", error)
          .add("state", state)
          .toString();
    }
  }
}
// [END analyticsdata_quickstart_oauth2]
