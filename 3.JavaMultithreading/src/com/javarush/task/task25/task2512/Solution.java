package com.javarush.task.task25.task2512;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        final LinkedList<Throwable> exceptions = new LinkedList<>();
        while (e != null) {
            exceptions.addFirst(e);
            e = e.getCause();
        }

        final StringJoiner stringJoiner = new StringJoiner("\n");

        exceptions.forEach(element -> stringJoiner.add(element.toString()));
        System.out.println(stringJoiner);
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
