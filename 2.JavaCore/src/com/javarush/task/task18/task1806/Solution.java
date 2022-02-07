package com.javarush.task.task18.task1806;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Исправить ошибки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        final String inputFilename = "c:/data.txt";
//        final String outputFileName = "c:/result.txt";
        final String inputFilename = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1806/1";
        final String outputFileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1806/2";

        FileInputStream inputStream = new FileInputStream(inputFilename);
        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream(outputFileName);

        if (inputStream.available() > 0) {
            //читаем весь файл одним куском
            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count);
        }

        inputStream.close();
        outputStream.close();
    }
}
