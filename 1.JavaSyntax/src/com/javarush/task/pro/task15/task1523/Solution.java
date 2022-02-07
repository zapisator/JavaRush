package com.javarush.task.pro.task15.task1523;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* 
Получение информации по API
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://httpbin.org/post");
        final URLConnection connection = url.openConnection();

        connection.setDoOutput(true);
        sendHello(connection);
        readResponse(connection);
    }

    private static void readResponse(URLConnection connection) {
        try (
                final InputStream inputStream = connection.getInputStream();
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                final BufferedReader reader = new BufferedReader(inputStreamReader)
        ) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            System.out.println("Can not read: " + e);
        }
    }

    private static void sendHello(URLConnection connection) {
        try (
                final OutputStream outputStream = connection.getOutputStream();
                final PrintStream sender = new PrintStream(outputStream)
        ) {
            sender.println("Привет.");
        } catch (Exception e) {
            System.out.println("Can not write: " + e);
        }
    }
}

