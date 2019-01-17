package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConteinerByLinkedList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private int modCount = 0;

    public ConteinerByLinkedList() {
    }


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



    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int position = 0;
            private int expectedModCount = modCount;
            Node<E> result = new Node<>(null, first);

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.position != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                    result = result.next;
                return result.date;
            }
        };
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

        Node(E date,  Node<E> next) {
            this.date = date;
           this.next = next;
        }
    }
}
