package com.javarush.task.task32.task3202;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

/* 
Читаем из потока
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream(
                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task32/task3202/testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        is = is == null ? new ByteArrayInputStream("".getBytes()) : is;
        final byte[] buffer = new byte[is.available()];

        is.read(buffer);
        return new StringWriter(buffer.length).append(new String(buffer));
    }
}
