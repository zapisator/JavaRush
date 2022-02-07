package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {

    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return (int) map
                .values()
                .stream()
                .mapToLong(List::size)
                .sum();
    }

    @Override
    public V put(K key, V value) {
        final List<V> values = map.getOrDefault(key, new ArrayList<>());
        final V previous = values.isEmpty() ? null : values.get(values.size() - 1);

        map.remove(key);
        if (values.size() == repeatCount) {
            values.remove(0);
        }
        values.add(value);
        map.put(key, values);
        return previous;
    }

    @Override
    public V remove(Object key) {
        final List<V> values = map.getOrDefault((K) key, new ArrayList<>());
        final V result = values.isEmpty() ? null : values.get(0);

        map.remove(key);
        if (!values.isEmpty()) {
            values.remove(0);
        }
        if (!values.isEmpty()) {
            map.put((K) key, values);
        }
        return result;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.values()
                .stream()
                .anyMatch(vs -> vs.contains(value));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }

}