package com.javarush.task.task15.task1530;

public class TeaMaker extends DrinkMaker {

    @Override
    void getRightCup() {
        System.out.println(DrinkMaker.GET_CUP_FOR_TEA);
    }

    @Override
    void putIngredient() {
        System.out.println(DrinkMaker.PUT_INGREDIENTS);
    }

    @Override
    void pour() {
        System.out.println(DrinkMaker.POUR);
    }
}
