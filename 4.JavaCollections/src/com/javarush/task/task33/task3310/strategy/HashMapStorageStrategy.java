package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map.Entry;

public class HashMapStorageStrategy implements StorageStrategy {

    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return data.entrySet()
                .stream()
                .filter(longStringEntry -> longStringEntry.getValue().equals(value))
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
