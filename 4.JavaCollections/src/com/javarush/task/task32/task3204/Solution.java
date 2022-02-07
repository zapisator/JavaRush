package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

/* 
Генератор паролей
*/

public class Solution {

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());

        Stream.generate(Solution::getPassword)
                .limit(10000)
                .map(ByteArrayOutputStream::toString)
                .filter(s -> s.chars().noneMatch(Character::isDigit)
                        || s.chars().noneMatch(Character::isLowerCase)
                        || s.chars().noneMatch(Character::isUpperCase))
                .forEach(System.out::println);

    }

    public static ByteArrayOutputStream getPassword() {
        final Random random = new Random();
        final StringBuilder password = random.ints('0', 'z' + 1)
                .filter(Character::isLetterOrDigit)
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();

        password.appendCodePoint('0' + random.nextInt('9' - '0'))
                .appendCodePoint('a' + random.nextInt('z' - 'a'))
                .appendCodePoint('A' + random.nextInt('Z' - 'A'));

        Collections.shuffle(Arrays.asList(
                password.toString().split(""))
        );
        try {
            stream.write(password.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

}
