package com.javarush.task.pro.task13.task1313;

import java.security.SecureRandom;

public class StringsLinkedList {
    private Node first;
    private Node last;

    public StringsLinkedList() {
        first = new Node();
        last = new Node();
    }

    public void printAll() {
        Node currentElement = first.next;
        while ((currentElement) != null) {
            System.out.println(currentElement.value);
            currentElement = currentElement.next;
        }
    }

    public void add(String value) {
        if (first.next == null) {
            Node current = new Node(first, value, last);
            first.next = current;
            last.prev = current;
        } else {
            Node prev = last.prev;
            Node current = new Node(prev, value, last);
            prev.next = current;
            last.prev = current;
        }
    }

    public static class Node {
        private Node prev;
        private final String value;
        private Node next;

        public Node() {
            prev = null;
            value = null;
            next = null;
        }

        public Node(Node prev, String value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
