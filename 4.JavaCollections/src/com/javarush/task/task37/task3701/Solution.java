package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T>{

        final Iterator<T> iterator = Solution.super.iterator();
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || Solution.this.size() > 0;
        }

        @Override
        public T next() {
            try {
                final Field fieldCursor = accessibleFieldCursor();
                final int cursorValue = fieldCursor.getInt(iterator);
                final int size = Solution.this.size();

                if (cursorValue >= size) {
                    fieldCursor.setInt(iterator, cursorValue % size);
                }
                return iterator.next();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        private Field accessibleFieldCursor() {
            final Field fieldCursor;

            try {
                fieldCursor = iterator.getClass().getDeclaredField("cursor");
                fieldCursor.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            return fieldCursor;
        }

        @Override
        public void remove() {
            iterator.remove();
        }

    }
}
