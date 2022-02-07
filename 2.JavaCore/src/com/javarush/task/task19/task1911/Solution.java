package com.javarush.task.task19.task1911;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* 
Ридер обертка
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        final PrintStream console = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final PrintStream mock = new PrintStream(buffer);

        System.setOut(mock);
        testString.printSomething();
        System.setOut(console);

        System.out.println(buffer.toString().toUpperCase(Locale.ENGLISH));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
