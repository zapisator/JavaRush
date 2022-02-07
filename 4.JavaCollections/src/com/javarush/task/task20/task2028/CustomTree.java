package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    @Override
    public int size() {
        return size(root) - 1;
    }

    private int size(Entry<String> entry) {
        return entry == null ? 0 : 1 + size(entry.leftChild) + size(entry.rightChild);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
        final LinkedList<Entry<String>> entries = new LinkedList<>();
        entries.add(root);
        Entry<String> entry = foundEntry(entries, element -> s.equals(element.elementName));
        entry = entry.elementName.equals(s) ? entry : null;
        return entry == null || entry.parent == null ? null : entry.parent.elementName;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        final LinkedList<Entry<String>> entries = new LinkedList<>();
        entries.add(root);
        Entry<String> entry = foundEntry(entries, Entry::isAvailableToAddChildren);
        if (!entry.isAvailableToAddChildren()) {
            fix();
            return add(s);
        }
        return entry.addChild(new Entry<>(s));
    }

    private void fix() {
        final LinkedList<Entry<String>> entries = new LinkedList<>();
        entries.add(root);
        Entry<String> entry = foundEntry(entries, entry1 ->
                !entry1.isAvailableToAddChildren()
                        && (entry1.leftChild == null || entry1.rightChild == null));
        if (entry.leftChild == null) {
            entry.availableToAddLeftChildren = true;
        } else {
            entry.availableToAddRightChildren = true;
        }
    }

    private Entry<String> foundEntry(
            LinkedList<Entry<String>> entries, Predicate<Entry<String>> predicate
    ) {
        Entry<String> entry = null;

        while ((entry == null || !predicate.test(entry)) && !entries.isEmpty()) {
            entry = entries.pop();
            addChildrenLast(entries, entry);
        }
        return entry;
    }

    private void addChildrenLast(LinkedList<Entry<String>> entries, Entry<String> entry) {
        if (entry.leftChild != null) {
            entries.addLast(entry.leftChild);
        }
        if (entry.rightChild != null) {
            entries.addLast(entry.rightChild);
        }
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }

        final String s = (String) o;
        final LinkedList<Entry<String>> entries = new LinkedList<>();

        entries.add(root);

        Entry<String> entry = foundEntry(entries, element -> s.equals(element.elementName));
        if (entry.elementName.equals(s)) {
            entry.remove();
            return true;
        }
        return false;
    }



    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CustomTree clone() {
        try {
            return (CustomTree) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        public boolean addChild(Entry<T> entry) {
            if (isAvailableToAddChildren()) {
                if (availableToAddLeftChildren) {
                    leftChild = entry;
                    availableToAddLeftChildren = false;
                    entry.parent = this;
                } else {
                    rightChild = entry;
                    availableToAddRightChildren = false;
                    entry.parent = this;
                }
                return true;
            }
            return false;
        }

        public void remove() {
            final Entry<T> parent = this.parent;

            if (parent.leftChild == this) {
                parent.leftChild = null;
//                parent.availableToAddLeftChildren = true;
            } else {
                parent.rightChild = null;
//                parent.availableToAddRightChildren = true;
            }
        }
    }

    public static void main(String[] args) {
        new CustomTree();
        new Entry<String>("");
    }

}
