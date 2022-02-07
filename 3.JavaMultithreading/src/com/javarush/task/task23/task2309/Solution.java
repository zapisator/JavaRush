package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

/* 
Анонимность иногда так приятна!
*/

public class Solution {

    private static final String QUERY = "SELECT * FROM ";

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
        print(solution.getServers());
        print(solution.getSubjects());
        print(solution.getSubscriptions());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(
                    String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }

    private String queryByType(Class clazz) {
        final String[] typeNameParts = ((ParameterizedType)clazz.getGenericSuperclass())
                .getActualTypeArguments()[0]
                .getTypeName()
                .split("\\.");
        return typeNameParts[typeNameParts.length - 1].toUpperCase(Locale.ROOT);
    }

    public List<User> getUsers() {
        return new AbstractDbSelectExecutor<User>() {
            @Override
            public String getQuery() {
                return QUERY + queryByType(this.getClass());
            }
        }.execute();
    }

    public List<Location> getLocations() {
        return new AbstractDbSelectExecutor<Location>() {
            @Override
            public String getQuery() {
                return QUERY + queryByType(this.getClass());
            }
        }.execute();
    }

    public List<Server> getServers() {
        return new AbstractDbSelectExecutor<Server>() {
            @Override
            public String getQuery() {
                return QUERY + queryByType(this.getClass());
            }
        }.execute();
    }

    public List<Subject> getSubjects() {
        return new AbstractDbSelectExecutor<Subject>() {
            @Override
            public String getQuery() {
                return QUERY + queryByType(this.getClass());
            }
        }.execute();
    }

    public List<Subscription> getSubscriptions() {
        return new AbstractDbSelectExecutor<Subscription>() {
            @Override
            public String getQuery() {
                return QUERY + queryByType(this.getClass());
            }
        }.execute();
    }
}
