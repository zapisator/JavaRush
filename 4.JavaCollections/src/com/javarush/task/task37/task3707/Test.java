package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        final AmigoSet<Integer> amigoSet = new AmigoSet<>(
                IntStream.range(0, 20)
                        .boxed()
                        .collect(Collectors.toList())
        );
        final Path persistent = Paths.get(
                "/home/sb_work/Загрузки/JavaRushTasks/"
                        + "4.JavaCollections/src/com/javarush/"
                        + "task/task37/task3707/out.txt"
        );

        try (
                final OutputStream stream = Files.newOutputStream(persistent);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        ) {
            objectOutputStream.writeObject(amigoSet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AmigoSet<Integer> amigoSet2 = null;
        try (
                final InputStream stream = Files.newInputStream(persistent);
                final ObjectInputStream objectInputStream = new ObjectInputStream(stream)
        ) {
            amigoSet2 = (AmigoSet<Integer>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Integer integer : amigoSet) {
            System.out.println(integer);
        }
        for (Integer integer : amigoSet2) {
            System.out.println(integer);
        }

        HashSet set;
    }

}
