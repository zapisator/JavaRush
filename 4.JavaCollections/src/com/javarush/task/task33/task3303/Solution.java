package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/

public class Solution {

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz)
            throws IOException {
        return new ObjectMapper().readValue(new File(fileName), clazz);
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        final String fileName = args[0];
        final String className = args[1];

        final Object t = convertFromJsonToNormal(fileName, Class.forName(className));
    }

}
