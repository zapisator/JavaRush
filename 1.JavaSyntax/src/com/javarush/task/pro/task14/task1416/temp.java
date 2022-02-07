package com.javarush.task.pro.task14.task1416;

public class temp {

    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
            System.out.println("===");
            System.out.println(e.getStackTrace());
        }
    }

}
