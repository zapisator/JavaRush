package com.javarush.task.task19.task1921;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Person {
    private String name;
    private Date birthDate;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static Person parse(final String line) {
        final String[] words = line.split(" ");
        if (words.length < 4) {
            throw new RuntimeException("Не хватает данных.");
        }

        return new Person(name(words), date(words));
    }

    private static Date date(final String[] words) {
        final String day = words[words.length - 3];
        final String month = words[words.length - 2];
        final String year = words[words.length - 1];
        final SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

        try {
            return format.parse(day + " " + month + " " + year);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static String name(final String[] words) {
        final StringJoiner joiner = new StringJoiner(" ");

        for (int i = 0; i < words.length - 3; i++) {
            joiner.add(words[i]);
        }
        return joiner.toString();
    }

    @Override
    public String toString() {
        final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return name + " " + format.format(birthDate);
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
