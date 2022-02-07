package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("abcrcab"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null) {
            return false;
        }
        s = s.toLowerCase(Locale.ROOT);

        final Set<Character> characters = new HashSet<>();

        s.chars()
                .forEach(character -> {
                    final char symbol = (char) character;

                    if (characters.contains(symbol)) {
                        characters.remove(symbol);
                    } else {
                        characters.add(symbol);
                    }
                });
        return characters.size() <= 1;
    }
}
