package com.javarush.task.task35.task3502;

import com.javarush.task.task35.task3502.Solution.SomeClass;
import java.util.List;

/* 
Знакомство с дженериками
*/

public class Solution <S extends List<SomeClass>> {
    public static class SomeClass<N extends Number> {
    }

    public static void main(String[] args) {

    }
}
