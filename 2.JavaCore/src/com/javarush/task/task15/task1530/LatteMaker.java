package com.javarush.task.task15.task1530;

public class LatteMaker extends DrinkMaker {

    @Override
    void getRightCup() {
        System.out.println(DrinkMaker.GET_CUP_FOR_LATTE);
    }

    @Override
    void putIngredient() {
        System.out.println(DrinkMaker.MAKING_COFFEE);
    }

    @Override
    void pour() {
        System.out.println(DrinkMaker.FILL_WITH_MILK);
    }
}
