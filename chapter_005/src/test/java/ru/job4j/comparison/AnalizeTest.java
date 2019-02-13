package ru.job4j.comparison;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AnalizeTest {
    @Test
    public void whenHasNoChanges() {
        List<User> previous = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan")));
        List<User> current = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan")));
        Analize stat = new Analize();
        stat.diff(previous, current);
        assertThat(stat.diff(previous, current).toString(), is("added = 0, changed = 0, deleted = 0"));
    }

    @Test
    public void whenAddOneElementThenAddedIsOne() {
        List<User> previous = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan")));
        List<User> current = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan"), new User(3, "Peter")));
        Analize stat = new Analize();
        stat.diff(previous, current);
        assertThat(stat.diff(previous, current).toString(), is("added = 1, changed = 0, deleted = 0"));
    }

    @Test
    public void whenDeleteOneElementThenDeletedIsOne() {
        List<User> previous = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan"), new User(3, "Peter")));
        List<User> current = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan")));
        Analize stat = new Analize();
        stat.diff(previous, current);
        assertThat(stat.diff(previous, current).toString(), is("added = 0, changed = 0, deleted = 1"));
    }

    @Test
    public void whenChangeOneElementThenChangedIsOne() {
        List<User> previous = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan")));
        List<User> current = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Vano")));
        Analize stat = new Analize();
        stat.diff(previous, current);
        assertThat(stat.diff(previous, current).toString(), is("added = 0, changed = 1, deleted = 0"));
    }

    @Test
    public void whenAddOneChangeOneDeleteOneElementThenAllAreOne() {
        List<User> previous = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Ivan"), new User(3, "Peter")));
        List<User> current = new ArrayList<>(Arrays.asList(new User(1, "Alex"), new User(2, "Vano"), new User(4, "Sam")));
        Analize stat = new Analize();
        stat.diff(previous, current);
        assertThat(stat.diff(previous, current).toString(), is("added = 1, changed = 1, deleted = 1"));
    }





}