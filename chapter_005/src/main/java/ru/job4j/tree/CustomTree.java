package ru.job4j.tree;

import java.util.*;

public class CustomTree<E extends Comparable<E>> implements SimpleTree<E> {
    private final Node<E> root;
    private int modCount = 0;

    public CustomTree(E root) {
        this.root = new Node(root);
    }


    @Override
    public Optional<Node> findBy(E value) {
        Optional<Node> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node> searchedParent = findBy(parent);
        if (searchedParent.isPresent()) {
            Optional<Node> searchedChild = findBy(child);
            if (!searchedChild.isPresent()) {
                searchedParent.get().add(new Node(child));
                modCount++;
                result = true;
            }
        }

        return result;
    }






    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int count = modCount;
            Node<E> temp = root;
            Queue<Node<E>> data = new LinkedList<>();

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return temp != null;
            }


            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result;
                for (Node<E> node : temp.leaves()) {
                    data.offer(node);
                }
                result = temp.getValue();
                temp = data.poll();
                return result;
            }
        };
    }
}
