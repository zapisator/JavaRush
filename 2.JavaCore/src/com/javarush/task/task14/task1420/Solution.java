package com.javarush.task.task14.task1420;

import java.math.BigInteger;
import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        final int[] numbers = numbers();
        final BigInteger leastCommonDenominator = BigInteger.valueOf(numbers[0]).gcd(BigInteger.valueOf(numbers[1]));

        System.out.println(leastCommonDenominator);
    }

    private static int[] numbers() throws Exception {
        int[] numbers = new int[2];

        try (final Scanner scanner = new Scanner(System.in)) {
            numbers[0] = scanner.nextInt();
            numbers[1] = scanner.nextInt();
            if (numbers[0] < 1 || numbers[1] < 1) {
                throw new Exception();
            }
        }
        return numbers;
    }
}
