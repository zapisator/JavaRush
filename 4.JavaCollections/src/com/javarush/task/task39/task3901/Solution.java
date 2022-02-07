package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        final Set<Character> characters = new HashSet<>();
        int maxLength = 0;
        boolean isUnique = true;

        for (int i = 0; i < s.length() && i + maxLength < s.length(); i++) {
            for (int j = i; j < s.length() && isUnique; j++) {
                final char symbol = s.charAt(j);
                final int length = j - i + 1;

                if (characters.contains(symbol)) {
                    isUnique = false;
                } else {
                    characters.add(symbol);
                    maxLength = Math.max(maxLength, length);
                }
            }
            characters.clear();
            isUnique = true;
        }
        return maxLength;
    }
}
