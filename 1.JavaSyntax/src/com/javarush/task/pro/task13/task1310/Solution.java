package com.javarush.task.pro.task13.task1310;

import java.util.HashMap;
import java.util.Map.Entry;

/* 
Успеваемость студентов-2
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println("Список студентов группы: ");
        printStudents();
        System.out.print("Средний балл группы: " + getAverageMark());
    }

    public static void addStudents() {
        grades.put("Давыдов Олег", 4.3d);
        grades.put("Шульга Николай", 4.1d);
        grades.put("Колос Василий", 4.9d);
        grades.put("Шевченко Тарас", 3.7d);
        grades.put("Марчук Любослав", 3.2d);
    }

    public static void printStudents() {
        for (Entry<String, Double> student : grades.entrySet()) {
            System.out.println(student.getKey());
        }
    }

    public static Double getAverageMark() {
        double sum = 0;

        for (Entry<String, Double> student: grades.entrySet()) {
            sum += student.getValue();
        }
        return sum / grades.size();
    }
}
