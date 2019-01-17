package ru.job4j.list;

public class Node<T> {

    private T value;
   Node<T> next;
    private int size;
    private Node<T> first;

    Node(T value) {
        this.value = value;
    }

    public void add(T date) {
        Node<T> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод определяет имеет ли список замыкание.
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node pointerOne = first, pointerTwo = first;
        while (pointerOne != null && pointerTwo != null && pointerTwo.next != null) {
            pointerOne = pointerOne.next;
            pointerTwo = pointerTwo.next.next;
            if (pointerOne == pointerTwo) {
                result = true;
                break;
            }
        }
        return result;
    }

}
