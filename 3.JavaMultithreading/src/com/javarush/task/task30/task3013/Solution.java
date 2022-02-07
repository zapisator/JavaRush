package com.javarush.task.task30.task3013;

/* 
Битовые операции
*/

import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);
        IntStream.range(0, 300)
                .forEach(nb -> System.out.printf("%06d\t%32s\n", nb, Integer.toString(solution.resetLowerBits(nb), 2)));
    }

    public int resetLowerBits(int number) {
        return ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)
                | ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)) >> 16)) & ~((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)
                | ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)) >> 16)) >> 1)) | (~(((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)
                | ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)) >> 16)) & ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)
                | ((((number >> 0) | (number >> 1))
                | (((number >> 0) | (number >> 1)) >> 2)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)
                | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2) | ((((number >> 0) | (number >> 1)) | (((number >> 0) | (number >> 1)) >> 2)) >> 4)) >> 8)) >> 16)) >> 1));
    }
}