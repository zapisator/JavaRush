package com.javarush.task.task20.task2002;

import com.javarush.task.task20.task2002.User.Country;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
//            File yourFile = File.createTempFile("your_file_name", null);
            final String yourFile = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2002/file.txt";
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.users = Arrays.asList(
                new User("Andrey", "Makarsky", new Date(), true, Country.RUSSIA),
                new User("Inna", "Skrypka", new Date(), false, Country.UKRAINE),
                new User("Zholy", "Bulsun", new Date(), true, Country.OTHER)
            );
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            assert javaRush.equals(loadedObject);
            loadedObject.users.forEach(System.out::println);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(users);
            }
        }

        @SuppressWarnings("unchecked")
        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            try (final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
                users = (List<User>) objectInputStream.readObject();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
