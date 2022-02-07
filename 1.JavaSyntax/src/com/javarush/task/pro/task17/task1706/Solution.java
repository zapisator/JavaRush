package com.javarush.task.pro.task17.task1706;

/* 
Хищники vs Травоедные
*/

public class Solution {
    public static void main(String[] args) {
        printRation(new Cow());
        printRation(new Lion());
        printRation(new Elephant());
        printRation(new Wolf());
    }

    public static void printRation(Animal animal){
        String herbivore = "Любит траву";
        String predator = "Любит мясо";

        System.out.println(animal instanceof Predator ? predator : herbivore);
    }
}
