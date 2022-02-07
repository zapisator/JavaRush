package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/* 
ClassLoader - что это такое?
*/

public class Solution {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Set<? extends Animal> allAnimals = getAllAnimals(
//                Solution.class
//                        .getProtectionDomain()
//                        .getCodeSource()
//                        .getLocation()
//                        .getPath()
//                        + Solution.class
//                        .getPackage()
//                        .getName()
//                        .replaceAll("[.]", "/")
//                        + "/data"
//        );

        Set<? extends Animal> allAnimals = getAllAnimals(
                "/home/sb_work/Загрузки/JavaRushTasks"
                        + "/out/production/4.JavaCollections"
                        + "/com/javarush/task/task35/task3507/data"
        );
        allAnimals.forEach(System.out::println);

    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        try {
            return Files.walk(Paths.get(pathToAnimals), 1)
                    .filter(Files::isRegularFile)
                    .map(Solution::classLoadedByFileName)
                    .filter(Solution::isProperClass)
                    .map(Solution::newInstance)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Animal newInstance(Class<?> clazz) {
        try {
            return (Animal) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> classLoadedByFileName(Path fileName) {
        try {
            return new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    final byte[] bytes = fileBytes(fileName);

                    return defineClass(name, bytes, 0, bytes.length);
                }
            }.findClass(qualifiedClassName(fileName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String qualifiedClassName(Path fileName) {
        return packageQualifiedName() + '.' + simpleClassName(fileName);
    }

    private static String packageQualifiedName() {
        final String packagePath = Solution.class
                .getPackage()
                .getName()
                + "/data";

        return packagePath.replaceAll(File.separator, ".");
    }

    private static String simpleClassName(Path fileName) {
        final String simpleClassName = fileName.getFileName().toString();

        return simpleClassName.substring(0, simpleClassName.lastIndexOf('.'));
    }

    private static byte[] fileBytes(Path file) {
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isProperClass(Class<?> clazz) {
        return doInheritAnimal(clazz) && hasPublicDefaultConstructor(clazz);
    }

    private static boolean doInheritAnimal(Class<?> clazz) {
        return Arrays.stream(clazz.getInterfaces())
                .map(Class::toString)
                .map(Solution::qualifiedToSimpleName)
                .anyMatch(simpleClassName -> simpleClassName.equals("Animal"));
    }

    private static String qualifiedToSimpleName(String name) {
        return name.substring(name.lastIndexOf('.') + 1);
    }

    private static boolean hasPublicDefaultConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);
    }

}
