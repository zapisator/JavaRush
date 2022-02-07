package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws Exception{
//        args = new String[]{"span"};

        Elements element = Jsoup
                .parse(
                        content(fileName()),
                        "hh.ru",
                        Parser.xmlParser())
                .select(args[0]);
        System.out.println(element);
    }

    private static String content(String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final StringJoiner joiner = new StringJoiner("");

            while (reader.ready()) {
                joiner.add(reader.readLine());
            }
            return joiner.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileName() {
//        return "/home/sb_work/Загрузки/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/tags.html";
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
