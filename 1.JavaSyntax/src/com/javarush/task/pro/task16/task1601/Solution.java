package com.javarush.task.pro.task16.task1601;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Лишь бы не в понедельник:)
*/

public class Solution {

    static Date birthDate = new Date("Jan 25 21:48:00 1986");

    public static void main(String[] args) {
        System.out.print(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEEE", new Locale("ru", "RU"));
        return format.format(date);
    }
}
