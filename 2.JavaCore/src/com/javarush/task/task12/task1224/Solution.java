package com.javarush.task.task12.task1224;

/* 
Неведома зверушка
*/

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cat()));
        System.out.println(getObjectType(new Tiger()));
        System.out.println(getObjectType(new Lion()));
        System.out.println(getObjectType(new Bull()));
        System.out.println(getObjectType(new Pig()));
    }

    public static String getObjectType(Object o) {
        final Map<String, String> corresponds = Stream.of(
                new String[][] {
                        {Cat.class.getSimpleName(), "Кот"},
                        {Tiger.class.getSimpleName(), "Тигр"},
                        {Lion.class.getSimpleName(), "Лев"},
                        {Bull.class.getSimpleName(), "Бык"},
                }
        ).collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
        final String className = o.getClass().getSimpleName();

        if (corresponds.containsKey(className)) {
            return corresponds.get(className);
        }
        return "Животное";
    }

    public static class Cat {
    }

    public static class Tiger {
    }

    public static class Lion {
    }

    public static class Bull {
    }

    public static class Pig {
    }
}
