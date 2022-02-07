package com.javarush.task.task14.task1408;

public class UkrainianHen extends Hen {

    protected final String description = Country.UKRAINE;

    @Override
    String getDescription() {
        return super.getDescription()
                + myCountry()
                + iBring();
    }

    @Override
    String myCountry() {
        return Hen.COUNTRY + this.description + ".";
    }

    @Override
    String iBring() {
        return Hen.I_BRING + getCountOfEggsPerMonth() + Hen.EGGS_PER_MONTH;
    }

    @Override
    int getCountOfEggsPerMonth() {
        return 502;
    }
}
