package com.javarush.task.task16.task1605;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/* 
Поговорим о музыке
*/

public class Solution {
    public static int delay = 1000;

    public static void main(String[] args) {
        Thread violin = new Thread(new Violin("Player"));
        violin.start();
    }

    public static void sleepNSeconds(int n) {
        try {
            Thread.sleep(n * delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface MusicalInstrument extends Runnable {
        Date startPlaying();

        Date stopPlaying();
    }

    public static class Violin implements MusicalInstrument {
        private String owner;

        public Violin(String owner) {
            this.owner = owner;
        }

        @Override
        public Date startPlaying() {
            System.out.println(this.owner + " is starting to play");
            return new Date();
        }

        @Override
        public Date stopPlaying() {
            System.out.println(this.owner + " is stopping playing");
            return new Date();
        }

        @Override
        public void run() {
            final long begin = startPlaying().getTime();
            Solution.sleepNSeconds(1);
            final long stop = stopPlaying().getTime();
            System.out.printf("Playing %d ms\n", (stop - begin));

        }
    }
}
