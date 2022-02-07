package com.javarush.task.task19.task1913;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

/* 
Выводим только цифры
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        final PrintStream consoleStream = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final PrintStream bufferSteam = new PrintStream(buffer);

        System.setOut(bufferSteam);
        testString.printSomething();
        System.setOut(consoleStream);

        final String result = buffer
                .toString()
                .chars()
                .filter(Character::isDigit)
                .mapToObj(number -> (char)number)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
