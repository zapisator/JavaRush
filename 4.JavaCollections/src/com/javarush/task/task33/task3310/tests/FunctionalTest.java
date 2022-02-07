package com.javarush.task.task33.task3310.tests;

import static com.javarush.task.task33.task3310.Helper.generateRandomString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.DualHashBidiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.FileStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        final String one = generateRandomString();
        final String two = generateRandomString();
        final String three = one;

        final Long oneId = shortener.getId(one);
        final Long twoId = shortener.getId(two);
        final Long threeId = shortener.getId(three);

        final String oneById = shortener.getString(oneId);
        final String twoById = shortener.getString(twoId);
        final String threeById = shortener.getString(threeId);

        assertNotEquals(twoId, oneId);
        assertNotEquals(twoId, threeId);
        assertEquals(oneId, threeId);

        assertEquals(oneById, one);
        assertEquals(twoById, two);
        assertEquals(threeById, three);
    }

    @Test
    public void testHashMapStorageStrategy() {
        final Shortener shortener = new Shortener(new HashMapStorageStrategy());

        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        final Shortener shortener = new Shortener(new OurHashMapStorageStrategy());

        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        final Shortener shortener = new Shortener(new FileStorageStrategy());

        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        final Shortener shortener = new Shortener(new HashBiMapStorageStrategy());

        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        final Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());

        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        final Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());

        testStorage(shortener);
    }

}
