package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomMapTest {

    @Test
    public void whenEqualsAndHashCodeAreOverride() {
        User user1 = new User("Alex", 1, new GregorianCalendar(1985, 7, 8));
        User user2 = new User("Alex", 2, new GregorianCalendar(1985, 7, 8));
        CustomMap map = new CustomMap<User, Object>();
        map.insert(user1, new Object());
        map.insert(user2, new Object());
        Iterator it = map.iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey().toString());
        }
    }

    @Test
    public void whenAddEqualsKeysThenFalse() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        assertThat(map.insert(3, "c"), is(true));
        assertThat(map.insert(1, "b"), is(false));
    }

    @Test
    public void whenInsertManyKeysThenSizeIncrease() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        map.insert(4, "d");
        map.insert(5, "e");
        map.insert(6, "f");
        assertThat(map.getTableLength(), is(16));
        map.insert(7, "g");
        map.insert(8, "h");
        map.insert(9, "k");
        map.insert(10, "j");
        map.insert(11, "i");
        map.insert(12, "m");
        assertThat(map.getTableLength(), is(32));
        assertThat(map.getValues().toString(), is("[a, b, c, d, e, f, g, h, k, j, i, m]"));
    }

    @Test
    public void whenGetTrueKey() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        assertThat(map.get(1), is("a"));
    }

    @Test
    public void whenGetFalseKey() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        assertThat(map.get(2) == null, is(true));
    }

    @Test
    public void whenInsertThreeKeysThenDeleteOne() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        map.delete(3);
        assertThat(map.getValues().toString(), is("[a, b]"));
    }

    @Test
    public void whenIterateMapAndHasNextThenNext() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        assertThat(iterator.next().getValue(), is("a"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("b"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoNextElementThenException() {
        CustomMap<Integer, String> map = new CustomMap<>();
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenInsertElementWhileIterateThenException() {
        CustomMap<Integer, String> map = new CustomMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        iterator.next();
        map.insert(4, "d");
        iterator.next();
    }
}
