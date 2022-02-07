package com.javarush.task.task36.task3610;

import java.util.Map;

/* 
MyMultiMap
*/

public class Solution {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new MyMultiMap<>(3);
        Map<Integer, Integer> map2 = new MyMultiMap2<>(3);

//        System.out.println(map.put(5, 56));
//        System.out.println(map.put(5, 57));



        for (int i = 0; i < 7; i++) {
            map.put(i, i);
            map2.put(i, i);
        }
        map.put(5, 56);
        map2.put(5, 56);

        map.put(5, 57);
        map2.put(5, 57);

        System.out.println(map.put(5, 58));             // Expected: 57
        System.out.println(map2.put(5, 58));

        System.out.println(map);                        // Expected: {0=0, 1=1, 2=2, 3=3, 4=4, 5=56, 57, 58, 6=6}
        System.out.println(map2);                        // Expected: {0=0, 1=1, 2=2, 3=3, 4=4, 5=56, 57, 58, 6=6}

        System.out.println(map.size());                 // Expected: size = 9
        System.out.println(map2.size());

        System.out.println(map.remove(5));         // Expected: 56
        System.out.println(map2.remove(5));
        System.out.println(map);                        // Expected: {0=0, 1=1, 2=2, 3=3, 4=4, 5=57, 58, 6=6}
        System.out.println(map2);

        System.out.println(map.size());                 // Expected: size = 8
        System.out.println(map2.size());

        System.out.println(map.keySet());               // Expected: [0, 1, 2, 3, 4, 5, 6]
        System.out.println(map2.keySet());
        System.out.println(map.values());               // Expected: [0, 1, 2, 3, 4, 57, 58, 6]
        System.out.println(map2.values());

        System.out.println(map.containsKey(5));         // Expected: true
        System.out.println(map2.containsKey(5));

        System.out.println(map.containsValue(57));      // Expected: true
        System.out.println(map2.containsValue(57));

        System.out.println(map.containsValue(7));       // Expected: false
        System.out.println(map2.containsValue(7));

        System.out.println(map.remove(5));
        System.out.println(map2.remove(5));

        System.out.println(map.remove(5));
        System.out.println(map2.remove(5));

        System.out.println(map);
        System.out.println(map2);

        for (int i = 0; i < 4; i++) {
            System.out.println(map.put(7, null));
            System.out.println(map2.put(7, null));
        }

        System.out.println(map);
        System.out.println(map2);



        System.out.println(map.remove(7));
        System.out.println(map2.remove(7));

        System.out.println(map.remove(7));
        System.out.println(map2.remove(7));

        System.out.println(map);
        System.out.println(map);


    }
}