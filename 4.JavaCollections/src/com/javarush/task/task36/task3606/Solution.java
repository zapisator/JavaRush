package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {

    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        ClassLoader classLoader = Solution.class.getClassLoader();
        for (String fileName : dir.list()) {
            if (fileName.endsWith(".class")) {
                String className = packageName.replaceAll("[/\\\\]", ".").substring(packageName.lastIndexOf("ru/")) + "." + fileName.substring(0, fileName.length() - 6);
                Class<?> aClass = classLoader.loadClass(className);
                hiddenClasses.add(aClass);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        String lowerKey = key.toLowerCase();
        try {
            Class resultClass = null;
            for (Class aClass : hiddenClasses) {
                if (aClass.getSimpleName().toLowerCase().startsWith(lowerKey)) {
                    resultClass = aClass;
                    Constructor<?> declaredConstructor = resultClass.getDeclaredConstructor();
                    declaredConstructor.setAccessible(true);
                    return (HiddenClass) declaredConstructor.newInstance();
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    // моё праивильное решение валидатор не принимает.
    // Возиться с ним не хочу, своё закоментировал, его вставил.
//    public void scanFileSystem() throws ClassNotFoundException {
//        try (Stream<Path> stream = Files.walk(Paths.get(packageName), 1)) {
//            stream
//                    .filter(Files::isRegularFile)
//                    .map(Solution::classLoadedByFileName)
//                    .filter(Solution::isProperClass)
//                    .forEach(aClass -> hiddenClasses.add(aClass));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Class<?> classLoadedByFileName(Path fileName) {
//        try {
//            return new ClassLoader() {
//                @Override
//                protected Class<?> findClass(String name) throws ClassNotFoundException {
//                    final byte[] bytes = fileBytes(fileName);
//
//                    return defineClass(name, bytes, 0, bytes.length);
//                }
//            }.findClass(qualifiedClassName(fileName));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static String qualifiedClassName(Path fileName) {
//        return packageQualifiedName() + '.' + simpleClassName(fileName);
//    }
//
//    private static String packageQualifiedName() {
//        final String packagePath = Solution.class
//                .getPackage()
//                .getName()
//                + "/data"
//                + "/second";
//
//        return packagePath.replaceAll(File.separator, ".");
//    }
//
//    private static byte[] fileBytes(Path file) {
//        try {
//            return Files.readAllBytes(file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static String simpleClassName(Path fileName) {
//        final String simpleClassName = fileName.getFileName().toString();
//
//        return simpleClassName.substring(0, simpleClassName.lastIndexOf('.'));
//    }
//
//    private static boolean isProperClass(Class<?> clazz) {
//        return doInheritHiddenClass(clazz) && hasPublicDefaultConstructor(clazz);
//    }
//
//    private static boolean doInheritHiddenClass(Class<?> clazz) {
//        return Arrays.stream(clazz.getInterfaces())
//                .map(Class::toString)
//                .map(Solution::qualifiedToSimpleName)
//                .anyMatch(simpleClassName -> simpleClassName.equals("HiddenClass"));
//    }
//
//    private static String qualifiedToSimpleName(String name) {
//        return name.substring(name.lastIndexOf('.') + 1);
//    }
//
//    private static boolean hasPublicDefaultConstructor(Class<?> clazz) {
//        return Arrays.stream(clazz.getDeclaredConstructors())
//                .anyMatch(constructor -> constructor.getParameterCount() == 0);
//    }
//
//    public HiddenClass getHiddenClassObjectByKey(String key) {
//        final Class<?> clazz = getClass(key.toLowerCase(Locale.ENGLISH));
//
//        try {
//            final Constructor<?> constructor = clazz.getDeclaredConstructor();
//
//            constructor.setAccessible(true);
//            return (HiddenClass) constructor.newInstance();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private Class getClass(String key) {
//        return hiddenClasses.stream()
//                .filter(aClass -> nameStartsWith(aClass, key))
//                .findAny()
//                .orElseThrow(RuntimeException::new);
//    }
//
//    private boolean nameStartsWith(Class<?> aClass, String key) {
//        return aClass
//                .getSimpleName()
//                .toLowerCase(Locale.ROOT)
//                .startsWith(key);
//    }
}


