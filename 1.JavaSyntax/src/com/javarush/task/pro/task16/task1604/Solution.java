package com.javarush.task.pro.task16.task1604;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* 
День недели рождения твоего
*/

public class Solution {

    static Calendar birthDate = new GregorianCalendar(2021, Calendar.JULY, 8);

    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Calendar calendar) {
        final Date date = calendar.getTime();
        final SimpleDateFormat format = new SimpleDateFormat("EEEE", new Locale("ru", "RU"));
        return format.format(date);
    }
}
