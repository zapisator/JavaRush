package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* 
Машину на СТО не повезем!
*/

public class Solution {

    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {

        protected List<Wheel> wheels;

        public Car() {
            wheels = new LinkedList<>();
            Arrays.stream(claimSize4(loadWheelNamesFromDB()))
                    .map(Wheel::valueOf)
//                    .distinct()
                    .forEach(wheels::add);
        }

        private String[] claimSize4(String[] wheelNames) {
            if (wheelNames.length != 4) {
                throw new RuntimeException();
            }
            return wheelNames;
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        final Car car = new Car();

        car.wheels.forEach(System.out::println);
    }
}
