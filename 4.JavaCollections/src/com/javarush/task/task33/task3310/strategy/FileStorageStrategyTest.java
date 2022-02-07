//package com.javarush.task.task33.task3310.strategy;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
//class FileStorageStrategyTest {
//
//    @Test
//    void getReduce() {
//        final Entry entry1 = new Entry(1, 1L, "1", null);
//        final Entry entry2 = new Entry(2, 2L, "2", null);
//        final Entry entry3 = new Entry(3, 3L, "3", null);
//        final List<Entry> entryList = Arrays.asList(entry1, entry2, entry3);
//
//        final Entry reduced = FileStorageStrategy.getReduce(entryList);
//        final Entry expected = new Entry(entry3.hash, entry3.key, entry3.value,
//                new Entry(entry2.hash, entry2.key, entry2.value,
//                        new Entry(entry1.hash, entry1.key, entry1.value, null)));
//
//        assertEquals(expected, reduced);
//    }
//
//    @Test
//    void containsKey() {
//    }
//
//    @Test
//    void containsValue() {
//    }
//
//    @Test
//    void getKey() {
//    }
//
//    @Test
//    void put() {
//    }
//
//    @Test
//    void getValue() {
//    }
//
//    @Test
//    void hash() {
//    }
//
//    @Test
//    void indexFor() {
//    }
//
//    @Test
//    void getEntry() {
//    }
//
//    @Test
//    void resize() {
//    }
//
//    @Test
//    void transfer() {
//    }
//
//    @Test
//    void addEntry() {
//    }
//}