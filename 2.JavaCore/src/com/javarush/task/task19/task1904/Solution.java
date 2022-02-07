package com.javarush.task.task19.task1904;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(new FileInputStream(
                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1904/person")
        );
        final PersonScannerAdapter adapter = new PersonScannerAdapter(scanner);
        System.out.println(adapter.read());
    }

    public static class PersonScannerAdapter implements PersonScanner {

        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            final String[] fields = fileScanner.nextLine().split(" ");

            final String firstName = fields[1];
            final String middleName = fields[2];
            final String lastName = fields[0];

            final int day =  Integer.parseInt(fields[3]);
            final int month = Integer.parseInt(fields[4]);
            final int year = Integer.parseInt(fields[5]);

            final LocalDate localDate = LocalDate.of(year, month, day);
            final Date date = Date.from(
                    localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
            );

            return new Person(firstName, middleName, lastName, date);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
