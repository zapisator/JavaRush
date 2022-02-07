package com.javarush.task.pro.task13.task1318;

/* 
Следующий месяц, пожалуйста
*/

public class Solution {

    private static Month FIRST = Month.values()[0];
    private static Month LAST = Month.values()[Month.values().length - 1];

    public static void main(String[] args) {
        System.out.println(getNextMonth(Month.JANUARY));
        System.out.println(getNextMonth(Month.JULY));
        System.out.println(getNextMonth(LAST));
    }

    public static Month getNextMonth(Month month) {
        Month result = null;
        if (month == LAST) {
            result = FIRST;
        } else {
            result = Month.values()[month.ordinal() + 1];
        }
        return result;
    }
}
