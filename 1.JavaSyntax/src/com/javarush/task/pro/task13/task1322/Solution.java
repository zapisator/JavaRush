package com.javarush.task.pro.task13.task1322;

public class Solution {

    public static void main(String[] args) {
        for (int countOfCorners = -1; countOfCorners < 10; countOfCorners++) {
            System.out.println(getShapeNameByCountOfCorners(countOfCorners));
        }

    }

    public static String getShapeNameByCountOfCorners(int countOfCorner) {
        final String[] names = {
                "Треугольник", "Четырехугольник", "Пятиугольник",
                "Шестиугольник", "Семиугольник", "Восьмиугольник",
                "Другая фигура"
        };
        String shape;

        switch (countOfCorner) {
            case 3:
                ;
            case 4:
                ;
            case 5:
                ;
            case 6:
                ;
            case 7:
                ;
            case 8:
                shape = names[countOfCorner - 3];
                break;
            default:
                shape = names[names.length - 1];
        }
        return shape;
    }
}
