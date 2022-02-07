package com.javarush.task.task33.task3308;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class Shop {

    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    @Override
    public String toString() {
        return "Shop{" +
                "goods=" + goods +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + Arrays.toString(secretData) +
                '}';
    }

    private static class Goods {

        public List<String> names;

        public Goods() {
        }

        @Override
        public String toString() {
            return "Good{" +
                    "names=" + names +
                    '}';
        }
    }

}
