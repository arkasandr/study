package ru.job4j.iterator;

import java.util.Iterator;

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
                    Integer result = 0;
                    changeIterator();
                    result = iterator.next();
                    changeIterator();
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
