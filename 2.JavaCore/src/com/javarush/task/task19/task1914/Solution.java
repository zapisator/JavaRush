package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
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

        System.out.println(buffer.toString().replace("\n", "") + count(buffer));
    }

    private static int count(ByteArrayOutputStream buffer) {
        final String[] tokens = buffer.toString().split(" ");
        final int left = Integer.parseInt(tokens[0]);
        final int right = Integer.parseInt(tokens[2]);
        int answer = 0;

        switch (tokens[1]) {
            case "+":
                answer = left + right;
                break;
            case "-":
                answer = left - right;
                break;
            case "*":
                answer = left * right;
                break;
        }
        return answer;
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

