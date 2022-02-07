package com.javarush.task.pro.task15.task1512;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/* 
Задом наперед
*/

public class Solution {
    public static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public static PrintStream stream = new PrintStream(outputStream);

    public static void main(String[] args) throws IOException {
        final String something = something();
        printSomething(something);
        outputStream.reset();
        printSomething(reversed(something));
        System.out.println(outputStream);
    }

    private static String reversed(final String something) {
        return new StringBuilder(something).reverse().toString();
    }


    private static String something() {
        String something = "";
        try (final Scanner scanner = new Scanner(System.in)) {
            something = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Scanner failed: " + e);
        }
        return something;
    }

    public static void printSomething(String str) {
        stream.print(str);
    }
}