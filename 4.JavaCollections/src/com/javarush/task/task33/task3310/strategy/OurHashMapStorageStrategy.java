package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;
import java.util.Optional;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null) {
            return containsNullValue();
        }

        final Entry[] tab = table;

        for (Entry entry : tab) {
            for (Entry e = entry; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsNullValue() {
        final Entry[] tab = table;

        for (Entry entry : tab) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.value == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Long getKey(String value) {
        if (value == null) {
            return getKeyOfNullValue();
        }

        final Entry[] tab = table;

        for (Entry entry : tab) {
            for (Entry e = entry; e != null; e = e.next) {
                if (value.equals(e.getValue())) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    private Long getKeyOfNullValue() {
        final Entry[] tab = table;

        for (Entry entry : tab) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.value == null) {
                    return e.key;
                }
            }
        }
        return null;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null){
            return;
        }

        final int hash = hash((long) key.hashCode());
        final int i = indexFor(hash, table.length);

        for (Entry e = table[i]; e != null; e = e.next) {
            final Long k = e.key;

            if (e.hash == hash && Objects.equals(key, k)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, i);
    }


    @Override
    public String getValue(Long key) {
        return Optional.ofNullable(getEntry(key))
                .orElseGet(() -> new Entry(0, 0L, null, null))
                .getValue();
    }

    public int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);

        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        final int hash = (key == null) ? 0 : hash(((long) key.hashCode()));

        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            final Long k = e.key;

            if (e.hash == hash && Objects.equals(k, key))
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
        final int maximumCapacity = 1 << 30;
        final Entry[] oldTable = table;
        final int oldCapacity = oldTable.length;

        if (oldCapacity == maximumCapacity) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        final Entry[] newTable = new Entry[newCapacity];

        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        final Entry[] src = table;
        final int newCapacity = newTable.length;

        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];

            if (e != null) {
                src[j] = null;
                do {
                    final Entry next = e.next;
                    final int i = indexFor(e.hash, newCapacity);

                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        final Entry e = table[bucketIndex];

        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold){
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        final Entry e = table[bucketIndex];

        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

}
