package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        final List<String> sites = Arrays.asList("http://javarush.ru/social.html",
                "https://stackoverflow.com/questions/32086560/get-request-with-java-sockets");
        getSite(new URL(sites.get(1)));
    }

    public static void getSite(URL url) {
        try (
                final Socket socket = new Socket(url.getHost(), url.getDefaultPort());
                final BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
        ) {
            final PrintWriter wtr = new PrintWriter(socket.getOutputStream());
            final String getRequest = String.format(
                    "GET %s HTTP/1.1"
                            + "%nHost: %s"
                            + "%n%n%n",
                    url.getPath(),
                    url.getHost());
            System.out.println(getRequest);
            wtr.printf(getRequest);
            wtr.flush();

            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}