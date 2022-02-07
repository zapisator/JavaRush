package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.stream.Stream;
import javax.xml.bind.DatatypeConverter;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        return Stream.of(MessageDigest.getInstance("MD5"))
                .peek(messageDigest -> messageDigest.update(byteArrayOutputStream.toByteArray()))
                .map(MessageDigest::digest)
                .map(DatatypeConverter::printHexBinary)
                .allMatch(s -> s.equalsIgnoreCase(md5));

//        final MessageDigest grinder = MessageDigest.getInstance("MD5");
//
//        grinder.update(byteArrayOutputStream.toByteArray());
//        return DatatypeConverter
//                .printHexBinary(
//                        grinder.digest()
//                ).equalsIgnoreCase(md5);
    }
}
