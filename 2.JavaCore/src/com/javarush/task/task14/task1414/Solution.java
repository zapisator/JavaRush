package com.javarush.task.task14.task1414;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
MovieFactory
*/

public class Solution {

    final static String SOAP_OPERA = "soapOpera";
    final static String CARTOON = "cartoon";
    final static String THRILLER = "thriller";

    public static void main(String[] args) throws Exception {
        //ввести с консоли несколько ключей (строк), пункт 7
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Movie movie = null;
        String key = null;

        while (isMovieOfThree(key = reader.readLine()))
        {
            movie = MovieFactory.getMovie(key);
            System.out.println(movie.getClass().getSimpleName());
        }
        movie = MovieFactory.getMovie(key);

        /*
8 Создать переменную movie класса Movie и для каждой введенной строки(ключа):
8.1 получить объект используя MovieFactory.getMovie и присвоить его переменной movie
8.2 вывести на экран movie.getClass().getSimpleName()
        */

    }

    private static boolean isMovieOfThree(String key) {
        return SOAP_OPERA.equals(key) || CARTOON.equals(key) || THRILLER.equals(key);
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;

            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if (SOAP_OPERA.equals(key)) {
                movie = new SoapOpera();
            } else if (CARTOON.equals(key)) {
                movie = new Cartoon();
            } else if (THRILLER.equals(key)) {
                movie = new Thriller();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    static class Cartoon extends Movie {

    }

    static class Thriller extends Movie {

    }

}
