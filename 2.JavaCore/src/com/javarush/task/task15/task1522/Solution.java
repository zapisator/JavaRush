package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(thePlanet.getClass().getSimpleName());
    }

    public static Planet thePlanet;

    //add static block here - добавьте статический блок тут
    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void readKeyFromConsoleAndInitPlanet() {
        thePlanet = planet();
    }

    private static Planet planet() {
        final String planetName = planetName();
        Planet planet = null;

        switch (planetName) {
            case Planet.EARTH:
                planet = Earth.getInstance();
                break;
            case Planet.MOON:
                planet = Moon.getInstance();
                break;
            case Planet.SUN:
                planet = Sun.getInstance();
                break;
        }
        return planet;
    }

    private static String planetName() {
        String planetName = "";

        try (final Scanner scanner = new Scanner(System.in)) {
            planetName = scanner.nextLine();
        }
        return planetName;
    }
}
