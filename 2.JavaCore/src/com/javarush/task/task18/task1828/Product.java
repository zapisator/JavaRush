package com.javarush.task.task18.task1828;

public class Product {
    int id;
    String name;
    String price;
    String quantity;

    public Product(int id, String name, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-8d%-30s%-8s%-4s", id, name, price, quantity);
    }
}