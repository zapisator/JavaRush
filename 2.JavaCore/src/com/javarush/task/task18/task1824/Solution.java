package com.javarush.task.task18.task1824;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                final String fileName = scanner.nextLine().trim();
                try (final FileInputStream stream = new FileInputStream(fileName)) {

                } catch (FileNotFoundException e) {
                    System.out.println(fileName);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
