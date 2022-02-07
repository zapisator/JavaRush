package com.javarush.task.task34.task3404.parser;

import static com.javarush.task.task34.task3404.lexer.LexemeType.LEFT_BRACKET;
import static com.javarush.task.task34.task3404.lexer.LexemeType.NUMBER;
import static com.javarush.task.task34.task3404.lexer.LexemeType.RIGHT_BRACKET;
import static com.javarush.task.task34.task3404.lexer.LexemeType.isBinary;
import static com.javarush.task.task34.task3404.lexer.LexemeType.isOperator;
import static com.javarush.task.task34.task3404.lexer.LexemeType.isUnary;
import static com.javarush.task.task34.task3404.lexer.LexemeType.leftPrecedes;

import com.javarush.task.task34.task3404.expression.Binary;
import com.javarush.task.task34.task3404.expression.Expression;
import com.javarush.task.task34.task3404.expression.Nullary;
import com.javarush.task.task34.task3404.expression.Unary;
import com.javarush.task.task34.task3404.lexer.Lexeme;
import com.javarush.task.task34.task3404.lexer.LexemeType;
import java.util.LinkedList;

public class Parser {

    private final LinkedList<Lexeme> lexemes;

    public Parser(LinkedList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Expression parse() {
        final LinkedList<Lexeme> operators = new LinkedList<>();
        final LinkedList<Expression> operands = new LinkedList<>();

        while (!lexemes.isEmpty()) {
            final Lexeme lexeme = lexemes.pop();
            final LexemeType lexemeType = lexeme.getType();

            if (lexemeType == NUMBER) {
                operands.addFirst(new Nullary(lexeme));
            } else if (lexemeType == LEFT_BRACKET) {
                operators.addFirst(lexeme);
            } else if (lexemeType == RIGHT_BRACKET) {
                composeWhileOperator(operators, operands);
                discardRightBracket(operators);
            } else if (operators.isEmpty() || (operators.peek().getType() == LEFT_BRACKET)) {
                operators.addFirst(lexeme);
            } else if (!leftPrecedes(operators.peek().getType(), lexeme.getType())) {
                operators.addFirst(lexeme);
            } else if (leftPrecedes(operators.peek().getType(), lexeme.getType())) {
                composeWhileOperatorOfHigherPrecedence(operators, operands, lexemeType);
                operators.addFirst(lexeme);
            }
        }
        while (!operators.isEmpty()) {
            compose(operators.pop(), operands);
        }
        return operands.size() == 1 ? operands.pop() : null;
    }

    private void discardRightBracket(LinkedList<Lexeme> operators) {
        operators.pop();
    }

    private void composeWhileOperatorOfHigherPrecedence(
            LinkedList<Lexeme> operators, LinkedList<Expression> operands, LexemeType lexemeType) {
        while (!operators.isEmpty()
                && isOperator(operators.peek().getType())
                && leftPrecedes(operators.peek().getType(), lexemeType)
        ) {
            compose(operators.pop(), operands);
        }
    }

    private void composeWhileOperator(
            LinkedList<Lexeme> operators, LinkedList<Expression> operands) {
        while (!operators.isEmpty() && isOperator(operators.peek().getType())) {
            compose(operators.pop(), operands);
        }
    }

    private void compose(Lexeme operator, LinkedList<Expression> operands) {
        final Expression operand = operands.pop();

        if (isUnary(operator.getType())) {
            operands.addFirst(new Unary(operator, operand));
        } else if (isBinary(operator.getType())) {
            final Expression firstOperand = operands.pop();

            operands.addFirst(new Binary(operator, firstOperand, operand));
        }
    }

}
