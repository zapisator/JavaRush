package com.javarush.task.task12.task1205;

/* 
Определимся с животным
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cow()));
        System.out.println(getObjectType(new Dog()));
        System.out.println(getObjectType(new Whale()));
        System.out.println(getObjectType(new Pig()));
    }

    public static String getObjectType(Object o) {
        final CLAZZ className = CLAZZ.fromString(o.getClass().getSimpleName());
        return className == null ? "" : className.representation;
    }

    public static class Cow {
    }

    public static class Dog {
    }

    public static class Whale {
    }

    public static class Pig {
    }

    enum CLAZZ {
        COW (Cow.class.getSimpleName(), "Корова"),
        WHALE (Whale.class.getSimpleName(), "Кит"),
        DOG (Dog.class.getSimpleName(), "Собака"),
        PIG (Pig.class.getSimpleName(), "Неизвестное животное");

        private final String className;
        private final String representation;

        CLAZZ(final String className, final String representation) {
            this.className = className;
            this.representation = representation;
        }

        public static CLAZZ fromString(String className) {
            for (CLAZZ clazz : CLAZZ.values()) {
                if (clazz.className.equalsIgnoreCase(className)) {
                    return clazz;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return className;
        }
    }
}
