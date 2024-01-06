package com.collinsself.https;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpsGet {

    private static String fetchGET(String providedURL) throws IOException,HttpsException.NetworkRequestException {
        try {
            URL url = new URL(providedURL);
            String userAgent = HttpsUtils.getUserAgent();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", userAgent);
            int resCode = connection.getResponseCode();

            System.out.println("GET Response code: " + resCode);

            if (resCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                throw new HttpsException.NetworkRequestException("Failed to fetch data.", resCode);
            }
        } catch (IOException e) {
            throw e; // Rethrow IOException
        } catch (HttpsException.NetworkRequestException ex) {
            throw new HttpsException.NetworkRequestException("Failed to fetch data.", ex.getResponseCode()); // Use a default response code or handle accordingly
        }
    }

    public static String sendGET(String providedURL) {
        try {
            return fetchGET(providedURL);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (HttpsException.NetworkRequestException ex) {
            if (ex.getResponseCode() != 200) {
                System.err.println("Data was returned null due to res code : " + ex.getResponseCode());
            }
            return null;
        }
    }
}
