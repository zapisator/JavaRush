package com.javarush.task.task20.task2013;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.StringJoiner;

/* 
Externalizable Person
*/

public class Solution {

    public static class Person implements Externalizable {

        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return new StringJoiner("; ", "[", "]")
                    .add(firstName)
                    .add(lastName)
                    .add(String.valueOf(age))
                    .toString();
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List<Person>) in.readObject();
        }
    }

    public static void main(String[] args) {
        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2013/file.txt";

        final Person person1 = new Person("Ivan", "Ivanov", 25);
        System.out.println(person1);
        try (
                final OutputStream outputStream = new FileOutputStream(fileName);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                final InputStream inputStream = new FileInputStream(fileName);
                final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            objectOutputStream.writeObject(person1);
            final Person person2 = (Person) objectInputStream.readObject();
            System.out.println(person2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
