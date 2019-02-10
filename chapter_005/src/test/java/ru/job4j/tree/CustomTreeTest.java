package ru.job4j.tree;

import org.junit.Test;


import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CustomTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddSimilarValuesThenFalse() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.add(1, 2),
                is(false)
        );
    }

    @Test
    public void whenIteratorHasNextThenNext() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(1, 6);
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }


}