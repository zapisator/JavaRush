package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

import java.util.Arrays;
import java.util.Random;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        System.out.println(getPartOfString("Амиго   Amigo"));
        if (new Random().nextInt() % 2 == 0) {
            System.out.println(getPartOfString("2"));
        } else {
            System.out.println(getPartOfString(null));
        }
    }

    public static String getPartOfString(String string) {
        try {
            final String[] tokens = string.split(" ");

            throwIfTooFewTokens(tokens.length);
            return String.join(" ", Arrays.copyOfRange(tokens, 1, 5));
        } catch (Exception e) {
            throw new TooShortStringException(e);
        }
    }

    private static void throwIfTooFewTokens(int length) {
        if (length < 5) {
            throw new RuntimeException();
        }
    }

    public static class TooShortStringException extends RuntimeException {

        public TooShortStringException(Throwable cause) {
            super(cause);
        }
    }
}
