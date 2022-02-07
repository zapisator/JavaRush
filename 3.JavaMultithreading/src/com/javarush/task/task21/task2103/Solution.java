package com.javarush.task.task21.task2103;

/* 
Все гениальное - просто!
*/

import java.util.Arrays;

public class Solution {
    public static boolean calculateReference(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
    }

    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return c;
    }

    public static void main(String[] args) {
        final boolean[] booleans = {false, true};
        final boolean[] reference = new boolean[16];
        final boolean[] values = new boolean[16];

        int i = 0;
        for (boolean a : booleans) {
            for (boolean b : booleans) {
                for (boolean c : booleans) {
                    for (boolean d : booleans) {
                        reference[i] = calculateReference(a, b, c, d);
                        System.out.printf(
                                "a:%5b b:%5b c:5%b d:5%b reference:%5s\n",
                                a, b, c, d, reference[i]);
                        i++;
                    }
                }
            }
        }

        i = 0;
        for (boolean a : booleans) {
            for (boolean b : booleans) {
                for (boolean c : booleans) {
                    for (boolean d : booleans) {
                        values[i] = calculate(a, b, c, d);
                        System.out.printf(
                                "a:%5b b:%5b c:5%b d:5%b reference:%5s\n",
                                a, b, c, d, values[i]);
                        i++;
                    }
                }
            }
        }

        System.out.println(Arrays.equals(reference, values));
    }
}
