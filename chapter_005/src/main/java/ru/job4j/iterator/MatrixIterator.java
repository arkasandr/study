package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private Integer[][] value;
    private int i, j;

    public MatrixIterator(Integer[][] value) {
        this.value = value;
    }


    @Override
    public boolean hasNext() {
        return (i < value.length && j < value[i].length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            int number = value[i][j];
            j++;
            while (i < value.length && j >= value[i].length) {
                j = 0;
                i++;
        }
        return number;
    }
}
