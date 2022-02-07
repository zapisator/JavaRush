package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {

    public static void main(String[] args) {
        test_null();
        test_equalLengthStrings_negative();
        test_differentLength_negative();
        test_equalLengthStrings_positive();
        test_differetnLength_positive();
    }

    private static void test_null() {
        check(null, null, false);
        check(null, "", false);
        check(null, "a", false);
    }

    private static void test_equalLengthStrings_positive() {
        check("", "", true);

        check("a", "a", true);
        check("a", "b", true);

        check("aa", "aa", true);
        check("aa", "ab", true);
        check("aa", "ba", true);

        check("aaa", "aaa", true);
        check("aaa", "aab", true);
        check("aaa", "aba", true);
        check("aaa", "baa", true);
    }

    private static void test_equalLengthStrings_negative() {
        check("aa", "bb", false);

        check("aaa", "abb", false);
        check("aaa", "bab", false);
        check("aaa", "abb", false);
        check("aaa", "bbb", false);
    }


    private static void test_differetnLength_positive() {
        check("", "a", true);

        check("a", "aa", true);
        check("a", "ab", true);

        check("aa", "aaa", true);
        check("aa", "aab", true);
        check("aa", "aba", true);
        check("aa", "baa", true);

        check("ab", "aab", true);
        check("ab", "aba", true);

        check("ab", "abb", true);
        check("ab", "bab", true);
    }

    private static void test_differentLength_negative() {
        check("aa", "abb", false);
        check("aa", "bab", false);
        check("aa", "bba", false);

        check("aa", "bbb", false);

        check("ab", "aaa", false);

        check("ab", "baa", false);

        check("ab", "bba", false);

        check("ab", "bbb", false);
    }

    private static void check(String first, String second, boolean expectation) {
        subtest(first, second, expectation);

        if (notSymmetricParameters(first, second)) {
            subtest(second, first, expectation);
        }
    }

    private static boolean notSymmetricParameters(String first, String second) {
        return (first == null && second != null) || (first != null && !first.equals(second));
    }

    private static void subtest(String first, String second, boolean expectation) {
        final String ansiReset = "\u001B[0m";
        final String ansiRed = "\u001B[31m";
        final String ansiGreen = "\u001B[32m";
        final boolean observed = isOneEditAway(first, second);

        System.out.printf(
                "%5s %5s : %5s :: %s%n",
                first == null ? null : "\"" + first + "\"",
                second == null ? null : "\"" + second + "\"",
                observed,
                (observed == expectation
                        ? ansiGreen + "SUCCEED"
                        : ansiRed + "FAILED ") + ansiReset
        );
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null
                || second == null
                || Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        final boolean isLongerFirst = first.length() > second.length();

        return isSuitable(isLongerFirst ? first : second, isLongerFirst ? second : first);
    }

    private static boolean isSuitable(String longer, String smaller) {
        int longerIndex = 0;
        int smallerIndex = 0;
        int differences = 0;

        while (longerIndex < longer.length()
                && smallerIndex < smaller.length()
                && differences <= 1) {
            final char longerChar = longer.charAt(longerIndex);
            final char smallerChar = smaller.charAt(smallerIndex);

            if (smallerChar != longerChar) {
                differences++;
                if (longer.length() > smaller.length()) {
                    longerIndex++;
                    if (longerIndex < longer.length()
                            && longer.charAt(longerIndex) != smallerChar) {
                        differences++;
                    }
                }
            }
            longerIndex++;
            smallerIndex++;
        }
        if (differences == 0 && longerIndex < longer.length()) {
            differences++;
            longerIndex++;
        }
        return longerIndex == longer.length()
                && smallerIndex == smaller.length()
                && differences <= 1;
    }

}
