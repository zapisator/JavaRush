package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/

import java.util.Objects;

public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Object clone = super.clone();
            return new A(i, j);
        }

        public int getI() {
            return i;
        }

        @Override
        public String toString() {
            return String.format("i: %d, j: %d", i, j);
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public String toString() {
            return super.toString() + String.format(", name: %s", getName());
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new C(super.getI(), super.getJ(), super.getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A(1, 2);
        A a1 = (A) a.clone();
        C c = new C(a.i, a.j, "name");
        C c1 = (C) c.clone();

        System.out.printf("a: [%s], \na1: [%s], \nc: [%s], \nc1: [%s]\n", a, a1, c, c1);
    }
}
