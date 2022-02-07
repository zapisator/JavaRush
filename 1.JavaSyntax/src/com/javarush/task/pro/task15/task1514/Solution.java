package com.javarush.task.pro.task15.task1514;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/* 
Все относительно
*/

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        try {
            final Path path1 = Paths.get(str1);
            final Path path2 = Paths.get(str2);
            try {
                final Path relative1 = path1.relativize(path2);
                System.out.println(relative1);
            } catch (IllegalArgumentException e) {
                final Path relative2 = path2.relativize(path1);
                System.out.println(relative2);
            }
        } catch (InvalidPathException e) {
            System.out.print("");
        }
    }
}

