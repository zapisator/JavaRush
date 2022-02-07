package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {

    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final String fileName = System.getProperty("user.dir")
                + "/2.JavaCore/src/com/javarush/task/task20/task2022/file.txt";
        final Solution solution = new Solution(fileName);

        solution.writeObject("Hello, World!");
        try (
                final FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                final FileInputStream fileInputStream = new FileInputStream(fileName);
                final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            objectOutputStream.writeObject(solution);
            final Solution solution1 = (Solution) objectInputStream.readObject();
            System.out.println(solution1.fileName.equals(solution.fileName));
        }
    }
}
