package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/

public class Solution implements Cloneable {

    protected Map<String, User> users = new LinkedHashMap();

    @Override
    public Solution clone() throws CloneNotSupportedException {
        super.clone();
        final Solution clone = new Solution();

        clone.users = new LinkedHashMap<>();
        this.users.forEach(((s, user) -> {
            try {
                clone.users.put(new String(s), user.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }));
        return clone;
    }

    public static class User implements Cloneable {

        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public User clone() throws CloneNotSupportedException {
            super.clone();

            return new User(age, name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof User)) {
                return false;
            }

            User user = (User) obj;
            return age == user.age && name.equals(user.name);
        }

        @Override
        public String toString() {
            return String.format("[%d; %s]", age, name);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }
}
