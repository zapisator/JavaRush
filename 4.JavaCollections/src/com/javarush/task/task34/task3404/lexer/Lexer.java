package com.javarush.task.task34.task3404.lexer;

import static com.javarush.task.task34.task3404.lexer.LexemeType.ADDITION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.COSINE;
import static com.javarush.task.task34.task3404.lexer.LexemeType.DIVISION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.EXPONENTIATION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.LEFT_BRACKET;
import static com.javarush.task.task34.task3404.lexer.LexemeType.MULTIPLICATION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.NEGATION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.NUMBER;
import static com.javarush.task.task34.task3404.lexer.LexemeType.RIGHT_BRACKET;
import static com.javarush.task.task34.task3404.lexer.LexemeType.SINE;
import static com.javarush.task.task34.task3404.lexer.LexemeType.SUBTRACTION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.TANGENT;
import static com.javarush.task.task34.task3404.lexer.LexemeType.isOperator;

import java.util.LinkedList;

public class Lexer {

    private final String expression;
    private final LinkedList<Lexeme> lexemes;
    private long countOperations;

    private Lexer(final String expression) {
        this.expression = expression;
        lexemes = new LinkedList<>();
    }

    public static Lexer lex(final String expression) {
        final Lexer lexer = new Lexer(expression);

        for (int i = 0, len = expression.length(); i < len; ) {
            i = lexer.trim(i);
            lexer.lexemes.add(lexer.analyze(i));
            i = lexer.lexemes.getLast().getEnd();
        }
        lexer.setCountOperations();
        return lexer;
    }

    public long getCountOperations() {
        return countOperations;
    }

    public void setCountOperations() {
        this.countOperations = lexemes.stream()
                .filter(lexeme -> isOperator(lexeme.getType()))
                .count();
    }

    private LexemeType type(int i) {
        switch (Character.toLowerCase(expression.charAt(i))) {
            case '(':
                return LEFT_BRACKET;
            case ')':
                return RIGHT_BRACKET;
            case '-':
                if (lexemes.isEmpty()
                        || (lexemes.getLast().getType() != NUMBER
                        && lexemes.getLast().getType() != RIGHT_BRACKET)) {
                    return NEGATION;
                } else {
                    return SUBTRACTION;
                }
            case '+':
                return ADDITION;
            case '^':
                return EXPONENTIATION;
            case '*':
                return MULTIPLICATION;
            case '/':
                return DIVISION;
            case 's':
                return SINE;
            case 'c':
                return COSINE;
            case 't':
                return TANGENT;
            default:
                return NUMBER;
        }
    }

    private Lexeme analyze(final int start) {
        final LexemeType type = type(start);

        return new Lexeme(type, expression, start, end(start, type));
    }

    private int end(final int start, LexemeType type) {
        switch (type) {
            case NUMBER:
                return numericEnd(start);
            case SINE:
            case COSINE:
            case TANGENT:
                return start + 3;
            default:
                return start + 1;
        }
    }

    private int numericEnd(final int start) {
        final int len = expression.length();
        int end = start;

        while (end < len && Character.isDigit(expression.charAt(end))) {
            end++;
        }
        return endIfIsFloat(end);
    }

    private int endIfIsFloat(int end) {
        final int len = expression.length();

        if (end < len && expression.charAt(end) == '.') {
            end++;

            while (end < len && Character.isDigit(expression.charAt(end))) {
                end++;
            }
        }
        return end;
    }

    private int trim(int end) {
        while (Character.isSpaceChar(expression.charAt(end))) {
            end++;
        }
        return end;
    }

    public String getExpression() {
        return expression;
    }

    public LinkedList<Lexeme> getLexemes() {
        return lexemes;
    }
}
