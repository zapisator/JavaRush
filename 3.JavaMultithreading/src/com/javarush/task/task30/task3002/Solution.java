package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        final int radix = radix(s);
        final String subString = subString(s, radix);
        return String.valueOf(Integer.parseInt(subString, radix(s)));
    }

    private static String subString(String s, int radix) {
        String subString;

        if (radix == 10) {
            subString = s;
        } else if (radix == 8) {
            subString = s.substring(1);
        } else {
            subString = s.substring(2);
        }
        return subString;
    }

    private static int radix(String s) {
        int decision = 10;

        if (s.length() > 1) {
            if (s.charAt(0) == '0') {
                if (s.charAt(1) == 'b' || s.charAt(1) == 'B') {
                    decision = 2;
                } else if (s.charAt(1) == 'x' || s.charAt(1) == 'X') {
                    decision = 16;
                } else {
                    decision = 8;
                }
            }
        }
        return decision;
    }
}
