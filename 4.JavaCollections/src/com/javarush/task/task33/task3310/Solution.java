package com.javarush.task.task33.task3310;

import static com.javarush.task.task33.task3310.Helper.printMessage;

import com.javarush.task.task33.task3310.strategy.DualHashBidiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        final long numberOfRepetitions = 10_000L;

        for (int i = 0; i < 10; i++) {
            testStrategy(new HashMapStorageStrategy(), numberOfRepetitions);
            printMessage("\n");
//            testStrategy(new OurHashMapStorageStrategy(), numberOfRepetitions);
//            printMessage("\n");
//            testStrategy(new FileStorageStrategy(), numberOfRepetitions);
//            printMessage("\n");
//            testStrategy(new FileStorageStrategyMy(), numberOfRepetitions);
//            printMessage("\n");
//            testStrategy(new OurHashBiMapStorageStrategy(), numberOfRepetitions);
//            printMessage("\n");
            testStrategy(new HashBiMapStorageStrategy(), numberOfRepetitions);
            printMessage("\n");
//            testStrategy(new DualHashBidiMapStorageStrategy(), numberOfRepetitions);
//            printMessage("\n");
            printMessage("=== === === === === ===\n");
        }
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream()
                .map(shortener::getId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream()
                .map(shortener::getString)
                .collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        final Shortener shortener = new Shortener(strategy);

        printMessage(strategy.getClass().getSimpleName());
        final Set<String> stringsBefore = Stream.generate(Helper::generateRandomString)
                .limit(elementsNumber)
                .collect(Collectors.toSet());

        final Date timeToGetIdsStart = new Date();
        final Set<Long> keys = getIds(shortener, stringsBefore);
        final Date timeToGetIdsEnd = new Date();
        printMessage(timeToGetIdsEnd.getTime() - timeToGetIdsStart.getTime());

        final Date timeToGetStringsStart = new Date();
        final Set<String> stringsAfter = getStrings(shortener, keys);
        final Date timeToGetStringsEnd = new Date();
        printMessage(timeToGetStringsEnd.getTime() - timeToGetStringsStart.getTime());

        printMessage(stringsBefore.equals(stringsAfter) ? "Тест пройден." : "Тест не пройден.");
    }

}
