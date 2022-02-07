package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i < 3000; i++) {
            solution.createExpression(i);
        }
    }

    public void createExpression(int number) {
        final StringBuilder numberRadix3 = new StringBuilder(Integer.toString(number, 3)).reverse();
        final TernaryNumber ternaryNumber = new TernaryNumber(number);

        appendNotZeroCiphers(numberRadix3, ternaryNumber);
        appendOneMoreIfHasSurplus(ternaryNumber);
        System.out.println(ternaryNumber.result);
    }

    private void appendNotZeroCiphers(StringBuilder numberRadix3, TernaryNumber ternaryNumber) {
        for (; ternaryNumber.i < numberRadix3.length(); ternaryNumber.i++) {
            final int currentSurplus =
                    numberRadix3.charAt(ternaryNumber.i) - '0' + ternaryNumber.nextSurplus;
            if (currentSurplus % 3 != 0) {
                ternaryNumber.result.append(' ')
                        .append(currentSurplus == 1 ? '+' : '-')
                        .append(' ')
                        .append((int) Math.pow(3, ternaryNumber.i));
            }
            ternaryNumber.nextSurplus = currentSurplus > 1 ? 1 : 0;
        }
    }

    private void appendOneMoreIfHasSurplus(TernaryNumber ternaryNumber) {
        if (ternaryNumber.nextSurplus == 1) {
            ternaryNumber.result.append(' ')
                    .append('+')
                    .append(' ')
                    .append((int) Math.pow(3, ternaryNumber.i));
        }
    }

    private static class TernaryNumber {

        final StringBuilder result;
        int nextSurplus = 0;
        int i = 0;

        TernaryNumber(int number) {
            result = new StringBuilder(number + " =");
        }
    }

}