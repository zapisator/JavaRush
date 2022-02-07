package com.javarush.task.task22.task2210;

import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {

    public static void main(String[] args) {
        assert Arrays.equals(new String[]{"level22", "lesson13", "task01"},
                getTokens("level22.lesson13.task01", "."));
    }

    public static String[] getTokens(String query, String delimiter) {
        return Collections.list(new StringTokenizer(query, delimiter)).stream()
                .map(token -> (String)token)
                .toArray(String[]::new);
    }
}
