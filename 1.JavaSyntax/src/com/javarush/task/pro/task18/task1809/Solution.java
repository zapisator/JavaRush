package com.javarush.task.pro.task18.task1809;

import java.util.ArrayList;
import java.util.Collections;

/* 
Прощание со ссылками на методы
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 12, 34, 54, 32, 1, 453, 1111);

        print(numbers);
    }

    public static void print(ArrayList<Integer> numbers) {
        numbers.forEach(number -> System.out.println(number));
    }
}
