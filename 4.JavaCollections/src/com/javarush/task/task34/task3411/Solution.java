package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {

    public static void main(String[] args) {
        int numRings = 4;

        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        //напишите тут ваш код
        if (numRings > 1) {
            moveRing(a, c, b, numRings - 1);
            print(a, b);
            moveRing(c, b, a, numRings - 1);
        } else {
            print(a, b);
        }
    }

    private static void print(char from, char to) {
        System.out.println("from " + from + " to " + to);
    }

}