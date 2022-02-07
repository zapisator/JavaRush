package com.javarush.task.task14.task1411;

import com.javarush.task.task14.task1411.Person.Coder;
import com.javarush.task.task14.task1411.Person.Loser;
import com.javarush.task.task14.task1411.Person.Proger;
import com.javarush.task.task14.task1411.Person.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        while (isRoleOfFour(key = reader.readLine()))
        {
            person = person(key);
            doWork(person);
        }
    }

    private static Person person(String key) {
        switch (key) {
            case Person.USER:
                return new User();
            case Person.LOSER:
                return new Loser();
            case Person.CODER:
                return new Coder();
            case Person.PROGER:
                return new Proger();
        }
        return null;
    }

    private static boolean isRoleOfFour(String role) {
        return role.equals(Person.CODER)
                || role.equals(Person.LOSER)
                || role.equals(Person.PROGER)
                || role.equals(Person.USER);
    }

    public static void doWork(Person person) {
        if (person instanceof User) {
            ((User)person).live();
        } else if (person instanceof Loser) {
            ((Loser) person).doNothing();
        } else if (person instanceof Coder) {
            ((Coder) person).writeCode();
        } else if (person instanceof Proger) {
            ((Proger) person).enjoy();
        }
    }
}
