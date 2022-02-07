package com.javarush.task.task34.task3404.test;

import com.javarush.task.task34.task3404.Solution;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;

public class Test {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static PrintStream setUp(final ByteArrayOutputStream buffer) {
        final PrintStream out = System.out;

        System.setOut(new PrintStream(buffer));
        return out;
    }

    private static void tearDown(final PrintStream out) {
        System.setOut(out);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Pair<String, Pair<String, String>>> expressions = expressions();

        System.out.printf(
                "|%35s expression %34s||  expected answer ||  obtained answer ||expected count || obtained count||  test  |\n",
                "", "");
        for (Pair<String, Pair<String, String>> expression : expressions) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final PrintStream out = setUp(buffer);

            solution.recurse(expression.getKey(), 0);
            tearDown(out);
            printResults(expression, buffer);
        }
    }

    private static void printResults(
            Pair<String, Pair<String, String>> expression, ByteArrayOutputStream buffer) {
        final String[] results = buffer.toString().trim().split(" ");
        final String expressionText = expression.getKey();
        final Pair<String, String> expressionObtained = expression.getValue();
        final String expectedAnswer = expressionObtained.getKey();
        final String obtainedAnswer = results[0];
        final String expectedCount = expressionObtained.getValue();
        final String obtainedCount = results[1];
        final String testResult = (expression.getValue().getKey().equals(results[0])
                && expression.getValue().getValue().equals(results[1])
                ? ANSI_GREEN + "SUCCESS" : ANSI_RED + "FAILED ")
                + ANSI_RESET + "|";

        System.out.printf(
                "|%80s\t||\t%15s || %15s \t|| %13s || %13s || %s\n",
                expressionText,
                expectedAnswer, obtainedAnswer,
                expectedCount, obtainedCount,
                testResult
        );
    }

    private static List<Pair<String, Pair<String, String>>> expressions() {
        final List<String> preExpressions = Arrays.asList(
                "0+0.304", "0.3 1",
                "-0", "-0 0",
                "(-0)", "0 1",
                "-(-0)", "0 2",
                "sin(-30)", "-0.5 2",
                "cos(-30)", "0.87 2",
                "tan(-30)", "-0.58 2",
                "2025 ^ 0.5", "45 1",
                "tan(2025 ^ 0.5)", "1 2",
                "(-2)*(-2)", "4 3",
                "-cos(180)^2", "-1 3",
                "-2^(-2)", "-0.25 3",
                "sin(2*(-5+1.5*4)+28)", "0.5 6",
                "9/4", "2.25 1",
                "9/4-1.5", "0.75 2",
                "(9/4-1.5)^(1+1)", "0.56 4",
                "8*(9/4-1.5)^(1+1)", "4.48 5",
                "2+8*(9/4-1.5)^(1+1)", "6.48 6",
                "sin(3.14/2)^2 + cos(3.14/2)^2", "1 7",
                "sin(2*55) - 2*sin(55)*cos(55)", "0.01 7",
                "1+(1+(1+1)*(1+1))*(1+1)+1", "12 8",
                "-(-2^(-2))+2+(-(-2^(-2)))", "2.5 10",
                "sin(2*(-5+1.5*4)+28)", "0.5 6",
                "sin(3.14/2)^2 + cos(3.14/2)^2", "1 7",
                "(1/3)*(5/8)", "0.2 3",
                "-2^(-2)", "-0.25 3",
                "-sin(2*(-5+1.5*4)+28)", "-0.5 7",
                "sin(2) + sin(0.55) - 2*sin((2+0.55)/2)*cos((2-0.55)/2)", "0 12",
                "2* tan(30/2) / (sin(15.0*2.0)/(1+cos(30)) + 2 *((1-cos(30))/sin(30)))", "0.68 15",
                "sin(cos(60)*60)*cos(45) * tan(56.25) / ((sin(30+45)+sin(30-45))/cos(60)*2.0^2.0)", "0.09 17"
        );
        final List<Pair<String, Pair<String, String>>> expressions = new LinkedList<>();

        for (int i = 0, len = preExpressions.size(); i < len; i += 2) {
            final String[] answers = preExpressions.get(i + 1).split(" ");

            expressions.add(
                    new Pair<>(
                            preExpressions.get(i),
                            new Pair<>(answers[0], answers[1])
                    )
            );
        }
        return expressions;
    }
}
