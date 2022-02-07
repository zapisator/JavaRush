package com.javarush.task.pro.task13.task1312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* 
ArrayList vs HashMap
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(getProgrammingLanguages());
    }

    public static HashMap<Integer, String> getProgrammingLanguages() {
        final ArrayList<String> programmingLanguages = programmingLanguages();
        final HashMap<Integer, String> languages = new HashMap<>();

        for (int i = 0; i < programmingLanguages.size(); i++) {
            languages.put(i, programmingLanguages.get(i));
        }
        return languages;
    }

    private static ArrayList<String> programmingLanguages() {
        return new ArrayList<>(Arrays.asList(
                "Java",
                "Kotlin",
                "Go",
                "Javascript",
                "Typescript",
                "Python",
                "PHP",
                "C++"
        ));
    }

}
