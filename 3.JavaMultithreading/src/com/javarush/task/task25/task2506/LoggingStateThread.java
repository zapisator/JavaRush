package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread supervised;
    private State state;

    public LoggingStateThread(Thread supervised) {
        super();
        this.supervised = supervised;
        this.setDaemon(true);
        state = supervised.getState();
    }

    @Override
    public void run() {
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != supervised.getState()) {
                state = supervised.getState();
                System.out.println(state);
            }
        }
    }
}
