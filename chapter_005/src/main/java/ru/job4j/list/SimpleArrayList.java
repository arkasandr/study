package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент в списке.
     */
    public E delete() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        this.first = first.next;
        return result.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Метод проверяет наличие первого элемента.
     */
    public boolean isEmpty() {
        return (this.first == null);
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
