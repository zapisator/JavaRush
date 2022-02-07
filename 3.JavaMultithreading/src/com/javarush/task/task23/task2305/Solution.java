package com.javarush.task.task23.task2305;

/* 
Inner
*/

import java.util.Arrays;

public class Solution {

    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {

    }

    public static Solution[] getTwoSolutions() {
        return Arrays.stream(new Solution[]{new Solution(), new Solution()})
                .peek(solution ->
                        solution.innerClasses = new InnerClass[]{
                                solution.new InnerClass(),
                                solution.new InnerClass()
                        })
                .toArray(Solution[]::new);
    }

    public static void main(String[] args) {
        Arrays.stream(getTwoSolutions())
                .forEach(
                        solution -> System.out.println(Arrays.deepToString(solution.innerClasses)));
    }
}
