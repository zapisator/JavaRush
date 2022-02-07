package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        Second s = (Second) convertOneToAnother(new First(), Second.class);
//        First f = (First) convertOneToAnother(new Second(), First.class);

        First first = new First();
        first.i = 1;
        first.name = "first";
        Second second = new Second();
        second.i = 2;
        second.name = "second";

        Second s = (Second) convertOneToAnother(first, Second.class);
        First f = (First) convertOneToAnother(second, First.class);

        System.out.println("f.i: " + f.i + " f.name: " + f.name);
        System.out.println("s.i: " + s.i + " s.name: " + s.name);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        final Writer writer = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(writer, one);
        final Reader reader = new StringReader(
                writer.toString().replaceFirst(
                        one.getClass().getSimpleName().toLowerCase(Locale.ROOT),
                        resultClassObject.getSimpleName().toLowerCase(Locale.ROOT)
                )
        );

        return mapper.readValue(reader, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;
    }
}
