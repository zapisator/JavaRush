package com.javarush.task.task17.task1705;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //напишите тут ваш код
        boolean stop = false;
        while(!stop){
            Path directory = Paths.get(scanner.nextLine());
            stop = true;
            if(Files.isDirectory(directory)){
                stop = false;
                System.out.println(directory + THIS_IS_DIR);
            }else if(Files.isRegularFile(directory)){
                stop = false;
                System.out.println(directory + THIS_IS_FILE);
            };
        }

    }
}
