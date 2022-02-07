package com.javarush.task.task19.task1918;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int min = console.nextInt();
        if((min <= 0) || (min >= 0))
        {
            while(console.hasNextInt())
            {
                int x = console.nextInt();
                if(x < min)
                    min = x;
            }
            System.out.println(min);
        }
        min = Integer.MAX_VALUE;
    }
}
