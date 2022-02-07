package com.javarush.task.task19.task1906;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = 0;
        int N = Integer.parseInt(reader.readLine());
        int c = N;
        maximum = N;
        if(N > 0) {
            for (int i = 0; i < c; i++) {
                N = Integer.parseInt(reader.readLine());
                if (N > maximum)
                    maximum = N;
            }
            System.out.println(maximum);
        }
    }
}
