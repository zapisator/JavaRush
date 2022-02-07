package com.javarush.task.pro.task18.task1803;

import java.util.Comparator;

/* 
Наставники JavaRush
*/

public class NameComparator implements Comparator<JavaRushMentor> {

    @Override
    public int compare(JavaRushMentor javaRushMentor, JavaRushMentor t1) {
        return javaRushMentor.getName().length() - t1.getName().length();
    }
}

