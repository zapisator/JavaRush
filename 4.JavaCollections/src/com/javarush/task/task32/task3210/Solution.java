package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
//        args = new String[]{
//                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task32/task3210/1.txt",
//                "21",
//                "It is a bad  to die."
//        };

        final RandomAccessFile file = new RandomAccessFile(args[0], "rw");
        final int cursor = Integer.parseInt(args[1]);
        final String text = args[2];
        final byte[] buffer = new byte[text.length()];

        file.seek(cursor);
        file.read(buffer, 0, buffer.length);
        file.seek(file.length());
        file.write(("" + text.equals(new String(buffer))).getBytes(StandardCharsets.UTF_8));
    }

}
