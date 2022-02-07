package com.javarush.task.pro.task13.task1319;

/* 
Месяцы в сезоне
*/

public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;


    private static Month[] getTrimester(int ordinal) {
        Month[] trimester = new Month[3];

        for (int i = 0; i < 3; i++) {
            trimester[i] = Month.values()[(ordinal + i) % 12];
        }
        return trimester;
    }

    public static Month[] getWinterMonths() {
        return getTrimester(Month.DECEMBER.ordinal());
    }

    public static Month[] getSpringMonths(){
        return getTrimester(Month.MARCH.ordinal());
    }

    public static Month[] getSummerMonths() {
        return getTrimester(Month.JUNE.ordinal());
    }

    public static Month[] getAutumnMonths() {
        return getTrimester(Month.SEPTEMBER.ordinal());
    }




}
