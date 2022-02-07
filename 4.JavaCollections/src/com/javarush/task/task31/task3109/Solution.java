package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {

    public static void main(String[] args) {
        final List<String> fileNames = Arrays.asList(
                "binary",          "binary.dat",        "binary.txt",        "binary.xml",
                "properties",     "properties.dat",     "properties.txt",     "properties.xml",
                "xml",
                "properties_xml", "properties_xml.dat", "properties_xml.txt", "properties_xml.xml",
                "not_exists");
        for (String fileName : fileNames) {
            printProperties(fileName);
        }

    }

    private static void printProperties(String fileName) {
        final Solution solution = new Solution();
        final Properties properties = solution.getProperties(
                "4.JavaCollections/src/com/javarush/task/task31/task3109/"
                        + fileName
        );

        System.out.println("fileName: " + fileName);
        properties.list(System.out);
        System.out.println();
    }


    public Properties getProperties(String fileName) {
        final Path path = Paths.get(fileName);
        Properties properties = new Properties();

        try {
            if (path.toString().endsWith(".xml")) {
                properties.loadFromXML(new FileInputStream(fileName));
            } else {
                properties.load(new FileInputStream(fileName));
            }
            return properties;
        } catch (IOException e) {
            System.out.println("Something went wrong during parsing the file.\n");
        }
        return properties;
    }
//    public Properties getProperties(String fileName) {
//        final Path path = Paths.get(fileName);
//        Properties properties = new Properties();
//
//        try {
//            final String mime =
//                    Files.exists(path) ? Files.probeContentType(path) : "";
//
//            if (mime.equals("text/xml") || mime.equals("application/xml")) {
//                properties.loadFromXML(new FileInputStream(fileName));
//            } else if (mime.equals("text/plain")) {
//                properties.load(new FileInputStream(fileName));
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong during parsing the file.\n");
//        }
//        return properties;
//    }
}
