package com.javarush.task.task20.task2014;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    public static void main(String[] args) {
        final Solution savedObject = new Solution(1);
        System.out.println("savedObject:\n" + savedObject);

        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2014/file.txt";

        try (
                final ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));
                final ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
        ) {
            writer.writeObject(savedObject);

            final Solution loadedObject = (Solution) reader.readObject();

            System.out.println("loadedObject:\n" + loadedObject);

            assert savedObject.string.equals(loadedObject.string);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.string;
    }
}
