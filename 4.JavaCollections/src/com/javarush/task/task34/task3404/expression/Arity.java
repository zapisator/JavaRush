package com.javarush.task.task34.task3404.expression;

import static com.javarush.task.task34.task3404.lexer.LexemeType.NEGATION;
import static com.javarush.task.task34.task3404.lexer.LexemeType.NUMBER;

import com.javarush.task.task34.task3404.lexer.Lexeme;
import com.javarush.task.task34.task3404.lexer.LexemeType;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringJoiner;

abstract class Arity implements Expression {

    public static final MathContext MATH_CONTEXT = new MathContext(0, RoundingMode.HALF_UP);
    public static final BigDecimal UNARY_MINUS = new BigDecimal("-1", MATH_CONTEXT);
    protected final Lexeme lexeme;
    protected final Expression[] operands;

    protected Arity(Lexeme lexeme, Expression... operands) {
        this.lexeme = lexeme;
        this.operands = operands;
    }

    @Override
    public abstract BigDecimal execute();

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ");
        final LexemeType type = lexeme.getType();

        for (Expression operand : operands) {
            joiner.add(operand.toString());
        }
        return lexeme
                + (type == NUMBER || type == NEGATION ? joiner.toString() : "(" + joiner + ")");
    }

}
