package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;
import java.util.Random;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        final Random random = new Random();
        for (int student = 5; student > 0; student--) {
            final String name =  random.ints('0', 'z' + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(random.nextInt(10))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            grades.put(name, (double)random.nextInt(5));
        }
    }
}
