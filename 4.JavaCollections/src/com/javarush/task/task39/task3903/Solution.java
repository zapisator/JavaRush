package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        long number = 1L << 63;
        int i = 63;
        int j = 0;

        System.out.printf("number: %s\nswaped: %s", Long.toBinaryString(number),
                Long.toBinaryString(swapBits(number, i, j)));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Please enter a number: ");
//
//        long number = Long.parseLong(reader.readLine());
//        System.out.println("Please enter the first index: ");
//        int i = Integer.parseInt(reader.readLine());
//        System.out.println("Please enter the second index: ");
//        int j = Integer.parseInt(reader.readLine());
//
//        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        final long ithValue = number >>> i & 1;
        final long jthValue = number >>> j & 1;

        if (ithValue != jthValue) {
            final long bitMask = (1L << i) | (1L << j);

            number ^= bitMask;
        }
        return number;
    }
}
