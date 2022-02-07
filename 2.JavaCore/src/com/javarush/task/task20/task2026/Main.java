package com.javarush.task.task20.task2026;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        boolean isENTER = false;
        while (!isENTER) {
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                sum = sum + x;
            } else if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("ENTER")) {
                    isENTER = true;
                }
                System.out.println(sum);
            }


        }
    }
}