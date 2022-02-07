package com.javarush.task.pro.task16.task1607;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/* 
Освоение нового API
*/

public class Solution {

    private static final LocalDate time = LocalDate.of(2020, Month.SEPTEMBER, 12);

    public static void main(String[] args) {
        System.out.println(nowExample());
        System.out.println(ofExample());
        System.out.println(ofYearDayExample());
        System.out.println(ofEpochDayExample());
    }

    static LocalDate nowExample() {
        return LocalDate.now();
    }

    static LocalDate ofExample() {
        return LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth());
    }

    static LocalDate ofYearDayExample() {
        return LocalDate.ofYearDay(time.getYear(), time.getDayOfYear());
    }

    static LocalDate ofEpochDayExample() {
        return LocalDate.ofEpochDay(time.toEpochDay());
    }
}
