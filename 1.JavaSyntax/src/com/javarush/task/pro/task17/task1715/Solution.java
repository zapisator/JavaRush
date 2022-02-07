package com.javarush.task.pro.task17.task1715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Вилларибо и Виллабаджо.
*/

public class Solution {
    static List<MusicalInstrument> orchestra = new ArrayList<>();

    public static void main(String[] args) {
        createKeyboardOrchestra();
        createStringedOrchestra();
        playOrchestra();
    }

    public static void createKeyboardOrchestra() {
        orchestra.addAll(Arrays.asList(new Organ(), new Piano(), new Piano(), new Piano()));
    }

    public static void createStringedOrchestra() {
        orchestra.addAll(Arrays.asList(new Violin(), new Violin(), new Guitar()));
    }

    public static void playOrchestra() {
//        for (var instrument : orchestra) {
//            instrument.play();
//        }
    }
}
