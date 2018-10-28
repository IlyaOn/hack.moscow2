package com.example.user.healthtech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * Created by User on 27.10.2018.
 */

public class Json {
    //  global variables
    static String gettingJSON;
    static String URL = "http://64a90e76.ngrok.io/";
    static CountDownLatch latch;

    static void getJSON(final String _URL,final String reg, final String strJSON) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                URL url;
                try {
                    url = new URL(_URL + reg + strJSON);
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("invalid url");
                }
                synchronized (this) {
                    StringBuffer response = new StringBuffer();

                    HttpURLConnection conn = null;
                    try {
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoOutput(false);
                        conn.setDoInput(true);
                        conn.setUseCaches(false);
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

                        // handle the response
                        int status = conn.getResponseCode();
                        if (status != 200) {
                            throw new IOException("Post failed with error code " + status);
                        } else {
                            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            conn.disconnect();
                        }

                        //Here is your json in string format
                        String responseJSON = response.toString();
                        gettingJSON = responseJSON;
                        latch.countDown();

                    }
                }
            }
        });
        thread.start();
    }

    static void communication_server(final String reg, final String strJSON){
        try {
            latch = new CountDownLatch(1);
            getJSON(URL, reg, strJSON);
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
