package com.javarush.task.task35.task3509;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.util.Pair;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        return Arrays.stream(elements)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        return Arrays.stream(elements)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }
        return IntStream.range(0, keys.size())
                .mapToObj(i -> new Pair<>(keys.get(i), values.get(i)))
                .collect(Collectors.toMap(
                        Pair::getKey, Pair::getValue, (obj1, obj2) -> obj2, HashMap::new)
                );
    }
}
