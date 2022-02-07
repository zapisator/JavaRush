package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Integer integer = 8;
        Object obj = integer;
        String str = (String) obj;
    }

    public void methodThrowsNullPointerException() {
        String str = null;

        str.length();
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsClassCastException();
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
