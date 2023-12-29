//package com.collins-self.line-of-virgil.src.GetAllPoems;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetAllPoems {
    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36";

    public static void SendGET(String ProvidedUrl) throws IOException {
        // A URLConnection() is made, and cast as an HttpsURLConnection
        URL url = new URL(ProvidedUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET"); // NOTE: GET is also the default method
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int resCode = connection.getResponseCode();

        System.out.println("GET Response code : " + resCode);

        if (resCode == HttpsURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) { // line read is not null
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
        } else {
            System.out.println("GET request did not work.");
        }
    }
    public static void main(String[] args) throws IOException {
       for (int i = 1; i < 13; ++i) {
           String url = "https://https://www.thelatinlibrary.com/vergil/aen" + i + ".shtml";
           SendGET(url);
       }
    }
}
