package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        AtomicBoolean doBlockO1 = new AtomicBoolean(true);
        AtomicBoolean doBlockO2 = new AtomicBoolean(true);

        final Thread blockerOfO1 = new Thread(() -> {
//            System.out.println("o1 is blocked");
            synchronized (o1) {
                while (doBlockO1.get());
            }
//            System.out.println("blockerO1 ended. o1 released");
        });
        final Thread blockerOfO2 = new Thread(() -> {
//            System.out.println("o2 is blocked");
            synchronized (o2) {
                while (doBlockO2.get());
            }
//            System.out.println("blockerO2 ended. o2 released");
        });
        blockerOfO1.start();
        blockerOfO2.start();

        while (blockerOfO1.getState() != State.RUNNABLE || blockerOfO2.getState() != State.RUNNABLE);
        final Thread normal = new Thread(() -> {
            solution.someMethodWithSynchronizedBlocks(o1, o2);
//            System.out.println("normal ended");
        });
        normal.start();

        while (normal.getState() != State.BLOCKED);
        doBlockO1.set(false);


        while (blockerOfO1.getState() != State.TERMINATED) {}

        final Thread blockerOfO1Another1 = new Thread(() -> {
            synchronized (o1) {
                int a;
            }
//            System.out.println("blockerOfO1Another1 ended.");
        });
        blockerOfO1Another1.start();

        boolean blocked = false;
        boolean terminated = false;
        while (!blocked && !terminated) {
            blocked = blockerOfO1Another1.getState() == State.BLOCKED;
            terminated = blockerOfO1Another1.getState() == State.TERMINATED;
        }

        doBlockO2.set(false);

        return blocked;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }

}
