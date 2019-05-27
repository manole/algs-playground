package datastructure;

public class Queue {
    private int size = 0;
    private Node head;
    private Node tail;

    private class Node {
        private int val;
        private Node next;
        private Node previous;
    }

    private void addFirst(int val) {
        Node node = new Node();
        node.val = val;
        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
        }
        size++;
        head = node;
    }

    private int removeLast() {
        final int val;
        if (tail != null) {
            val = tail.val;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.previous;
                tail.next = null;
            }
        } else {
            throw new IllegalStateException();
        }
        size--;
        return val;
    }
}
