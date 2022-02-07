package com.javarush.task.task17.task1714;

/* 
Comparable
*/

import java.util.Arrays;
import java.util.List;

public class Beach implements Comparable<Beach>{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {
        final List<Beach> beaches = Arrays.asList(
                new Beach("badQualityDistantBeach", 10f, 1),
                new Beach("badQualityNearbyBeach", 1f, 1),
                new Beach("bestQualityDistantBeach", 10f, 10),
                new Beach("bestQualityNearbyBeach", 1f, 10)
        );

        for (int i = 0; i < beaches.size(); i++) {
            final Beach thisBeach = beaches.get(i);
            beaches.forEach(beach -> {
                System.out.println(
                        beach.getName()
                                + " distance: " + beach.getDistance()
                                + " quality: " + beach.getQuality()
                                + "\nis compared to:\n"
                                + thisBeach.getName()
                                + " distance: " + thisBeach.getDistance()
                                + " quality: " + thisBeach.getQuality()
                                + "\n"
                                + "result: " + beach.compareTo(thisBeach)
                                + "\n"
                );
            });
        }
    }

    @Override
    public synchronized int compareTo(Beach o) {
        return Integer.compare(this.getQuality(), o.getQuality()) + Float.compare(o.getDistance(), this.getDistance());
    }
}
