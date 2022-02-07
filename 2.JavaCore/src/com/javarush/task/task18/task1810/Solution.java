package com.javarush.task.task18.task1810;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (isBigger1000byte(reader.readLine())) {
            }
            throw new DownloadException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isBigger1000byte(String fileName) {
        try (final BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(fileName))) {
            return bufferedInputStream.available() >= 1000;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class DownloadException extends Exception {

    }
}
