package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {

    private static final int SOURCE_FILE = 0;
    private static final int NOT_SMALLER_FILE = 1;
    private static final int NOT_BIGGER_FILE = 2;
    private static String[] fileNames;

    public static void main(String[] args) {
        try (final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in))) {
            feelFileNames(bufferedReader);
            copyToTwoFiles(
                    fileNames[SOURCE_FILE],
                    fileNames[NOT_SMALLER_FILE],
                    fileNames[NOT_BIGGER_FILE]
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void feelFileNames(BufferedReader bufferedReader) throws IOException {
        fileNames = new String[3];
        fileNames[SOURCE_FILE] = bufferedReader.readLine();
        fileNames[NOT_SMALLER_FILE] = bufferedReader.readLine();
        fileNames[NOT_BIGGER_FILE] = bufferedReader.readLine();
    }

    private static void copyToTwoFiles(final String source, final String second,
            final String third) {
        try (
                final BufferedInputStream sourceStream =
                        new BufferedInputStream(new FileInputStream(source));
                final BufferedOutputStream secondStream =
                        new BufferedOutputStream(new FileOutputStream(second));
                final BufferedOutputStream thirdStream =
                        new BufferedOutputStream(new FileOutputStream(third))
        ) {
            final byte[] sourceContent = new byte[sourceStream.available()];
            if (sourceStream.read(sourceContent) != sourceContent.length) {
                throw new RuntimeException("Read insufficient data.");
            };
            final int margin = sourceContent.length / 2 + sourceContent.length % 2;
            secondStream.write(sourceContent, 0, margin);
            thirdStream.write(sourceContent, margin, sourceContent.length - margin);
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
