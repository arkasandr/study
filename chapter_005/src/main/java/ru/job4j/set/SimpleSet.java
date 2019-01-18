package ru.job4j.set;

import ru.job4j.list.ConteinerByArray;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private ConteinerByArray<E> container = new ConteinerByArray<>();

//    public boolean add(E value) {
//        boolean result = false;
//        if (!contains(value)) {
//            container.add(value);
//            result = true;
//        }
//        return result;
//    }

    /**
     *  Метод добавляет элемент во множество.
     */
    public boolean add(E value) {
        boolean result = true;
        for (E element : container) {
            if (element == null ? value == null : element.equals(value)) {
                result = false;
            }
        }
        container.add(value);
        return result;
    }


    /**
     *  Метод проверяет наличие дубликата во множестве.
     */
    public boolean contains(E value) {
        boolean result = false;
        Iterator<E> it = container.iterator();
        while (it.hasNext()) {
            if (value.equals(it.next())) {
                result = true;
                break;
            }
        }
      return result;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
