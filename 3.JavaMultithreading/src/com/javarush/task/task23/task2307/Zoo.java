package com.javarush.task.task23.task2307;

public class Zoo {
    private static int count = 7;
    private int mouseCount = 1;

    public static int getAnimalCount()
    {
        return count;
    }

    public int getMouseCount()
    {
        return mouseCount;
    }

    public static class Mouse
    {
        public Mouse()
        {
        }

    }
}
