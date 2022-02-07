package com.javarush.task.task33.task3310.strategy;

import static com.javarush.task.task33.task3310.strategy.FileStorageStrategyMy.getReduce;
import static java.nio.file.attribute.PosixFilePermissions.fromString;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBucket {

    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("FileBucket_", ".temp");
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.setPosixFilePermissions(path, fromString("rw-rw-rw-"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path.toFile().deleteOnExit();
    }

    public static FileBucket createAndPut(List<Entry> entryList) {
        final Entry entry = getReduce(entryList);
        final FileBucket bucket = new FileBucket();

        bucket.putEntry(entry);
        return bucket;
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putEntry(Entry entry) {
        try (
                final OutputStream stream = Files.newOutputStream(path);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)
        ) {
            objectOutputStream.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) {
            return null;
        }

        try (
                final InputStream stream = Files.newInputStream(path);
                final ObjectInputStream objectInputStream = new ObjectInputStream(stream)
        ) {
            return (Entry) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
