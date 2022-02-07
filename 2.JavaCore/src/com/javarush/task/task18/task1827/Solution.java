package com.javarush.task.task18.task1827;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {

//        args = new String[]{"-c", "Шорты пляжные булые", "71.00", "3"};

        if (args.length == 4) {
            switch (args[0]) {
                case "-c":
                    create(args[1], Float.parseFloat(args[2]), Integer.parseInt(args[3]));
                    break;
            }
        }
    }

    private static void create(String name, float price, int quantity) {
        final String fileName = fileName();
        final List<String> fileContent = fileContent(fileName);
        final int[] schema = new int[]{8, 8 + 30, 8 + 30 + 8, 8 + 30 + 8 + 4};
        final ObjectMapper mapper = ObjectMapper.create(schema);
        final List<Product> products = mapper.readValues(fileContent);
        final int maxId = products.stream()
                .map(Product::getId)
                .max(Comparator.comparingInt(product -> product))
                .orElse(0);
        final Product product = new Product(maxId + 1, name, price, quantity);
        appendToFile(fileName, mapper.writeValue(product));
    }

    private static String fileName() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file name:\n", e);
        }
    }

    private static List<String> fileContent(final String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final List<String> lines = new ArrayList<>();
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read a file: \n", e);
        }
    }

    private static void appendToFile(final String fileName, final String writeValue) {
        try (final FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(writeValue);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write:\n", e);
        }
    }

}
