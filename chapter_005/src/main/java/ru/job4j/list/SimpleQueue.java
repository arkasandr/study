package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> st1;
    private SimpleStack<T> st2;

    SimpleQueue() {
        st1 = new SimpleStack<>();
        st2 = new SimpleStack<>();
    }

    /**
     * Метод вставляет элемент в стэк.
     */
    public void push(T value) {
            st1.push(value);
    }

    /**
     * Метод удаляет последний добавленный в стэк элемент.
     */

    public T poll() {
        if (st2.isEmpty()) {
        while (!st1.isEmpty()) {
            st2.push(st1.poll());
        }
        }
        return st2.poll();
    }
}
