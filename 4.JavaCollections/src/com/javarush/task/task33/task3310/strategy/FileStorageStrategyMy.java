package com.javarush.task.task33.task3310.strategy;

import static com.javarush.task.task33.task3310.strategy.FileBucket.createAndPut;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javafx.util.Pair;

public class FileStorageStrategyMy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10_000L;

    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize = 1_000_000L;

    public static Entry getReduce(List<Entry> integerListEntry) {
        return integerListEntry
                .stream()
                .reduce(
                        null,
                        (subtotal, e) -> new Entry(e.hash, e.key, e.value, subtotal)
                );
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        final int hash = (key == null) ? 0 : hash(((long) key.hashCode()));
        final FileBucket bucket = getBucket(indexFor(hash, table.length));

        Entry e = bucket.getEntry();

        for (; e != null; e = e.next) {
            final Long k = e.key;

            if (e.hash == hash && Objects.equals(k, key)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null) {
            return;
        }

        final int hash = hash((long) key.hashCode());
        final int i = indexFor(hash, table.length);
        final FileBucket bucket = table[i];

        if (bucket != null) {
            for (Entry entry = bucket.getEntry(); entry != null; entry = entry.next) {
                if (entry.hash == hash && entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
            }
        }
        addEntry(hash, key, value, i);
    }

    void resize(int newBucketSizeLimit) {
        final long oldBucketSizeLimit = bucketSizeLimit;

        if (oldBucketSizeLimit == maxBucketSize) {
            throw new RuntimeException("Maximum size limit for bucket has exceeded.");
        }

        final FileBucket[] newTable = new FileBucket[newBucketSizeLimit];

        transfer(newTable);
        table = newTable;
        bucketSizeLimit *= 2;
    }

    void transfer(FileBucket[] newTable) {
        Arrays.stream(table)
                .filter(Objects::nonNull)
                .map(this::extractEntryAndRemove)
                .map(this::allNextEntries)
                .flatMap(Collection::stream)
                .collect(groupingBy(entry -> indexFor(hash(entry.key), newTable.length)))
                .entrySet()
                .stream()
                .map(e -> new Pair<>(e.getKey(), createAndPut(e.getValue())))
                .forEach(pair -> newTable[pair.getKey()] = pair.getValue());
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null) {
            return containsNullValue();
        }

        final FileBucket[] tab = table;

        for (FileBucket bucket : tab) {
            if (bucket != null) {
                for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                    if (value.equals(e.value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getValue(Long key) {
        return Optional.ofNullable(getEntry(key))
                .orElseGet(() -> new Entry(0, 0L, null, null))
                .getValue();
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public Long getKey(String value) {
        if (value == null) {
            return getKeyOfNullValue();
        }

        final FileBucket[] tab = table;

        for (FileBucket bucket : tab) {
            if (bucket != null) {
                for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                    if (value.equals(e.getValue())) {
                        return e.getKey();
                    }
                }
            }
        }
        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        final FileBucket bucket = getBucket(bucketIndex);
        final Entry newEntry = new Entry(hash, key, value, bucket.getEntry());

        bucket.remove();
        bucket.putEntry(newEntry);
        table[bucketIndex] = bucket;
        if (bucket.getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;

        long currentBucketSize = table[bucketIndex].getFileSize();
        if (currentBucketSize > maxBucketSize)
            maxBucketSize = currentBucketSize;
    }

    private boolean containsNullValue() {
        final FileBucket[] tab = table;

        for (FileBucket bucket : tab) {
            if (bucket != null) {
                for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                    if (e.value == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Long getKeyOfNullValue() {
        final FileBucket[] tab = table;

        for (FileBucket bucket : tab) {
            if (bucket != null) {
                for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                    if (e.value == null) {
                        return e.key;
                    }
                }
            }
        }
        return null;
    }


    public int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);

        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    private FileBucket getBucket(int bucketIndex) {
        return Optional
                .ofNullable(table[bucketIndex])
                .orElseGet(FileBucket::new);
    }

    private Entry extractEntryAndRemove(FileBucket fileBucket) {
        final Entry entry = fileBucket.getEntry();

        fileBucket.remove();
        return entry;
    }

    private List<Entry> allNextEntries(Entry entry) {
        final List<Entry> entries = new LinkedList<>();

        for (Entry e = entry; e != null; e = e.next) {
            entries.add(e);
        }
        return entries;
    }

}
