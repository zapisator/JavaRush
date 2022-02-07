package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/* 
Работа с Java 8 DateTime API
*/

public class SolutionMy {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        switch (formatType(date.trim())) {
            case DATE_TIME:
                final LocalDateTime dateTime = LocalDateTime
                        .parse(date, DateTimeFormatter.ofPattern("d.M.y H:m:s"));

                print(dateTime);
                break;
            case DATE:
                final LocalDate localDate = LocalDate
                        .parse(date, DateTimeFormatter.ofPattern("d.M.y"));

                print(localDate);
                break;
            case TIME:
                final LocalTime localTime = LocalTime
                        .parse(date, DateTimeFormatter.ofPattern("H:m:s"));

                print(localTime);
                break;
            default:
                throw new RuntimeException("Something went wrong with[" + date + "]");
        }
    }

    private static void print(LocalDateTime dateTime) {
        print(dateTime.toLocalDate());
        print(dateTime.toLocalTime());
    }

    private static void print(LocalDate date) {
        System.out.printf("День: %d"
                        + "%nДень недели: %d"
                        + "%nДень месяца: %d"
                        + "%nДень года: %d"
                        + "%nНеделя месяца: %s"
                        + "%nНеделя года: %s"
                        + "%nМесяц: %d"
                        + "%nГод: %d%n",
                date.getDayOfMonth(),
                date.getDayOfWeek().getValue(),
                date.getDayOfMonth(),
                date.getDayOfYear(),
                DateTimeFormatter.ofPattern("W").format(date),
                DateTimeFormatter.ofPattern("w").format(date),
                date.getMonth().getValue(),
                date.getYear()
        );
    }

    private static void print(LocalTime time) {
        System.out.printf("AM или PM: %s"
                        + "%nЧасы: %s"
                        + "%nЧасы дня: %s"
                        + "%nМинуты: %s"
                        + "%nСекунды: %s%n",
                DateTimeFormatter.ofPattern("a").format(time),
                DateTimeFormatter.ofPattern("K").format(time),
                DateTimeFormatter.ofPattern("k").format(time),
                time.getMinute(),
                time.getSecond()
        );
    }

    private static FormatType formatType(String date) {
        final String dateFormat = "\\d{1,2}\\.\\d{1,2}\\.\\d{4}";
        final String timeFormat = "\\d{1,2}:\\d{1,2}:\\d{1,2}";
        final String dateTimeFormat = dateFormat + "\\p{Blank}+" + timeFormat;

        if (date.matches(dateTimeFormat)) {
            return FormatType.DATE_TIME;
        }
        if (date.matches(dateFormat)) {
            return FormatType.DATE;
        }
        if (date.matches(timeFormat)) {
            return FormatType.TIME;
        }
        throw new RuntimeException("Unrecognized dateTime format of: " + date);
    }

    enum FormatType {
        TIME, DATE, DATE_TIME
    }

}
