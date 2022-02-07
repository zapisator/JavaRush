package com.javarush.task.task20.task2018;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class App {

    // Java program to demonstrate
    // the case if superclass need
    // not to be serializable
    // while serializing subclass


    // superclass A
    // A class doesn't implement Serializable
    // interface.
    static class A {

        protected String nameA = "A";

        // parameterized constructor
        public A(String nameA) {
            this.nameA += nameA;
        }

        // default constructor
        // this constructor must be present
        // otherwise we will get runtime exception
        public A() {
            System.out.println("A's class constructor called");
        }

    }

    // subclass B
// implementing Serializable interface
    static class B extends A implements Serializable, Externalizable {

        private String nameB;

        public B() {
        }

        // parameterized constructor
        public B(String nameA, String nameB) {
            super(nameA);
            this.nameA += nameA;
            this.nameB = nameB;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(nameA);
            out.writeObject(nameB);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            nameA = (String) in.readObject();
            nameB = (String) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        B b = new B("B2", "C33");

        System.out.println("nameA: " + b.nameA + ", nameB: " + b.nameB);

        // Serializing B's(subclass) object
        final String fileName = "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2018/abc.ser";
        //Saving of object in a file
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // Method for serialization of B's class object
        oos.writeObject(b);

        // closing streams
        oos.close();
        fos.close();

        System.out.println("Object has been serialized");

        // De-Serializing B's(subclass) object

        // Reading the object from a file
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Method for de-serialization of B's class object
        B b1 = (B) ois.readObject();

        // closing streams
        ois.close();
        fis.close();

        System.out.println("Object has been deserialized");

        System.out.println("nameA: " + b1.nameA + ", nameB: " + b1.nameB);
    }
}

