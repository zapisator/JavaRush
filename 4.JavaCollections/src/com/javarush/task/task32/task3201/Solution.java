package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws IOException {
//        args = new String[]{
//                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task32/task3201/1.txt",
//                "60",
//                "bad "
//        };

        final String fileName = args[0];
        final RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        final int cursor = Math.min(Integer.parseInt(args[1]), (int) file.length());
        final String text = args[2];

        file.seek(cursor);
        file.write(text.getBytes(StandardCharsets.UTF_8));
    }
}
