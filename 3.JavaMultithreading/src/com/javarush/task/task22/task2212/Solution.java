package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        return telNumber != null && telNumber.matches(
                "^\\+\\d{12}"
                        + "|^\\+\\(\\d{3}\\)\\d{9}"
                        + "|^\\+\\d{1}\\(\\d{3}\\)\\d{8}"
                        + "|^\\+\\d{2}\\(\\d{3}\\)\\d{7}"
                        + "|^\\+\\d{3}\\(\\d{3}\\)\\d{6}"
                        + "|^\\+\\d{4}\\(\\d{3}\\)\\d{5}"
                        + "|^\\+\\d{5}\\(\\d{3}\\)\\d{4}"
                        + "|^\\+\\d{6}\\(\\d{3}\\)\\d{3}"
                        + "|^\\+\\d{7}\\(\\d{3}\\)\\d{2}"
                        + "|^\\+\\d{8}\\(\\d{3}\\)\\d{1}"
                        + "|\\d{10}"
                        + "|^\\(\\d{3}\\)\\d{7}"
                        + "|^\\d{1}\\(\\d{3}\\)\\d{6}"
                        + "|^\\d{2}\\(\\d{3}\\)\\d{5}"
                        + "|^\\d{3}\\(\\d{3}\\)\\d{4}"
                        + "|^\\d{4}\\(\\d{3}\\)\\d{3}"
                        + "|^\\d{5}\\(\\d{3}\\)\\d{2}"
                        + "|^\\d{6}\\(\\d{3}\\)\\d{1}"
        );
    }

    public static void main(String[] args) {
        assert checkTelNumber("+380501234567") ;

        assert checkTelNumber("+(050)001234567");
        assert checkTelNumber("+3(050)01234567");
        assert checkTelNumber("+38(050)1234567");
        assert checkTelNumber("+380(050)234567");
        assert checkTelNumber("+3800(050)34567");
        assert checkTelNumber("+38000(050)4567");
        assert checkTelNumber("+380000(050)567");
        assert checkTelNumber("+3800000(050)67");
        assert checkTelNumber("+38000000(050)7");

        assert checkTelNumber("0501234567") ;

        assert checkTelNumber("(050)1234567");
        assert checkTelNumber("0(501)234567");
        assert checkTelNumber("00(501)34567");
        assert checkTelNumber("000(501)4567");
        assert checkTelNumber("0000(501)567");
        assert checkTelNumber("00000(501)67");
        assert checkTelNumber("000000(501)7");

        assert !checkTelNumber("+38)050(1234567");
        assert !checkTelNumber("+38(050)123-45-67");
        assert !checkTelNumber("050ххх4567");
        assert !checkTelNumber("050123456");
        assert !checkTelNumber("(0)501234567");

        assert !checkTelNumber(null);
    }
}
