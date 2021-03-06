package com.javarush.task.task30.task3004;

import java.util.concurrent.ForkJoinPool;

/* 
Fork/Join
*/

public class Solution {
    private String binaryRepresentationMethod(int x) {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return binaryRepresentationMethod(b) + result;
        }
        return result;
    }


    public static void main(String[] args) {
        int x;

        for (x = 0; x < 127; x++) {
            Solution solution = new Solution();
            String result1 = solution.binaryRepresentationMethod(x);
            System.out.println(result1);

            ForkJoinPool forkJoinPool = new ForkJoinPool();
            String result2 = forkJoinPool.invoke(new BinaryRepresentationTask(x));
            System.out.println(result2);
            System.out.println();

        }
    }

}
