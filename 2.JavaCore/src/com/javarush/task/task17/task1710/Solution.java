package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static final String USAGE =
            "Программа запускается с одним из следующих наборов параметров:\n"
            + "-c name sex bd\n"
            + "-i id\n"
            + "-u id name sex bd\n"
            + "-d id\n"
            + "\n"
            + "Значения параметров:\n"
            + "name - имя, String\n"
            + "sex - пол, \"м\" или \"ж\", одна буква\n"
            + "bd - дата рождения в следующем формате 15/04/1990\n"
            + "-c - добавляет человека с заданными параметрами в конец allPeople, "
            + "выводит id (index) на экран\n"
            + "-i - выводит на экран информацию о человеке с id: "
            + "name sex (м/ж) bd (формат 15-Apr-1990)\n"
            + "-u - обновляет данные человека с данным id\n"
            + "-d - производит логическое удаление человека с id, заменяет все его данные на null\n";
    public static final int PARAMETER_NUMBER_C = 4;
    public static final int PARAMETER_NUMBER_I = 2;
    public static final int PARAMETER_NUMBER_U = 5;
    public static final int PARAMETER_NUMBER_D = 2;

    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
//        args = new String[]{"-c", "Миронов", "м", "15/04/1990"};
//        args = new String[]{"-i", "1"};
//        args = new String[]{"-i", "-1"};
//        args = new String[]{"-u", "1", "Миронов", "м", "15/04/1990"};
//                args = new String[]{"-d", "1"};

        if (args.length == 0) {
            throw new IllegalArgumentException(USAGE);
        }
        switch (args[0]) {
            case "-c":
                addRearAndPrintItsIndex(args);
                break;
            case "-i":
                print(args);
                break;
            case "-u":
                update(args);
                break;
            case "-d":
                delete(args);
                break;
            default:
                throw new IllegalArgumentException(USAGE);
        }
//        allPeople.forEach(System.out::println);
    }

    private static void addRearAndPrintItsIndex(String[] args) {
        checkParameterNumber(args, PARAMETER_NUMBER_C);
        checkSex(args[2]);

        allPeople.add(newPerson(args[1], args[2], args[3]));
        System.out.println(allPeople.size() - 1);
    }

    private static Person newPerson(final String name, final String sex, final String date) {
        final Date birthDate = birthDate(date);

        return sex.equalsIgnoreCase("м") ?
                Person.createMale(name, birthDate) : Person.createFemale(name, birthDate);
    }

    private static Date birthDate(String arg) {
        checkBirthdate(arg);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            return dateFormat.parse(arg);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Неверный формат даты\n\n" + USAGE, e);
        }
    }

    private static void checkBirthdate(String arg) {
        if (!arg.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
            throw new IllegalArgumentException("Неверный формат даты\n\n" + USAGE);
        }
    }

    private static void checkParameterNumber(String[] args, int parameter_number) {
        if (args.length != parameter_number) {
            throw new IllegalArgumentException("Неверное число параметров\n\n" + USAGE);
        }
    }

    private static void checkSex(String arg) {
        switch (arg.toLowerCase()) {
            case "м":
            case "ж":
                break;
            default:
                throw new IllegalArgumentException(
                        "Параметр пол может быть задан только одним из 4 симоволов: м/М/ж/Ж\n\n"
                        + USAGE
                );
        }
    }

    private static void print(String[] args) {
        checkParameterNumber(args, PARAMETER_NUMBER_I);
        final Person person = person(args[1]);

        System.out.println(person);
    }

    private static Person person(String arg) {
        try {
            return allPeople.get(id(arg));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Неверный id\n\n" + USAGE, e);
        }
    }

    private static int id(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Параметр id может быть только числом: м/М/ж/Ж\n\n"
                    + USAGE, e
            );
        }
    }

    private static void update(String[] args) {
        checkParameterNumber(args, PARAMETER_NUMBER_U);
        checkBirthdate(args[4]);
        checkSex(args[3]);

        final int idToUpdate = id(args[1]);
        final Person person = newPerson(args[2], args[3], args[4]);

        replace(idToUpdate, person);
    }

    private static void replace(int idToUpdate, Person person) {
        checkId(idToUpdate);

        allPeople.remove(idToUpdate);
        allPeople.add(idToUpdate, person);
    }

    private static void delete(String[] args) {
        checkParameterNumber(args, PARAMETER_NUMBER_D);

        removeById(id(args[1]));
    }

    private static void removeById(int id) {
        checkId(id);

        final Person person = allPeople.get(id);
        person.setBirthDate(null);
        person.setName(null);
        person.setSex(null);
    }

    private static void checkId(int id) {
        if (id < 0 || id >= allPeople.size()) {
            throw new IllegalArgumentException("Неверный id\n\n" + USAGE);
        }
    }
}
