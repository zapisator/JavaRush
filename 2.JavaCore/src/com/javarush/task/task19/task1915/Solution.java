package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        final String fileName = fileName();

        final PrintStream console = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final PrintStream mock = new PrintStream(buffer);

        System.setOut(mock);
        testString.printSomething();
        System.setOut(console);

        writeToFile(fileName, buffer);
        System.out.println(buffer);
    }

    private static void writeToFile(String fileName, ByteArrayOutputStream buffer) {
        try (final FileOutputStream writer = new FileOutputStream(fileName)) {
            writer.write(buffer.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

