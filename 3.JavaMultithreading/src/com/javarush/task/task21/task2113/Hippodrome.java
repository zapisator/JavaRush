package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException {
        final List<Horse> horses = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            final Horse horse = new Horse("Horse" + i, 3, 0);
            horses.add(horse);
        }
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    void move() {
        for (final Horse horse : horses) {
            horse.move();
        }
    }

    void print() {
        for (final Horse horse : horses) {
            horse.print();
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append('\n');
        }
        System.out.println(builder);
    }

    public Horse getWinner() {
        return horses.stream()
                .min((horse1, horse2) -> Double.compare(horse2.getDistance(), horse1.getDistance()))
                .orElseThrow(RuntimeException::new);
    }

    public void printWinner() {
        System.out.printf("Winner is %s!\n", getWinner().getName());
    }

}
