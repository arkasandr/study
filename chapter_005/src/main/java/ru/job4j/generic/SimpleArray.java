package ru.job4j.generic;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    private final Object[] array;
    private int index = 0;

    public SimpleArray(int length) {
        this.array = new Object[length];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index != array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }

    /**
     * Метод заменяет указанным элементом (model) элемент, находящийся по индексу index;
     */
    public void set(int index, T model) {
        if (!(index < array.length)) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = model;
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку;
     */
    public T add(T model) {
        if (this.index == array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[this.index++] = model;
        return model;
    }

    /**
     * Метод удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть
     * на единицу влево (в середине массива не должно быть пустых ячеек);
     */
    public void remove(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = null;
        System.arraycopy(array, index+1, array, index, array.length - index  - 1);
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int index) {
       return (T) array[index];
    }
}
