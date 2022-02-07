package com.javarush.task.task34.task3404.expression;

import com.javarush.task.task34.task3404.lexer.Lexeme;
import java.math.BigDecimal;

public class Nullary extends Arity {

    public Nullary(Lexeme lexeme) {
        super(lexeme);
    }

    @Override
    public BigDecimal execute() {
        return new BigDecimal(lexeme.getSubexpressionText());
    }
}
