package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javafx.util.Pair;

/* 
Добавление файла в архив
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        List<Content> entries = getContents(args[1]);

        FileOutputStream zipFile = new FileOutputStream(args[1]);
        ZipOutputStream zip = new ZipOutputStream(zipFile);

        //кладем в него  ZipEntry –«архивный объект»
        File file = new File(args[0]);
        zip.putNextEntry(new ZipEntry("new/" + file.getName()));

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        Files.copy(file.toPath(), zip);

        //копируем все остальные файлы
        for (Content content : entries) {
            if (!content.getFileName().equals("new/" + file.getName())) content.saveToZip(zip);
        }

        // закрываем архив
        zip.close();
    }

    private static List<Content> getContents(String arg) throws IOException {
        List<Content> entries = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arg))) {
            ZipEntry currentEntry;
            byte[] buffer = new byte[1024];
            while ((currentEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                entries.add(new Content(currentEntry.getName(), outputStream.toByteArray()));
            }
        }
        return entries;
    }

    static class Content {
        String fileName;
        byte[] body;

        Content(String fileName, byte[] body) {
            this.fileName = fileName;
            this.body = body;
        }

        public String getFileName() {
            return fileName;
        }

        void saveToZip(ZipOutputStream zip) throws IOException {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zip.putNextEntry(zipEntry);
            zip.write(body);
            zip.closeEntry();
        }
    }
}
//public class Solution {
//
//    public static void main(String[] args) throws IOException {
////        args = new String[]{
////                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/Solution.java",
////                "/home/sb_work/Загрузки/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/zip.zip"
////        };
//        /**/
//        final Path archive = Paths.get(args[1]);
//
//        List<Pair<ZipEntry, byte[]>> zipEntries;
//        try (final ZipInputStream inputStream = new ZipInputStream(
//                new BufferedInputStream(Files.newInputStream(Paths.get(args[1]))))) {
//            zipEntries = zipEntries(inputStream);
//        }
//
//        try (final BufferedInputStream bufferedInputStream = new BufferedInputStream(
//                Files.newInputStream(Paths.get(args[0])))) {
//            final String newFileName = Paths.get("new/")
//                    .resolve(Paths.get(args[0]).getFileName())
//                    .toString();
//            final ZipEntry entry = new ZipEntry(newFileName);
//
//            zipEntries.removeIf(pair -> newFileName.equals(pair.getKey().getName()));
//            zipEntries.add(new Pair<>(entry, content(bufferedInputStream)));
//        }
//
//        try (final ZipOutputStream outputStream = new ZipOutputStream(
//                Files.newOutputStream(archive))) {
//            for (Pair<ZipEntry, byte[]> zipEntry : zipEntries) {
//                final ZipEntry entry = new ZipEntry(zipEntry.getKey());
//                final byte[] buffer = zipEntry.getValue();
//
//                outputStream.putNextEntry(entry);
//                if (!entry.isDirectory()) {
//                    outputStream.write(buffer);
//                }
//            }
//        }
//    }
//
//    private static byte[] content(BufferedInputStream bufferedInputStream) throws IOException {
//        final byte[] buffer = new byte[bufferedInputStream.available()];
//
//        bufferedInputStream.read(buffer);
//        return buffer;
//    }
//
//    private static List<Pair<ZipEntry, byte[]>> zipEntries(ZipInputStream inputStream) {
//        final List<Pair<ZipEntry, byte[]>> entries = new LinkedList<>();
//
//        try {
//            ZipEntry entry = inputStream.getNextEntry();
//
//            while (entry != null) {
//                final byte[] buffer = copy(inputStream);
//
//                entries.add(new Pair<>(entry, entry.isDirectory() ? null : buffer));
//                entry = inputStream.getNextEntry();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return entries;
//    }
//
//    private static byte[] copy(ZipInputStream inputStream)
//            throws IOException {
//        final ByteArrayOutputStream out = new ByteArrayOutputStream();
//        byte[] buffer = new byte[8 * 1024];
//        int len;
//
//        while ((len = inputStream.read(buffer)) > 0) {
//            out.write(buffer, 0, len);
//        }
//        return out.toByteArray();
//    }
//}
