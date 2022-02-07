package com.javarush.task.pro.task14.task1401;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Поработай обработчиком
*/

public class Solution {
    public static final int NEGATIVE_AGE = -1;
    public static final int TOO_BIG_AGE = -2;

    public static final int NAME_ABSENT = -1;
    public static final int NAME_EMPTY = -2;
    public static final int NAME_DOES_NOT_MATCH = -3;

    public static final int NEGATIVE_INDEX = -1;

    public static final String INPUT_NAME = "\nВведите имя: ";
    public static final String INPUT_AGE = "Введите возраст пользователя '%s': ";

    public static final String CANNOT_BE_NULL = "Имя не может быть null.";
    public static final String CANNOT_BE_EMPTY = "Имя не может быть пустым.";
    public static final String CANNOT_CONTAIN_DIGIT = "Имя не может содержать цифры.";

    public static final String CANNOT_BE_NEGATIVE = "Возраст не может быть меньше 0.";
    public static final String CANNOT_BE_TOO_BIG = "Возраст не может быть больше 150.";
    public static final String UNKNOWN_ERROR = "Неизвестная ошибка.";

    public static final String FOUND = "\nПользователь '%s' найден под индексом %d.\n";
    public static final String NOT_FOUND = "\nПользователь '%s' не найден.\n";

    static List<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            addUser(new User());
        }

        User userToSearch = new User();
        userToSearch.setName("Рафаэль");

        findUserIndex(userToSearch);
    }

    static void addUser(User user) {
        String name = getName();
        int age = getAge(name);

        setName(user, name);
        setAge(user, age);
        users.add(user);
    }

    private static int getAge(String name) {
        System.out.printf(INPUT_AGE, name);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String getName() {
        System.out.print(INPUT_NAME);
        return scanner.nextLine();
    }

    private static void setAge(User user, int age) {
        final int setAgeStatus = user.setAge(age);

        if (setAgeStatus == NEGATIVE_AGE) {
            printError(CANNOT_BE_NEGATIVE);
        } else if (setAgeStatus == TOO_BIG_AGE) {
            printError(CANNOT_BE_TOO_BIG);
        } else if (setAgeStatus != 0) {
            printError(UNKNOWN_ERROR);
        }
    }

    private static void setName(User user, String name) {
        final int setNameStatus = user.setName(name);

        if (setNameStatus == NAME_ABSENT) {
            printError(CANNOT_BE_NULL);
        } else if (setNameStatus == NAME_EMPTY) {
            printError(CANNOT_BE_EMPTY);
        } else if (setNameStatus == NAME_DOES_NOT_MATCH) {
            printError(CANNOT_CONTAIN_DIGIT);
        } else if (setNameStatus != 0) {
            printError(UNKNOWN_ERROR);
        }
    }

    static void findUserIndex(User user) {
        final int userIndex = users.indexOf(user);

        if (userIndex == NEGATIVE_INDEX) {
            printErrorF(user.getName());
        } else {
            System.out.printf(FOUND, user.getName(), userIndex);
        }
    }

    static void printErrorF(String name) {
        System.out.printf(NOT_FOUND, name);
    }

    static void printError(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
