package com.javarush.task.task35.task3512;

public class Generator<T> {

    final Class<T> tClass;

    public Generator(Class<T> tClass) {
        this.tClass = tClass;
    }

    T newInstance() {
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
