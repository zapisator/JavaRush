package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/* 
Знания - сила!
*/

import static com.javarush.task.task24.task2412.Solution.Stock.NAME;
import static com.javarush.task.task24.task2412.Solution.Stock.SYMBOL;
import static com.javarush.task.task24.task2412.Solution.Stock.OPEN;
import static com.javarush.task.task24.task2412.Solution.Stock.LAST;
import static com.javarush.task.task24.task2412.Solution.Stock.DATE;
import static com.javarush.task.task24.task2412.Solution.Stock.CHANGE;

public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"change {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get(NAME));
            String symbol = (String) stock.get(SYMBOL);
            double open = !stock.containsKey(OPEN) ? 0 : ((double) stock.get(OPEN));
            double last = !stock.containsKey(LAST) ? 0 : ((double) stock.get(LAST));
            double change = !stock.containsKey(CHANGE) ? 0 : ((double) stock.get(CHANGE));
            Date date = (Date) stock.get(DATE);
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort((stock1, stock2) -> Comparator.comparing(Stock::name)
                .thenComparing(Stock::date, Comparator.reverseOrder())
                .thenComparing(Stock::change, Comparator.reverseOrder())
                .compare(stock1, stock2));
    }

    public static class Stock extends HashMap<String, Object> {

        static final String NAME = "name";
        static final String SYMBOL = "symbol";
        static final String OPEN = "open";
        static final String LAST = "last";
        static final String DATE = "date";
        static final String CHANGE = "change";

        public Stock(String name, String symbol, double open, double last) {
            put(NAME, name);
            put(SYMBOL, symbol);
            put(OPEN, open);
            put(LAST, last);
            put(DATE, getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put(NAME, name);
            put(SYMBOL, symbol);
            put(DATE, date);
            put(CHANGE, change);
        }

        public String name() {
            return (String) get(NAME);
        }

        public String symbol() {
            return (String) get(SYMBOL);
        }

        public double open() {
            return (double) get(OPEN);
        }

        public double last() {
            return (double) get(LAST);
        }

        public LocalDate date() {
            return ((Date) get(DATE)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        public double change() {
            return (double) get(CHANGE);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();

        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("A", "SIRI", 2, calendar.getTime()));
        stocks.add(new Stock("A", "CMCSA", -.03, calendar.getTime()));
        stocks.add(new Stock("A", "CMCSA", .03, calendar.getTime()));
        stocks.add(new Stock("A", "CMCSA", -.03, calendar.getTime()));
        stocks.add(new Stock("A", "SIRI", -.03, calendar.getTime()));
        stocks.add(new Stock("A", "SIRI", .03, calendar.getTime()));
        stocks.add(new Stock("A", "SIRI", -.03, calendar.getTime()));
        stocks.add(new Stock("B", "SIRI", -.04, calendar.getTime()));
        stocks.add(new Stock("B", "SIRI", .03, calendar.getTime()));
        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

