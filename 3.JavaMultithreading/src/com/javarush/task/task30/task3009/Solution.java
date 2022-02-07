package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        final Set<Integer> palindromes = new HashSet<>();

        try {
            final int value = Integer.parseInt(number);

            for (int radix = Character.MIN_RADIX; radix <= Character.MAX_RADIX; radix++) {
                final String word = Integer.toString(value, radix);

                if (isPalindrome(new StringBuilder(word))) {
                    palindromes.add(radix);
                }
            }
        } catch (Exception ignore) {
        }
        return palindromes;
    }

    private static boolean isPalindrome(StringBuilder word) {
        return IntStream.range(0, word.length() / 2)
                .allMatch(index -> word.charAt(index) == word.charAt(word.length() - index - 1));
    }
}