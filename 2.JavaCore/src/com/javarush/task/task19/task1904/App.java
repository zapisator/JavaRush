package com.javarush.task.task19.task1904;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int first_min =Integer.MAX_VALUE, second_min = Integer.MAX_VALUE;

        while (scan.hasNextInt())
        {
            int num = scan.nextInt();
            second_min = num;
            if (second_min < first_min)
            {
                second_min = first_min;
                first_min =num;

            }

        }
        System.out.println(second_min);
    }
}
