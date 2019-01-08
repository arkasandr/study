package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddExcessElement() {
        SimpleArray<Integer> intArray = new SimpleArray<>(1);
        intArray.add(0);
        intArray.add(1);
    }

    @Test
    public void whenAddTwoElementsThenSumResult() {
        SimpleArray<Integer> intArray = new SimpleArray<>(2);
        intArray.add(1);
        intArray.add(2);
        int result = intArray.get(0) + intArray.get(1);
        assertThat(result, is(3));
    }

    @Test
    public void whenSetElementThenConcatenateResult() {
        SimpleArray<String> stringArray = new SimpleArray<>(2);
        stringArray.add("A");
        stringArray.add("B");
        stringArray.set(1, "C");
        String result = String.format("%s %s", stringArray.get(0), stringArray.get(1));
        assertThat(result, is("A C"));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteThenDeletingElement() {
        SimpleArray<String> stringArray = new SimpleArray<>(3);
        stringArray.add("AB");
        stringArray.add("BC");
        stringArray.add("CD");
        stringArray.remove(2);
        stringArray.get(2).length();
    }


    @Test
    public void shouldReturnEvenNumbersSequentially() {
        SimpleArray<Integer> intArray = new SimpleArray<>(3);
        intArray.add(6);
        intArray.add(7);
        intArray.add(8);
        Iterator<Integer> it = intArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }

}