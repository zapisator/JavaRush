package com.javarush.task.task15.task1527;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws MalformedURLException {
        final URL url = new URL(url());
        final String query = url.getQuery();
        final String[] parameters = query.split("&");
        final List<Pair<String, String>> pairs = pairs(parameters);

        printValues(pairs);
        System.out.println();
        pairs.stream()
                .filter(pair -> pair.getKey().equals("obj"))
                .map(Pair::getValue)
                .forEach(Solution::processWithAlert);
    }

    private static void printValues(final List<Pair<String, String>> pairs) {
        pairs.forEach(pair -> printOne(pair.getKey()));
    }

    private static List<Pair<String, String>> pairs(String[] parameters) {
        final List<Pair<String, String>> pairs = new LinkedList<>();

        for (String parameter : parameters) {
            String[] pair = parameter.split("=");
            pairs.add(new Pair<>(pair[0], pair.length == 1 ? null : pair[1]));
        }
        return pairs;
    }

    private static void printOne(String s) {
        System.out.print(s + " ");
    }

    private static void processWithAlert(String s) {
        try {
            double number = Double.parseDouble(s);
            alert(number);
        } catch (NumberFormatException e) {
            alert(s);
        }
    }

    private static String url() {
        String url = "";

        try (final Scanner scanner = new Scanner(System.in)) {
            url = scanner.nextLine();
        }
        return url;
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
