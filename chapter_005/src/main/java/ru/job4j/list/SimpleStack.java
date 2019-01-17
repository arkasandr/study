package ru.job4j.list;

public class SimpleStack<T> {

    private ConteinerByLinkedList<T> stack;

    public SimpleStack() {
        stack = new ConteinerByLinkedList<T>();
    }

    /**
     * Метод удаляет последний добавленный в стэк элемент.
     */
    public T poll() {
        return stack.delete();
    }

    /**
     * Метод вставляет элемент в стэк.
     */
    public void push(T value) {
        stack.add(value);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
