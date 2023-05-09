package com.khoubyari.example.test;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class HotelAPITest {
    @Test
    public void createHotel() throws Exception {
        try {
            URL url = new URL("http://localhost:8090/example/v1/hotels");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String requestBody = "{\"name\":\"Beds R Us\",\"description\":\"Very basic, small rooms but clean\",\"city\":\"Santa Ana\",\"rating\":3}";
            
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(requestBody);
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response Body : " + response.toString());
            if (responseCode==201)
                Assert.assertTrue(true);
                else Assert.assertTrue(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}