package com.javarush.task.task28.task2801;

/* 
Осваиваем switch
*/

public class Solution {
    public static enum E1 {A, B, C, Y}

    public static enum E2 {D, E, F}

    public static enum E3 {D, E, F}

    public static final String E_1 = "E1";
    public static final String E_2 = "E2";

    public static void main(String[] args) {
        Solution.switchTest(E1.C);
        Solution.switchTest(E3.D);
        Solution.switchTest(E2.D);
        /* output
        it's E1.C
        undefined
        it's E2.D
         */
    }

    public static void switchTest(Enum obj) {
        final String name = obj.getClass().getSimpleName();
        final String prefix = "it's ";

        switch (name) {
            case E_1:
                System.out.println(prefix + E_1 + "." + obj.name());
                break;
            case E_2:
                System.out.println(prefix + E_2 + "." + obj.name());
                break;
            default:
                System.out.println("undefined");
        }
    }

    private static String className(Enum obj) {
        return obj.getClass().getSimpleName();
    }
}
