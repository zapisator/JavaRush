package com.javarush.task.task34.task3404.lexer;

import static com.javarush.task.task34.task3404.format.OutputFormat.decimalFormat;
import static com.javarush.task.task34.task3404.lexer.LexemeType.NUMBER;

import java.util.Objects;

public class Lexeme {

    private final LexemeType type;
    private final String expressionText;
    private final int start;
    private final int end;

    public Lexeme(LexemeType type, String expressionText, int start, int end) {
        Objects.requireNonNull(type);
        this.type = type;
        this.expressionText = expressionText;
        this.start = start;
        this.end = end;
    }

    public LexemeType getType() {
        return type;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        final String operand = getSubexpressionText();
        return type == NUMBER ? decimalFormat(operand) : operand;
    }

    public String getSubexpressionText() {
        return expressionText.substring(start, end);
    }
}
