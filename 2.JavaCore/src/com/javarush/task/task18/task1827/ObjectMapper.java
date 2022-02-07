package com.javarush.task.task18.task1827;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ObjectMapper {

    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int LENGTH = 3;
    final int[] schema;

    private ObjectMapper(int[] schema) {
        this.schema = schema;
    }

    public static ObjectMapper create(String line) {
        final int[] schema = new int[LENGTH + 1];

        schema[NAME_INDEX] = firstCharOfNameIndex(line);
        schema[PRICE_INDEX] = firstCharOfPriceIndex(line, schema[NAME_INDEX]);
        schema[QUANTITY_INDEX] = firstCharOfQuantityIndex(line, schema[PRICE_INDEX]);
        schema[LENGTH] = line.length() - 1;
        return new ObjectMapper(schema);
    }

    public static ObjectMapper create(final int[] schema) {
        return new ObjectMapper(schema);
    }

    private static int firstCharOfNameIndex(String line) {
        int i = 0;
        char current = line.charAt(i);

        while (!Character.isAlphabetic(current)) {
            i++;
            current = line.charAt(i);
        }
        return i;
    }

    private static int firstCharOfPriceIndex(String line, int i) {
        char current = line.charAt(i);

        while (!Character.isDigit(current)) {
            i++;
            current = line.charAt(i);
        }
        return i;
    }

    private static int firstCharOfQuantityIndex(String line, int i) {
        char current = line.charAt(i);

        while (current != '.') {
            i++;
            current = line.charAt(i);
        }
        i+= 3;
        current = line.charAt(i);
        while (!Character.isDigit(current)) {
            i++;
            current = line.charAt(i);
        }
        return i;
    }

    public Product readValue(final String line) {
        final int id = Integer.parseInt(line.substring(0, schema[0]).trim());
        final String name = line.substring(schema[0], schema[1]).trim();
        final float price = Float.parseFloat(line.substring(schema[1], schema[2]).trim());
        final int quantity = Integer.parseInt(line.substring(schema[2]).trim());

        return new Product(id, name, price, quantity);
    }

    public List<Product> readValues(final List<String> lines) {
        return lines.stream()
                .map(this::readValue)
                .collect(Collectors.toList());
    }

    public String writeValue(final Product product) {
        String field;
        return  "\n"
                + (field = String.valueOf(product.getId()))
                + space(schema[NAME_INDEX] - field.length())
                + (field = product.getName())
                + space(schema[PRICE_INDEX] - schema[NAME_INDEX] - field.length())
                + (field = String.format(Locale.ENGLISH, "%.2f", product.getPrice()))
                + space(schema[QUANTITY_INDEX] - schema[PRICE_INDEX] - field.length())
                + (field = String.valueOf(product.getQuality()))
                + space(schema[LENGTH] - schema[QUANTITY_INDEX] - field.length());
    }

    private String space(int i) {
        return generate(() -> " ")
                .limit(i)
                .collect(joining());
    }
}
