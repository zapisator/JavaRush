package com.javarush.task.task37.task3714;

import static com.javarush.task.task37.task3714.Solution.RomanToIntegerCharacters.rtoi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Input a roman number to be converted to decimal: ");
//        String romanString = bufferedReader.readLine();
//        System.out.println("Conversion result equals " + pairs(romanString));
        test();
    }

    public static int romanToInteger(String s) {
        int result = 0;
        int previous = 0;

        for (char c : prepared(s)) {
            final int current = rtoi(c);

            result += current < previous ? -current : +current;
            previous = current;
        }
        return result;
    }

    private static char[] prepared(String s) {
        return new StringBuilder(s)
                .reverse()
                .toString()
                .toLowerCase(Locale.ROOT)
                .toCharArray();
    }

    private static void test() {
        Object[][] tests = new Object[][]{
                {1, "I"},
                {2, "II"},
                {3, "III"},
                {4, "IV"},
                {5, "V"},
                {9, "IX"},
                {12, "XII"},
                {16, "XVI"},
                {29, "XXIX"},
                {44, "XLIV"},
                {45, "XLV"},
                {68, "LXVIII"},
                {83, "LXXXIII"},
                {97, "XCVII"},
                {99, "XCIX"},
                {500, "D"},
                {501, "DI"},
                {649, "DCXLIX"},
                {798, "DCCXCVIII"},
                {891, "DCCCXCI"},
                {1000, "M"},
                {1004, "MIV"},
                {1006, "MVI"},
                {1023, "MXXIII"},
                {2014, "MMXIV"},
                {3999, "MMMCMXCIX"}
        };

        Arrays.stream(tests)
                .forEach(objects -> {
                            final int expected = (Integer) objects[0];
                            final String input = (String) objects[1];
                            final int real = romanToInteger(input);

                            System.out.printf(
                                    "input: %10s\t\texpected: %5s\t\treal: %5s\t\tTest: %5s%n",
                                    input, expected, real, expected == real);
                        }
                );
    }

    protected static class RomanToIntegerCharacters {

        private static final Map<Character, Integer> pairs = new HashMap<>();

        static {
            pairs.put('i', 1);
            pairs.put('x', 10);
            pairs.put('c', 100);
            pairs.put('m', 1000);
            pairs.put('v', 5);
            pairs.put('l', 50);
            pairs.put('d', 500);
        }

        public static int rtoi(char character) {
            return pairs.get(character);
        }
    }

}
