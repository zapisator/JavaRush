package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка 2
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        final PrintStream consoleStream = System.out;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);

        substituteSystemOutputWith(printStream);
        printSomething();
        substituteSystemOutputWith(consoleStream);
        printToSystemOut(byteArrayOutputStream);
    }

    private static void printToSystemOut(ByteArrayOutputStream byteArrayOutputStream) {
        System.out.println(replacedByteArrayOutputStream(byteArrayOutputStream.toString()));
    }

    private static String replacedByteArrayOutputStream(String toString) {
        return toString.replaceAll("te", "??");
    }

    private static void printSomething() {
        testString.printSomething();
    }

    private static void substituteSystemOutputWith(PrintStream printStream) {
        System.setOut(printStream);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
