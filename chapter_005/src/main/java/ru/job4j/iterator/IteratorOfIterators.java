package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfIterators {

    private Iterator it;

        Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
            return new Iterator<Integer>() {
                Iterator<Integer> iterator = it.next();

                @Override
                public boolean hasNext() {
                    changeIterator();
                    return iterator.hasNext();
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Integer result;
                    result = iterator.next();
                    return result;
                }


                void changeIterator() {
                    while (!iterator.hasNext() && it.hasNext()) {
                        iterator = it.next();
                    }
                    return;

                }
            };
        }

}
