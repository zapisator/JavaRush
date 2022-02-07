package com.javarush.task.task18.task1828;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* 
Прайсы 2
*/

public class Solution {

    public static void main(String[] args) throws Exception {

//        args = new String[]{"-u", "19846",   "Шорты пляжные красные0", "159.00",  "12"};

        final String fileName = fileName();
        final List<Product> products = new ArrayList<>();
        read(fileName, products);
        proceede(args, products);
        write(fileName, products);
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void read(String fileName, List<Product> products) {
        try (final BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                final Product product = product(fileReader.readLine());
                products.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void proceede(String[] args, List<Product> products) {
        switch (args[0]) {
            case "-u": {
                update(args, products);
                break;
            }
            case "-d": {
                delete(args, products);
                break;
            }
        }
    }

    private static void update(String[] args, List<Product> products) {
        final int id = Integer.parseInt(args[1]);
        final Product product = products.stream()
                .filter(product1 -> product1.id == id)
                .findFirst()
                .orElse(null);

        if (product != null) {
            product.name = name(Optional.ofNullable(args[2]).orElse(""));
            product.price = price(Optional.ofNullable(args[3]).orElse(""));
            product.quantity = quantity(Optional.ofNullable(args[4]).orElse(""));
        }
    }

    private static void delete(String[] args, List<Product> products) {
        final int id = Integer.parseInt(args[1]);
        products.stream()
                .filter(product1 -> product1.id == id)
                .findFirst()
                .ifPresent(products::remove);
    }

    private static String name(final String arg) {
        String name = arg.trim();
        return name.length() > 30 ? name.substring(0, 30) : name;
    }

    private static String price(final String arg) {
        String price = arg.trim();
        return price.length() > 8 ? price.substring(0, 30) : price;
    }

    private static String quantity(final String arg) {
        String price = arg.trim();
        return price.length() > 4 ? price.substring(0, 30) : price;
    }

    private static void write(String fileName, List<Product> products) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Product product : products) {
                fileWriter.write(product.toString());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product product(String string) {
        String id = string.substring(0, 8).trim();
        String name = string.substring(8, 38).trim();
        String price = string.substring(38, 46).trim();
        String quantity = string.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price, quantity);
    }
}
