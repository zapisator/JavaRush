package com.javarush.task.pro.task17.task1708;

/* 
Минимальное и максимальное
*/

public class MinMaxUtil {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println(
                "min: \n"
                        + min(numbers[4]) + "\n"
                        + min(numbers[4], numbers[3]) + "\n"
                        + min(numbers[4], numbers[3], numbers[2]) + "\n"
                        + min(numbers[4], numbers[3], numbers[2], numbers[1]) + "\n"
                        + min(numbers[4], numbers[3], numbers[2], numbers[1], numbers[0]) + "\n\n"
        );

        System.out.println(
                "max:\n"
                        + max(numbers[0]) + "\n"
                        + max(numbers[0], numbers[1]) + "\n"
                        + max(numbers[0], numbers[1], numbers[2]) + "\n"
                        + max(numbers[0], numbers[1], numbers[2], numbers[3]) + "\n"
                        + max(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]) + "\n"
        );
    }

    public static int min(int firstNumber) {
        return firstNumber;
    }

    public static int min(int firstNumber, int secondNumber) {
        return Math.min(firstNumber, secondNumber);
    }

    public static int min(int firstNumber, int secondNumber, int thirdNumber) {
        return Math.min(Math.min(firstNumber, secondNumber), thirdNumber);
    }

    public static int min(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber) {
        return Math.min(Math.min(firstNumber, secondNumber), Math.min(thirdNumber, fourthNumber));
    }


    public static int min(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber, int fifthNumber) {
        return Math.min(Math.min(Math.min(firstNumber, secondNumber), Math.min(thirdNumber, fourthNumber)), fifthNumber);
    }

    public static int max(int firstNumber) {
        return firstNumber;
    }

    public static int max(int firstNumber, int secondNumber) {
        return Math.max(firstNumber, secondNumber);
    }

    public static int max(int firstNumber, int secondNumber, int thirdNumber) {
        return Math.max(Math.max(firstNumber, secondNumber), thirdNumber);
    }

    public static int max(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber) {
        return Math.max(Math.max(firstNumber, secondNumber), Math.max(thirdNumber, fourthNumber));
    }

    public static int max(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber, int fifthNumber) {
        return Math.max(Math.max(Math.max(firstNumber, secondNumber), Math.max(thirdNumber, fourthNumber)), fifthNumber);
    }
}
