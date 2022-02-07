package com.javarush.task.task15.task1530;

public abstract class DrinkMaker {

    static final String POUR = "Заливаем кипятком";
    static final String GET_CUP_FOR_LATTE = "Берем чашку для латте";
    static final String PUT_INGREDIENTS = "Насыпаем чай";
    static final String GET_CUP_FOR_TEA = "Берем чашку для чая";
    static final String FILL_WITH_MILK = "Заливаем молоком с пенкой";
    static final String MAKING_COFFEE = "Делаем кофе";

    abstract void getRightCup();
    abstract void putIngredient();
    abstract void pour();

    void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }

}
