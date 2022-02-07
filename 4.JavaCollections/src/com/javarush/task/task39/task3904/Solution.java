package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
Моё рассуждение.
Задача о получении н-го элемента последовательности Трибонначи.
Последовательность смещена влево.
*/

public class Solution {

    private static int n = 10;

    public static void main(String[] args) {
        for (int n = -1; n <= 70; n++) {
            System.out.println(
                    "The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(
                            n));
        }
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0;
        }
        n += 2;

        final long[] tribonacci = new long[n + 1];

        tribonacci[0] = 0;
        tribonacci[1] = 0;
        tribonacci[2] = 1;
        for (int i = 3; i < tribonacci.length; i++) {
            tribonacci[i] = tribonacci[i - 3] + tribonacci[i - 2] + tribonacci[i - 1];
        }
        return tribonacci[n];
    }

}

