package com.javarush.task.task18.task1827;

public class Product {

    private final int id;
    private final String name;
    private final float price;
    private final int quality;

    public Product(int id, String name, float price, int quality) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuality() {
        return quality;
    }
}
