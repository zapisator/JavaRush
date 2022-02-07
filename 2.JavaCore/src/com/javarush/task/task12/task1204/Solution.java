package com.javarush.task.task12.task1204;

/* 
То ли птица, то ли лампа
*/

public class Solution {
    public static void main(String[] args) {
        printObjectType(new Cat());
        printObjectType(new Bird());
        printObjectType(new Lamp());
        printObjectType(new Cat());
        printObjectType(new Dog());
    }

    public static void printObjectType(Object o) {
        final CLAZZ className = CLAZZ.fromString(o.getClass().getSimpleName());
        System.out.println(className == null ? "" : className.representation);
    }

    public static class Cat {
    }

    public static class Dog {
    }

    public static class Bird {
    }

    public static class Lamp {
    }

    enum CLAZZ {
        CAT (Cat.class.getSimpleName(), "Кошка"),
        DOG (Dog.class.getSimpleName(), "Собака"),
        BIRD (Bird.class.getSimpleName(), "Птица"),
        LAMP (Lamp.class.getSimpleName(), "Лампа");

        private final String className;
        private final String representation;

        CLAZZ(final String className, final String representation) {
            this.className = className;
            this.representation = representation;
        }

        public static CLAZZ fromString(String className) {
            for (CLAZZ b : CLAZZ.values()) {
                if (b.className.equalsIgnoreCase(className)) {
                    return b;
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
