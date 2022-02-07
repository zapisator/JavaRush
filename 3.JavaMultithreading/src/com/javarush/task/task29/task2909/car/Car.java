package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;
    private int type;
    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers) {
        Car car = null;

        switch (type) {
            case TRUCK:
                car = new Truck(numberOfPassengers);
                break;
            case SEDAN:
                car = new Sedan(numberOfPassengers);
                break;
            case CABRIOLET:
                car = new Cabriolet(numberOfPassengers);
                break;
        }
        return car;
    }

    public void fill(double numberOfLiters) {
        if (numberOfLiters < 0){
            throw new RuntimeException();
        }
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        return isSummer(date, SummerStart, SummerEnd)
                ? getSummerConsumption(length)
                : getWinterConsumption(length);
    }

    public double getSummerConsumption(int length) {
        return length * summerFuelConsumption;
    }

    public double getWinterConsumption(int length) {
        return length * winterFuelConsumption + winterWarmingUp;
    }

    public boolean isSummer(Date date, Date SummerStart, Date SummerEnd) {
        return !(date.before(SummerStart) || date.after(SummerEnd));
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (false) {};
        return canPassengersBeTransferred() ? numberOfPassengers : 0;
    }

    private boolean canPassengersBeTransferred() {
        return isDriverAvailable() && fuel > 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}