package com.javarush.task.task34.task3404.expression;

import static com.javarush.task.task34.task3404.expression.Binary.BinaryOperators.ADDITION;
import static com.javarush.task.task34.task3404.expression.Binary.BinaryOperators.DIVISION;
import static com.javarush.task.task34.task3404.expression.Binary.BinaryOperators.EXPONENTIATION;
import static com.javarush.task.task34.task3404.expression.Binary.BinaryOperators.MULTIPLICATION;
import static com.javarush.task.task34.task3404.expression.Binary.BinaryOperators.SUBTRACTION;
import static java.lang.Math.pow;
import static java.math.RoundingMode.HALF_UP;

import com.javarush.task.task34.task3404.lexer.Lexeme;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

public class Binary extends Arity {

    private static final BiFunction<Expression, Expression, BigDecimal>[] OPERATORS
            = new BiFunction[BinaryOperators.values().length];

    static {
        OPERATORS[EXPONENTIATION.ordinal()] = (left, right) -> BigDecimal.valueOf(
                pow(left.execute().doubleValue(), right.execute().doubleValue()));
        OPERATORS[MULTIPLICATION.ordinal()] = (left, right)
                -> left.execute().multiply(right.execute());
        OPERATORS[DIVISION.ordinal()] = (left, right)
                -> left.execute().divide(right.execute(), 2, HALF_UP);
        OPERATORS[SUBTRACTION.ordinal()] = (left, right)
                -> left.execute().subtract(right.execute());
        OPERATORS[ADDITION.ordinal()] = (left, right) -> left.execute().add(right.execute());

        if (!isFullyLoaded()) {
            throw new RuntimeException(Arrays.toString(OPERATORS) + " should not no nulls.");
        }
    }

    public Binary(Lexeme lexeme, Expression operandLeft, Expression operandRight) {
        super(lexeme, operandLeft, operandRight);
    }

    private static boolean isFullyLoaded() {
        return Arrays.stream(OPERATORS)
                .allMatch(Objects::nonNull);
    }

    @Override
    public BigDecimal execute() {
        return OPERATORS[functionIndex()]
                .apply(firstOperand(), secondOperand());
    }

    private Expression firstOperand() {
        return operand(0);
    }

    private Expression secondOperand() {
        return operand(1);
    }

    private Expression operand(int i) {
        return operands[i];
    }

    private String binaryOperatorName() {
        return lexeme.getType().toString();
    }

    private BinaryOperators binaryOperator() {
        return BinaryOperators.valueOf(binaryOperatorName());
    }

    private int functionIndex() {
        return binaryOperator().ordinal();
    }

    enum BinaryOperators {
        EXPONENTIATION,
        MULTIPLICATION,
        DIVISION,
        SUBTRACTION,
        ADDITION;
    }
}
