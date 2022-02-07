package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(4d, "4d");
        labels.put(5d, "5d");
        labels.put(6d, "6d");
        labels.put(7d, "7d");
        labels.put(8d, "8d");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
