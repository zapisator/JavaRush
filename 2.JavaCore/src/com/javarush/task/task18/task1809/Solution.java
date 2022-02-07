package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        copyReverse(fileNames());
    }

    private static void copyReverse(String[] fileNames) {
        try (
                final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileNames[0]));
                final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileNames[1]))
        ) {
            final byte[] reversedFileContent = reversedFileContent(readFileContent(inputStream));
            outputStream.write(reversedFileContent, 0, reversedFileContent.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] reversedFileContent(byte[] readFileContent) {
        final byte[] reversion = new byte[readFileContent.length];

        for (int i = 0; i < readFileContent.length; i++) {
            reversion[i] = readFileContent[reversion.length - i - 1];
        }
        System.out.println(Arrays.toString(readFileContent));
        System.out.println();
        System.out.println(Arrays.toString(reversion));
        return reversion;
    }

    private static byte[] readFileContent(final BufferedInputStream stream) throws IOException {
        byte[] readFileContent = new byte[stream.available()];
        final int byteRead = stream.read(readFileContent, 0, readFileContent.length);
        if (byteRead != readFileContent.length) {
            throw new RuntimeException("byteRead != readFileContent.length");
        }
        return readFileContent;
    }

    private static String[] fileNames() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] fileNames = new String[2];

            fileNames[0] = reader.readLine();
            fileNames[1] = reader.readLine();
            return fileNames;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
