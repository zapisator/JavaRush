package com.javarush.task.task18.task1826;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* 
Шифровка
*/

public class Solution {

    enum Crypt {
        ENCRYPT, DECRYPT
    }
    public static void main(String[] args) {
//        args = new String[]{
//                "-e",
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1826/1",
//                "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1826/2"
//        };
        if (args.length != 3) {
            usage();
            System.exit(-1);
        }
        switch (args[0]) {
            case "-e":
                crypt(args[1], args[2], Crypt.ENCRYPT);
                break;
            case "-d":
                crypt(args[1], args[2], Crypt.DECRYPT);
                break;
            default:
                usage();
        }
    }

    private static void crypt(String source, String destination, Crypt crypt) {
        try (
                final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(source));
                final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination))) {
            final byte[] input = new byte[inputStream.available()];
            if (inputStream.read(input) != input.length) {
                throw new RuntimeException("Read not enough.");
            }
            switch (crypt) {
                case ENCRYPT:
                    encrypt(input);
                    break;
                case DECRYPT:
                    decrypt(input);
                    break;
            }
            outputStream.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decrypt(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) ((input[i] - 1) % 256);
        }
    }

    private static void encrypt(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) ((input[i] + 1) % 256);
        }
    }

    private static void usage() {
        System.out.println("Программа запускается с одним из следующих наборов параметров:\n"
                + "-e fileName fileOutputName\n"
                + "-d fileName fileOutputName\n"
                + "\n"
                + "где:\n"
                + "fileName - имя файла, который необходимо зашифровать/расшифровать.\n"
                + "fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования.\n"
                + "-e - ключ указывает, что необходимо зашифровать данные.\n"
                + "-d - ключ указывает, что необходимо расшифровать данные.");
    }

}
