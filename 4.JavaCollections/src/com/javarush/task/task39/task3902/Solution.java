package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("The entered number has " + result + "ones");

    }

    public static boolean isWeightEven(long number) {
        return (bitCount(number) & 1) == 0;
    }

    private static int bitCount(long number) {
        final int rightShift = 3 * 16;
        return bitCount((int) (number >>> rightShift))
                + bitCount((int) ((number << 16) >>> rightShift))
                + bitCount((int) ((number << 2 * 16) >>> rightShift))
                + bitCount((int) ((number << 3 * 16) >>> rightShift));
    }

    /**
     * @author MIT HACKMEM
     * @link: https://web.archive.org/web/20151229003112/http://blogs.msdn.com/b/jeuge/archive/2005/06/08/hakmem-bit-count.aspx
     * */
    private static int bitCount(int u) {
        int uCount;

        uCount = u - ((u >> 1) & 033333333333) - ((u >> 2) & 011111111111);
        return ((uCount + (uCount >> 3)) & 030707070707) % 63;
    }
}
