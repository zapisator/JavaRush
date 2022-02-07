package com.javarush.task.task34.task3402;

/* 
Факториал с помощью рекурсии
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        System.out.println(solution.factorial(9));     //362880
//        System.out.println(solution.factorial(0));     //1
//        System.out.println(solution.factorial(1));     //1

        for (int i = 0; i < 13; i++) {
            System.out.println(solution.factorial(i));
        }
    }

    public int factorial(int n) {
//        if (n < 0 || n > 12) {
//            throw new RuntimeException(n + " could not be represented by integer type.");
//        }
        if (n > 0) {
            return n * factorial(n - 1);
        }
        return 1;
    }
}
