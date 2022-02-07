package com.javarush.task.task30.task3010;

import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {

    public static void main(String[] args) {
        try {
            final String word = args[0];
            final String probableNumber = word.chars()
                    .filter(Character::isLetterOrDigit)
                    .map(Character::toLowerCase)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
            final String decision =
                    probableNumber.length() != word.length() ? "incorrect" : radix(probableNumber);
            System.out.println(decision);
        } catch (Exception ignored) {
        }
    }


    private static String radix(String probableNumber) {
        final char maxCharacter = (char) probableNumber.chars().max().orElse(0);
        String radix;

        if (Character.isDigit(maxCharacter)) {
            radix = maxCharacter == '0' ? "2" : String.valueOf(maxCharacter - '0' + 1);
        } else {
            radix = String.valueOf(maxCharacter - 'a' + 11);
        }
        return radix;
    }
}