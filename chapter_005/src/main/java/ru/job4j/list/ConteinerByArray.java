package ru.job4j.list;

import java.util.*;

public class ConteinerByArray<E> implements Iterable<E> {

    private E[] conteiner;
    private int index;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;



    public ConteinerByArray() {
        this.conteiner = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ConteinerByArray(int size) {
        this.conteiner = (E[]) new Object[size];
    }

    /**
     * Метод вставляет элемент в контейнер.
     */
    public boolean add(E value) {

        if (index >= conteiner.length) {
            conteiner = Arrays.copyOf(this.conteiner, this.conteiner.length * 2);
        }
            conteiner[index++] = value;
            modCount++;
        return true;
    }

    /**
     * Метод возвращает индекс элемента в контейнере.
     */
    public E get(int index) {
        Objects.checkIndex(index, conteiner.length);
        return this.conteiner[index];
    }


    @Override
        public Iterator<E> iterator() {
            return new Iterator<>() {
                private int position = 0;
                private int expectedModCount = modCount;

                @Override
                public boolean hasNext() {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                        return this.position < index;
                }

                @Override
                public E next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return (E) conteiner[position++];
                }
            };
        }
}
