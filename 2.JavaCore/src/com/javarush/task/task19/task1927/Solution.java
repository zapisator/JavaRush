package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
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

        final String[] linesFromTestString = buffer.toString().split("\n");
        for (int i = 0; i < linesFromTestString.length; i++) {
            System.out.println(linesFromTestString[i]);
            if (i % 2 == 1) {
                System.out.println("JavaRush - курсы Java онлайн");
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
