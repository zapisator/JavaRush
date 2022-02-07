package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/

public class Solution {

    public static void processThreads(Thread... threads) {
        for (Thread thread : threads) {
            switch (thread.getState()) {
                case NEW:
                    thread.start();
                    break;
                case BLOCKED:
                case TIMED_WAITING:
                case WAITING:
                    thread.interrupt();
                    break;
                case RUNNABLE:
                    thread.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(thread.getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        final Thread[] threads = new Thread[]{new Thread()};
//
//        for (int i = 0; i < 10; i++) {
//            processThreads(threads);
//            Thread.sleep(200);
//            threads[0].interrupt();
//        }

    }
}
