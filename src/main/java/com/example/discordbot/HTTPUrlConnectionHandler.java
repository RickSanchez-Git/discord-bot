package com.example.discordbot;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPUrlConnectionHandler {
    private enum Errors {
        MISSING_URL("No url found"),
        HTTP("Http error: ");

        private String errorName;

        Errors(String errorName) {
            this.errorName = errorName;
        }

        public String getErrorName() {
            return errorName;
        }
        public String getErrorName(int errorId) {
            return errorName + errorId;
        }
    }

    public static String sendGet(@NotNull URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Object urlResponse = new JSONObject(response.toString())
                    .opt("url");
            if (urlResponse != null) {
                return (String) urlResponse;
            } else {
                return Errors.MISSING_URL.getErrorName();
            }
        }
        connection.disconnect();
        return Errors.HTTP.getErrorName(responseCode);
    }
}
