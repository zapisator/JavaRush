package com.javarush.task.task25.task2508;

import java.lang.Thread.State;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    private Thread thread;

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
//            if (thread.getState() == State.RUNNABLE) {
                System.out.println(thread.getName());
//            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
