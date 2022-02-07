package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

public class Solution {

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        System.out.println();
        System.out.println(getPartOfString("JavaRush - лучший сервис \tобучения Java\t."));
        System.out.println();
        System.out.println(getPartOfString("обучения Java\t."));
        System.out.println();
        System.out.println(getPartOfString(null));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            final int firstTab = string.indexOf('\t');
            final String substring = string.substring(firstTab + 1);
            return substring.substring(0, substring.indexOf('\t'));
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {

    }
}
