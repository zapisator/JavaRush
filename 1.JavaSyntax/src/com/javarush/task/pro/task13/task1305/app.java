package com.javarush.task.pro.task13.task1305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class app {

    public static void main(String[] args) {
        List<String> fake = Collections.nCopies(5, "Привет");

        ArrayList<String> list = new ArrayList<String>(fake);
        String str = list.get(0);

        for(String s: list) {
            System.out.println(str == s);
        }
        String one = new String("Привет");
        String two = new String("Привет");

        System.out.println(one == two);
        System.out.println(one.equals(two));

    }

}
