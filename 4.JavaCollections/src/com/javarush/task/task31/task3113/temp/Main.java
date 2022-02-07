package com.javarush.task.task31.task3113.temp;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3113/1.c"));
        Map<String, Integer> map = new HashMap<>();
        ArrayList<Character> toSort = new ArrayList<>();
        String tmp = r.readLine();

        while (tmp != null) {
            ArrayList<String> al = new ArrayList<>(Arrays.asList(tmp.split("")));
            for (String a : al) {
                if (map.containsKey(a))
                    map.put(a, map.get(a) + 1);
                else {
                    map.put(a, 1);
                    toSort.add(a.charAt(0));
                }
            }
            tmp = r.readLine();
        }

        Collections.sort(toSort);

        for (Character name : toSort) {
            System.out.println(name + " " + map.get(name + ""));
        }

        r.close();

        System.out.println();
    }
}
