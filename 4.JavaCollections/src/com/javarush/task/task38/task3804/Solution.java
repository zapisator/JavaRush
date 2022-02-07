package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

import static com.javarush.task.task38.task3804.Solution.ExceptionFactory.exception;
import static java.lang.System.*;

import java.util.Locale;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    enum Enumeration {
        SOME;
    }

    public static void main(String[] args) {
        for (DatabaseExceptionMessage value : DatabaseExceptionMessage.values()) {
            out.println(exception(value).getMessage());
        }
        for (ApplicationExceptionMessage value : ApplicationExceptionMessage.values()) {
            out.println(exception(value).getMessage());
        }
        for (UserExceptionMessage value : UserExceptionMessage.values()) {
            out.println(exception(value).getMessage());
        }
        out.println(exception(null).getMessage());
        out.println(exception(Enumeration.SOME).getMessage());
    }

    public static class ExceptionFactory {

        public static Throwable exception(Enum<?> enumeration) {

            if (enumeration != null) {
                final String message
                        = (enumeration.toString().charAt(0)
                        + enumeration.toString().substring(1).toLowerCase(Locale.ROOT)
                ).replace("_", " ");

                if (enumeration instanceof UserExceptionMessage) {
                    return new Error(message);
                } else if (enumeration instanceof DatabaseExceptionMessage) {
                    return new RuntimeException(message);
                } else if (enumeration instanceof ApplicationExceptionMessage) {
                    return new Exception(message);
                }
            }
            return new IllegalArgumentException();
        }
    }

}