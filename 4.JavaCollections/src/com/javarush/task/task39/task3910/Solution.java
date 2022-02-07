package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        for (int i = 3; i < 2000000; i *= 3) {
            i = -i;
            System.out.printf("i: %30s\tiBinary: %10"
                    + "s\tisPof3:%s\n", Integer.toBinaryString(i), i, isPowerOfThree(i));
            i = -i;
        }
    }

    /* The maximum power of 3 value that
       integer can hold is 1162261467 ( 3^19 ) .*/
    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
//        if (n == 0 || n == Integer.MIN_VALUE) {
//            return false;
//        }
//        if (n < 0) {
//            n = -n;
//        }
        return 1162261467 % n == 0;
    }
}
