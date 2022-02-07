package com.javarush.task.task35.task3511;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/* 
Wildcards для коллекций
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(sum(Arrays.asList(1, 2.3)));
        System.out.println(multiply(Arrays.asList(3., 2.3)));
        System.out.println(concat(Arrays.asList("hello", 1, 3.3, new BigDecimal("3.3"))));
        System.out.println(combine(Arrays.asList(
                Arrays.asList(1, 3.),
                Arrays.asList("enth", "nthnth", 3),
                Arrays.asList(new StringBuilder(), new StringJoiner(""))))
        );
    }

    public static Double sum(List<? extends Number> list) {
        Double result = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Number numb = list.get(i);
            result += numb.doubleValue();
        }
        return result;
    }

    public static Double multiply(List<? extends Number> list) {
        Double result = 1.0;
        for (int i = 0; i < list.size(); i++) {
            Number numb = list.get(i);
            result *= numb.doubleValue();
        }
        return result;
    }

    public static String concat(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : list) {
            builder.append(obj);
        }
        return builder.toString();
    }

    public static List<?> combine(List<? extends Collection> list) {
        List<? super Object> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Collection<?> collection = list.get(i);
            result.addAll(collection);
        }
        return result;
    }
}
