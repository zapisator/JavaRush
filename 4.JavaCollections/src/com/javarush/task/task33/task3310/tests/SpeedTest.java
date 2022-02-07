package com.javarush.task.task33.task3310.tests;

import static com.javarush.task.task33.task3310.Solution.getIds;
import static com.javarush.task.task33.task3310.Solution.getStrings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class SpeedTest {

    @Test
    public void testHashMapStorage() {
        final Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        final Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        final Set<String> origStrings = Stream.generate(Helper::generateRandomString)
                .limit(10_000)
                .collect(Collectors.toSet());

        final Set<Long> ids1 = new HashSet<>();
        final Set<Long> ids2 = new HashSet<>();
        final float timeToGetIds1 = getTimeToGetIds(shortener1, origStrings, ids1);
        final float timeToGetIds2 = getTimeToGetIds(shortener2, origStrings, ids2);
        final Set<String> strings1 = new HashSet<>();
        final Set<String> strings2 = new HashSet<>();
        final float timeToGetStrings1 = getTimeToGetStrings(shortener1, ids1, strings1);
        final float timeToGetStrings2 = getTimeToGetStrings(shortener2, ids2, strings2);

//        assertEquals(origStrings, strings1);
//        assertEquals(origStrings, strings2);
//        assertNotEquals(timeToGetIds1, timeToGetIds2, 30);
        assertTrue(timeToGetIds1 > timeToGetIds2);
        assertEquals(timeToGetStrings1, timeToGetStrings2, 30);
    }

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        final Date start = new Date();
        final Set<Long> idsToAdd = getIds(shortener, strings);
        final Date end = new Date();

        ids.addAll(idsToAdd);
        return (end.getTime() - start.getTime());
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        final Date start = new Date();
        final Set<String> stringsToAdd = getStrings(shortener, ids);
        final Date end = new Date();

        strings.addAll(stringsToAdd);
        return end.getTime() - start.getTime();
    }

}
