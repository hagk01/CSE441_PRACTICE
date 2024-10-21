package com.example.ex_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser {

    // Phương thức lấy XML từ URL
    public String getXmlFromUrl(String urlString) {
        StringBuilder xml = new StringBuilder();
        try {
            // Tạo URL và kết nối
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

             int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    xml.append(line);
                }
                reader.close();
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
