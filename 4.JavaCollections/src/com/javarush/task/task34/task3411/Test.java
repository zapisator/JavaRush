package com.javarush.task.task34.task3411;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;

public class Test {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final LinkedList<Integer> A = new LinkedList<>();
    private static final LinkedList<Integer> B = new LinkedList<>();
    private static final LinkedList<Integer> C = new LinkedList<>();

    private static boolean doVisualize = false;

    public static void main(String[] args) {
        test(20, false);
    }

    private static void test(int numRings, boolean doVisualize) {
        preparePyramids(numRings);
        Test.doVisualize = doVisualize;
        printResults(numRings, commands(numRings));
    }

    private static void preparePyramids(int numRings) {
        A.clear();
        B.clear();
        C.clear();
        for (int i = numRings; i > 0; i--) {
            A.addFirst(i);
        }
    }

    private static String[] commands(int numRings) {
        final PrintStream out = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        System.setOut(new PrintStream(buffer));
        Solution.moveRing('A', 'B', 'C', numRings);
        System.setOut(out);
        return buffer.toString().split("\n");
    }

    private static void printResults(int numRings, String[] commands) {
        moveAndVisualize(commands);
        printTable(numRings, commands);
    }

    private static void moveAndVisualize(String[] commands) {
        Arrays.stream(commands)
                .forEach(line -> {
                    final String[] words = line.split(" ");
                    final char from = words[1].charAt(0);
                    final char to = words[3].charAt(0);

                    move(from, to);
                    if (doVisualize) {
                        print();
                    }
                });
    }

    private static void move(char from, char to) {
        pyramid(to).addFirst(pyramid(from).pop());
    }

    private static LinkedList<Integer> pyramid(char from) {
        switch (from) {
            case 'A':
                return A;
            case 'B':
                return B;
            case 'C':
                return C;
            default:
                throw new RuntimeException("No such pyramid.");
        }
    }

    private static void print() {
        for (int i = Math.max(A.size(), Math.max(B.size(), C.size())) - 1; i >= 0; i--) {
            System.out.printf("%3s %3s %3s\n",
                    A.size() > i ? A.get(A.size() - i - 1).toString() : " ",
                    B.size() > i ? B.get(B.size() - i - 1).toString() : " ",
                    C.size() > i ? C.get(C.size() - i - 1).toString() : " ");
        }
        System.out.println("===_===_===\n");
    }

    private static void printTable(int numRings, String[] commands) {
        final int expectedCommandsCount = (int) Math.pow(2, numRings) - 1;
        final String testResult = testResult(numRings, commands, expectedCommandsCount);

        System.out.printf(
                "| numRings | op-s exp | op-s obt | result |\n"
                + "| %8s | %8s | %8s | %8s |\n\n",
                numRings,
                expectedCommandsCount, commands.length,
                testResult
        );
    }

    private static String testResult(int numRings, String[] commands, int expectedCommandsCount) {
        return ((expectedCommandsCount == commands.length
                && B.size() == numRings
                && isSorted())
                ? ANSI_GREEN + "PASSED"
                : ANSI_RED + "FAILED"
        ) + ANSI_RESET;
    }

    private static boolean isSorted() {
        for (int i = 0; i < B.size() - 1; i++) {
            if (B.get(i) > B.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
