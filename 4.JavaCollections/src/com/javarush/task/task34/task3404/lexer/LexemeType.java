package com.javarush.task.task34.task3404.lexer;

public enum LexemeType {
    RIGHT_BRACKET,
    LEFT_BRACKET,
    EXPONENTIATION,
    MULTIPLICATION,
    DIVISION,
    SINE,
    COSINE,
    TANGENT,
    NEGATION,
    ADDITION,
    SUBTRACTION,
    NUMBER;

    private static final int[] precedences = new int[LexemeType.values().length];

    static {
        precedences[RIGHT_BRACKET.ordinal()] = 7;
        precedences[LEFT_BRACKET.ordinal()] = 6;
        precedences[SINE.ordinal()] = 5;
        precedences[COSINE.ordinal()] = 5;
        precedences[TANGENT.ordinal()] = 5;
        precedences[EXPONENTIATION.ordinal()] = 4;
        precedences[MULTIPLICATION.ordinal()] = 3;
        precedences[DIVISION.ordinal()] = 3;
        precedences[NEGATION.ordinal()] = 2;
        precedences[SUBTRACTION.ordinal()] = 1;
        precedences[ADDITION.ordinal()] = 1;
        precedences[NUMBER.ordinal()] = 0;
    }

    public static boolean leftPrecedes(LexemeType left, LexemeType right) {
        final int leftPrecedence = precedences[left.ordinal()];
        final int rightPrecedence = precedences[right.ordinal()];

        if (leftPrecedence == rightPrecedence) {
            return leftAssociativity(left);
        } else {
            return leftPrecedence > rightPrecedence;
        }
    }

    private static boolean leftAssociativity(LexemeType type) {
        return !(type == SINE || type == COSINE || type == TANGENT || type == EXPONENTIATION);
    }

    public static boolean isUnary(LexemeType type) {
        return type == SINE || type == COSINE || type == TANGENT || type == NEGATION;
    }

    public static boolean isBinary(LexemeType type) {
        return isOperator(type) && !isUnary(type);
    }

    public static boolean isOperator(LexemeType type) {
        return type != NUMBER && type != LEFT_BRACKET && type != RIGHT_BRACKET;
    }
}
