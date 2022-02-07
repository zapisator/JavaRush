package com.javarush.task.task16.task1632;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.addAll(Arrays.asList(new Eternal(), new Interrupted(), new Hurray(), new MessageImpl(), new ConsoleReader()));
    }

    public static void main(String[] args) {
    }

    public static class Eternal extends Thread {

        @Override
        public void run() {
            while(true){

            }
        }
    }

    public static class Interrupted extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Hurray extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static class MessageImpl extends Thread implements Message {

        @Override
        public void run() {
            while (!this.isInterrupted()) {
            }
        }

        @Override
        public void showWarning() {
            this.interrupt();
        }
    }

    public static class ConsoleReader extends Thread {

        @Override
        public void run() {
            final int N = n();
            System.out.println(N);
        }

        private int n() {
            int n = 0;

            try (final Scanner scanner = new Scanner(System.in)) {
                for (String line = scanner.nextLine(); !line.equals("N"); line = scanner.nextLine() ) {
                    n += Integer.parseInt(line);
                }
            }
            return n;
        }
    }
}