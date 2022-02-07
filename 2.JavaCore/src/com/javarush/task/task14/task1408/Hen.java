package com.javarush.task.task14.task1408;

public abstract class Hen {
    protected static final String I_BRING = " Я несу ";
    protected static final String EGGS_PER_MONTH = " яиц в месяц.";
    protected static final String COUNTRY = " Моя страна - ";

    protected final String description = "Я - курица.";

    abstract int getCountOfEggsPerMonth();
    abstract String myCountry();
    abstract String iBring();

    String getDescription() {
        return description;
    }

}
