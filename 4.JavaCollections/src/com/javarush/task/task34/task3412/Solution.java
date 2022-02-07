package com.javarush.task.task34.task3412;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
Добавление логирования в класс
*/

public class Solution {

    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug("value1 = " + value1 + "\tvalue2 = " + value2 + "\tvalue3 = " + value3);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution(0, "Me", new Date());

        solution.calculateAndSetValue3(3L);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(2, 3);

    }

    public void calculateAndSetValue3(long value) {
        logger.trace("calculateAndSetValue3 is invoked.");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("value1 = " + value1);
        } else {
            value1 = (int) value;
            logger.debug("value1 = " + value1);
        }
    }

    public void printString() {
        logger.trace("calculateAndSetValue3 is invoked.");
        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("calculateAndSetValue3 is invoked.");
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        logger.trace("calculateAndSetValue3 is invoked.");
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(e.toString());
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        logger.debug("value1 = " + value1);
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        logger.debug("value2 = " + value2);
    }

    public void setValue3(Date value3) {
        this.value3 = value3;
        logger.debug("value3 = " + value3);
    }
}
