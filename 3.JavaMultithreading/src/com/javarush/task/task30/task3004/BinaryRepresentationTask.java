package com.javarush.task.task30.task3004;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class BinaryRepresentationTask extends RecursiveTask<String> {

    int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {

        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);

        if (b > 0) {
            return new BinaryRepresentationTask(b).fork().join() + result;
        } else {
            return result;
        }
    }
}
