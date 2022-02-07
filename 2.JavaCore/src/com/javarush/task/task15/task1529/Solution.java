package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Осваивание статического блока
*/

public class Solution {

    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        try (final Scanner scanner = new Scanner(System.in)) {
            final String machine = scanner.nextLine();

            if (machine.equals("helicopter")) {
                result = new Helicopter();
            } else if (machine.equals("plane")) {
                final int passengers = scanner.nextInt();
                result = new Plane(passengers);
            }
        }
    }
}
