package com.javarush.task.task22.task2208;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {

    public static String getQuery(Map<String, String> params) {
        final StringBuilder builder = new StringBuilder();

        for (final String key : params.keySet()) {
            final String value = params.getOrDefault(key, null);
            if (value != null) {
                if (builder.length() > 0) {
                    builder.append(" and ");
                }
                builder.append(key).append(" = '").append(value).append('\'');
            }
        }
        return builder.toString();
    }

    public static String getQueryReference(Map<String, String> params) {
        StringBuilder queryBuilder = new StringBuilder();
        for (String s : params.keySet()) {
            String value = params.get(s);
            if (value == null) {
                continue;
            }
            if (queryBuilder.toString().length() != 0) {
                queryBuilder.append(" and ");
            }
            queryBuilder.append(s + " = '" + value + "'");
        }
        return queryBuilder.toString();
    }

    public static void main(String[] args) {
        test();

//        final StringJoiner joiner = new StringJoiner(", ");
//        for (int i = 1; i < 22; i++) {
//            joiner.add("params" + i);
//        }
//        System.out.println(joiner.toString());
    }

    private static void test() {
        final Map<String, String>[] cases = cases();

        for (Map<String, String> aCase : cases) {
            final String reference = getQueryReference(aCase);
            final String my = getQuery(aCase);

            System.out.println(reference.equals(my));
            System.out.println("reference: |" + reference + "|");
            System.out.println("my       : |" + my + "|");
            System.out.println();
        }
    }

    private static Map<String, String>[] cases() {
        final Map<String, String> params1 = new HashMap<>();
        params1.put("name", "Ivanov");
        params1.put("country", "Ukraine");
        params1.put("city", "Kiev");
        params1.put("age", "7");

        final Map<String, String> params2 = new HashMap<>();
        params2.put("name", "Ivanov");
        params2.put("country", "Ukraine");
        params2.put("city", "Kiev");
        params2.put("age", null);

        final Map<String, String> params3 = new HashMap<>();
        params3.put("name", "Ivanov");
        params3.put("country", "Ukraine");
        params3.put("city", null);
        params3.put("age", "7");

        final Map<String, String> params4 = new HashMap<>();
        params4.put("name", "Ivanov");
        params4.put("country", null);
        params4.put("city", "Kiev");
        params4.put("age", "7");

        final Map<String, String> params5 = new HashMap<>();
        params5.put("name", null);
        params5.put("country", "Ukraine");
        params5.put("city", "Kiev");
        params5.put("age", "7");

        final Map<String, String> params6 = new HashMap<>();
//        params6.put("name", "Ivanov");
        params6.put("country", "Ukraine");
        params6.put("city", "Kiev");
        params6.put("age", "7");

        final Map<String, String> params7 = new HashMap<>();
        params7.put("name", "Ivanov");
//        params7.put("country", "Ukraine");
        params7.put("city", "Kiev");
        params7.put("age", "7");

        final Map<String, String> params8 = new HashMap<>();
        params8.put("name", "Ivanov");
        params8.put("country", "Ukraine");
//        params8.put("city", "Kiev");
        params8.put("age", "7");

        final Map<String, String> params9 = new HashMap<>();
        params9.put("name", "Ivanov");
        params9.put("country", "Ukraine");
        params9.put("city", "Kiev");
//        params9.put("age", "7");

        final Map<String, String> params10 = new HashMap<>();
        params10.put("name", "Ivanov");
        params10.put("country", "Ukraine");
//        params10.put("city", "Kiev");
//        params10.put("age", "7");

        final Map<String, String> params11 = new HashMap<>();
        params11.put("name", "Ivanov");
        params11.put("country", "Ukraine");
//        params11.put("city", "Kiev");
//        params11.put("age", "7");

        final Map<String, String> params12 = new HashMap<>();
        params12.put("name", "Ivanov");
//        params12.put("country", "Ukraine");
        params12.put("city", "Kiev");
//        params12.put("age", "7");

        final Map<String, String> params13 = new HashMap<>();
        params13.put("name", "Ivanov");
//        params13.put("country", "Ukraine");
//        params13.put("city", "Kiev");
        params13.put("age", null);

        final Map<String, String> params14 = new HashMap<>();
//        params14.put("name", "Ivanov");
        params14.put("country", "Ukraine");
        params14.put("city", "Kiev");
//        params14.put("age", "7");

        final Map<String, String> params15 = new HashMap<>();
//        params15.put("name", "Ivanov");
        params15.put("country", "Ukraine");
//        params15.put("city", "Kiev");
        params15.put("age", "7");

        final Map<String, String> params16 = new HashMap<>();
//        params16.put("name", "Ivanov");
//        params16.put("country", "Ukraine");
        params16.put("city", "Kiev");
        params16.put("age", "7");

        final Map<String, String> params17 = new HashMap<>();
        params17.put("name", "Ivanov");
//        params17.put("country", "Ukraine");
//        params17.put("city", "Kiev");
//        params17.put("age", "7");

        final Map<String, String> params18 = new HashMap<>();
//        params18.put("name", "Ivanov");
        params18.put("country", "Ukraine");
//        params18.put("city", "Kiev");
//        params18.put("age", "7");

        final Map<String, String> params19 = new HashMap<>();
//        params19.put("name", "Ivanov");
//        params19.put("country", "Ukraine");
        params19.put("city", "Kiev");
//        params19.put("age", "7");

        final Map<String, String> params20 = new HashMap<>();
//        params20.put("name", "Ivanov");
//        params20.put("country", "Ukraine");
//        params20.put("city", "Kiev");
        params20.put("age", "7");

        final Map<String, String> params21 = new HashMap<>();
//        params21.put("name", "Ivanov");
//        params21.put("country", "Ukraine");
//        params21.put("city", "Kiev");
//        params21.put("age", "7");

        final Map<String, String> params22 = new HashMap<>();
        params22.put("a", "Ivanov");
        params22.put("b", "Ukraine");
        params22.put("c", "Kiev");
        params22.put("d", "7");

        final Map<String, String> params23 = new HashMap<>();
        params23.put("a", "Ivanov");
        params23.put("b", "Ukraine");
        params23.put("c", "Kiev");
        params23.put("d", "7");
        params23.put("e", "Ivanov");
        params23.put("f", "Ukraine");
        params23.put("g", "Kiev");
        params23.put("h", "7");

        return Arrays.asList(params1, params2, params3, params4, params5, params6, params7, params8,
                        params9, params10, params11, params12, params13, params14, params15, params16,
                        params17, params18, params19, params20, params21, params22, params23)
                .toArray(new Map[0]);
    }
}
