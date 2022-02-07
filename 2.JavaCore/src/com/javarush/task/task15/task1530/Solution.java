package com.javarush.task.task15.task1530;

/* 
Template pattern
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Arrays.asList(new LatteMaker(), new TeaMaker()).forEach(DrinkMaker::makeDrink);
    }
}
