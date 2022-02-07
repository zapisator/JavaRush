package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        final Customer customer =  new Customer() {
            @Override
            public String getCompanyName() {
                return "JavaRush Ltd.";
            }

            @Override
            public String getCountryName() {
                return "Ukraine";
            }
        };
        final Contact contact = new Contact() {
            @Override
            public String getName() {
                return "Ivanov, Ivan";
            }

            @Override
            public String getPhoneNumber() {
                return "+38(050)123-45-67";
            }
        };
        final DataAdapter adapter = new DataAdapter(customer, contact);
        System.out.println(adapter.getCountryCode());
        System.out.println(adapter.getCompany());
        System.out.println(adapter.getContactFirstName());
        System.out.println(adapter.getContactLastName());
        System.out.println(adapter.getDialString());

    }

    public static class DataAdapter implements RowItem {

        private final Customer customer;
        private final Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            return countries.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(customer.getCountryName()))
                    .findFirst()
                    .map(stringStringEntry -> stringStringEntry.getKey())
                    .orElse("");
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            return contact.getName().split(", ")[1];
        }

        @Override
        public String getContactLastName() {
            return contact.getName().split(", ")[0];
        }

        @Override
        public String getDialString() {
            return "callto://"
                    + contact.getPhoneNumber()
                    .replaceAll("\\(", "")
                    .replaceAll("\\)", "")
                    .replaceAll("-", "");
        }
    }

    public interface RowItem {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        String getDialString();         //For example: callto://+380501234567
    }

    public interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}