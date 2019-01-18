package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddDuplicate(){
        SimpleSet<String> set = new SimpleSet<>();
        assertThat(set.add("A"), is(true));
        assertThat(set.add("A"), is(false));
    }

    @Test
    public void whenAddThreeValuesAndIterate(){
        SimpleSet<String> set = new SimpleSet<>();
        set.add("s");
        set.add("a");
        set.add("m");
        Iterator<String> iterator = set.iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next());
        }
        assertThat(result.toString(), is("sam"));

    }

    @Test
    public void whenAddTwoValuesAndIterate(){
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(false));
    }

}
