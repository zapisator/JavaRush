package com.javarush.task.task36.task3604;

import static com.javarush.task.task36.task3604.RedBlackTree.Color.BLACK;
import static com.javarush.task.task36.task3604.RedBlackTree.Color.RED;

public class RedBlackTree {

    private static final Node EMPTY = new Node(0);

    static {
        EMPTY.left = EMPTY;
        EMPTY.right = EMPTY;
    }

    protected Node current;
    private Node parent;
    private Node grand;
    private Node great;
    private Node header;

    public RedBlackTree() {
        header = new Node(Integer.MIN_VALUE);
        header.left = EMPTY;
        header.right = EMPTY;
    }
    public boolean isEmpty() {
        return header.right == EMPTY;
    }

    public void clear() {
        header.right = EMPTY;
    }

    public void insert(int item) {
        current = grand = parent = header;
        EMPTY.element = item;
        while (current.element != item) {
            great = grand;
            grand = parent;
            parent = current;
            current = item > current.element ? current.right : current.left;

            if (current.left.color == RED && current.right.color == RED) {
                reorient(item);
            }
        }

        if (current != EMPTY) {
            return;
        }

        current = new Node(item, EMPTY, EMPTY);

        if (item < parent.element) {
            parent.left = current;
        } else {
            parent.right = current;
        }

        reorient(item);
    }

    protected void reorient(int item) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            grand.color = RED;
            if (item < grand.element != item < parent.element) {
                parent = rotate(item, grand);
            }
            current = rotate(item, great);
            current.color = BLACK;
        }

        header.right.color = BLACK;
    }

    private Node rotate(int item, Node parent) {
        if (item < parent.element) {
            final Node left = parent.left;

            parent.left = item < left.element
                    ? rotateWithLeftNode(left)
                    : rotateWithRightNode(left);
            return parent.left;
        } else {
            final Node right = parent.right;

            parent.right = item < right.element
                    ? rotateWithLeftNode(right)
                    : rotateWithRightNode(right);
            return parent.right;
        }
    }

    private Node rotateWithLeftNode(Node element) {
        final Node left = element.left;

        element.left = left.right;
        left.right = element;
        return left;
    }

    private Node rotateWithRightNode(Node element) {
        final Node right = element.right;

        element.right = right.left;
        right.left = element;
        return right;
    }

    public Node getHeader() {
        return header;
    }

    public enum Color {
        BLACK,
        RED;
    }

    public static class Node {

        private int element;
        private Node left;
        private Node right;

        private Color color;

        public Node(int element) {
            this(element, null, null);
        }

        public Node(int element, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.color = BLACK;
        }

        public boolean isEmpty() {
            return this == EMPTY;
        }

        public int getValue() {
            return element;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Color getColor() {
            return color;
        }

    }
}
