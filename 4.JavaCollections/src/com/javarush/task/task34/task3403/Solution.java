package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {

    public static void main(String[] args) {
        final Solution solution = new Solution();

        for (int i = 33; i < 133; i++) {
            solution.recurse(i);
            System.out.println();
        }
    }

    public void recurse(int n) {
        if (n <= 1) {
            return;
        }
        int i = 2;
        int remainder = n % i;

        while (i < n && remainder != 0) {
            i++;
            remainder = n % i;
        }

        int quotient = n / i;

        System.out.print(i);
        if (quotient != 1) {
            System.out.print(' ');
            recurse(quotient);
        }
    }
}
