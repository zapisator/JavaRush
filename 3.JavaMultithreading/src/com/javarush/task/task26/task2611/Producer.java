package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Producer implements Runnable {

    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Stream.iterate(1, key -> key + 1)
                .limit(Integer.MAX_VALUE)
                .forEach(key -> {
                    map.put(String.valueOf(key), "Some text for " + key);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName()
                                + " thread was terminated");
                    }
                });
        } catch (Exception ignore) {
        }
    }
}
