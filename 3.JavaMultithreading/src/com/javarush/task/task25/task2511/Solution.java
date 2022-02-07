package com.javarush.task.task25.task2511;

import java.util.Collections;
import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/

public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = (Thread thread, Throwable throwable) -> {
            System.out.println(
                    throwable.getMessage().replaceAll(
                            thread.getName(),
                            String.join(
                                    "",
                                    Collections.nCopies(thread.getName().length(), "*")
                            )
                    )
            );
        };    //init handler here
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        });

        solution.run();
    }
}
