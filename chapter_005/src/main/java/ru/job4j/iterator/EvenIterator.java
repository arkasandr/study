package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {

    private int[] value;
    private int index = 0;

    public EvenIterator(int[] value) {
        this.value = value;
    }


    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return value[index++];
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        for (var i = index; i < value.length; i++) {
            if (value[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }
}
