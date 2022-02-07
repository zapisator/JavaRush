package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static AtomicInteger priorityCounter = new AtomicInteger(0);

    public MyThread() {
        setPriorityCounter();
    }

    public MyThread(Runnable target) {
        super(target);
        setPriorityCounter();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriorityCounter(group);
    }

    public MyThread(String name) {
        super(name);
        setPriorityCounter();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriorityCounter(group);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriorityCounter();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriorityCounter(group);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriorityCounter(group);
    }

    private void setPriorityCounter(ThreadGroup... groups) {
//        final int priority = priorityCounter.get() % MAX_PRIORITY + 1;
//
//        if (groups.length == 0) {
//            this.setPriority(priority);
//        } else {
//            this.setPriority(Math.min(priority, groups[0].getMaxPriority()));
//        }
        this.setPriority(priorityCounter.get() % MAX_PRIORITY + 1);
        priorityCounter.incrementAndGet();
    }

}
