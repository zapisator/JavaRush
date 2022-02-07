package com.javarush.task.task40.task4005;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Rw2 {

    public static void main(String[] args) throws IOException {
        final Path filePath = Paths.get(
                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task40/task4005/1.txt");
        try (                final BufferedReader reader = Files.newBufferedReader(filePath);
        ){
            System.out.println(reader.readLine());

        }

        try (
                final BufferedWriter writer = Files.newBufferedWriter(filePath);
        ) {
            writer.write("Some2\n");
        }


    }
}
