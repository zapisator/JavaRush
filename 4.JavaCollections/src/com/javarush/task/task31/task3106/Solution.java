package com.javarush.task.task31.task3106;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File result = new File(args[0]);    //Файл результата, по совместительству имя этого файла мы ищем в архиве
        if (!result.exists()) {
            result.createNewFile();
        }
        List<FileInputStream> fileInputStreams = new ArrayList<>(); //Список входящих стримов из разных кусков архива

        //Расставляем имена файлов архива в нужном нам порядке
        List<String> fileNames = new ArrayList<>();
        fileNames.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileNames);

        //Создаем входящий стрим для каждого куска архива
        for (String name : fileNames) {
            fileInputStreams.add(new FileInputStream(name));
        }

        try (ZipInputStream is = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreams))))    //Входящий стрим общего архива
        {
            while (true) {
                ZipEntry entry = is.getNextEntry();
                if (entry == null) break;

                try (OutputStream os = new BufferedOutputStream(new FileOutputStream(result))) {
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int readBytes; (readBytes = is.read(buffer, 0, bufferSize)) > -1; ) {
                        os.write(buffer, 0, readBytes);
                    }
                    os.flush();
                }
            }
        }
    }
}
//public class Solution {
//
//    public static void main(String[] args) {
////        args = new String[]{
////                "C:/result.mp3",
////                "C:/pathToTest/test.zip.003",
////                "C:/pathToTest/test.zip.001",
////                "C:/pathToTest/test.zip.004",
////                "C:/pathToTest/test.zip.002"
////        };
//
//        String fileName = args[0];
//        final List<String> filePartNames = Arrays.stream(args, 1, args.length)
//                .sorted(Comparator.naturalOrder())
//                .collect(Collectors.toList());
//
////        filePartNames.forEach(System.out::println);
//
//        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//        for (String filePartName : filePartNames) {
//            try (final BufferedInputStream inputStream = new BufferedInputStream(
//                    Files.newInputStream(Paths.get(filePartName)))) {
//                final byte[] buffer = new byte[inputStream.available()];
//
//                inputStream.read(buffer);
//                byteArrayOutputStream.write(buffer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try (
//                final ZipInputStream inputStream = new ZipInputStream(
//                        new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
//        ) {
//
//            final ZipEntry entry = inputStream.getNextEntry();
//            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            final byte[] buffer = new byte[4096];
//            int bytesRead = inputStream.read(buffer);
//            while (bytesRead != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//                bytesRead = inputStream.read(buffer);
//            }
//
//            Files.write(Paths.get(fileName), outputStream.toByteArray());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//}
