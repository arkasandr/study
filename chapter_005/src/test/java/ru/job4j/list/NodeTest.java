package ru.job4j.list;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class NodeTest {

    @Test
    public void whenForthConnectWithFirst() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> forth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = first;
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void whenThirdConnectWithSecond() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> forth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = second;
        forth.next = first;
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void whenListHaveNoLoop() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> forth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        assertThat(first.hasCycle(first), is(false));
    }

}

