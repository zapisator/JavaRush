package com.javarush.task.task34.task3404.expression;

import static com.javarush.task.task34.task3404.expression.Unary.UnaryOperators.COSINE;
import static com.javarush.task.task34.task3404.expression.Unary.UnaryOperators.NEGATION;
import static com.javarush.task.task34.task3404.expression.Unary.UnaryOperators.SINE;
import static com.javarush.task.task34.task3404.expression.Unary.UnaryOperators.TANGENT;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;

import com.javarush.task.task34.task3404.lexer.Lexeme;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class Unary extends Arity {

    private static final Function<Expression, BigDecimal>[] OPERATORS
            = new Function[UnaryOperators.values().length];

    static {
        OPERATORS[SINE.ordinal()] = (operand ->
                BigDecimal.valueOf(sin(toRadians(operand.execute().doubleValue()))));
        OPERATORS[COSINE.ordinal()] = (operand ->
                BigDecimal.valueOf(cos(toRadians(operand.execute().doubleValue()))));
        OPERATORS[TANGENT.ordinal()] = (operand ->
                BigDecimal.valueOf(tan(toRadians(operand.execute().doubleValue()))));
        OPERATORS[NEGATION.ordinal()] = (operand ->
                operand.execute().multiply(UNARY_MINUS, MATH_CONTEXT));

        if (!isFullyLoaded()) {
            throw new RuntimeException(Arrays.toString(OPERATORS) + " should have no nulls.");
        }
    }

    public Unary(Lexeme lexeme, Expression operand) {
        super(lexeme, operand);
    }

    private static boolean isFullyLoaded() {
        return Arrays.stream(OPERATORS)
                .allMatch(Objects::nonNull);
    }

    @Override
    public BigDecimal execute() {
        return OPERATORS[functionIndex()]
                .apply(operand());
    }

    private Expression operand() {
        return operands[0];
    }

    private String unaryOperatorName() {
        return lexeme.getType().toString();
    }

    private UnaryOperators unaryOperator() {
        return UnaryOperators.valueOf(unaryOperatorName());
    }

    private int functionIndex() {
        return unaryOperator().ordinal();
    }

    enum UnaryOperators {
        SINE,
        COSINE,
        TANGENT,
        NEGATION;
    }

}
