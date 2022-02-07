package com.javarush.task.task26.task2610;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Stream.iterate(0, index -> index + 1)
                .limit(Integer.MAX_VALUE)
                .forEach(index -> {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
