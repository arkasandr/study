package ru.job4j.search;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void sortUsersByAge() {
        List<User> input = new ArrayList<>();
        input.add(new User("Tom", 7));
        input.add(new User("Jane", 6));
        input.add(new User("Albert", 4));
        input.add(new User("Jane", 17));
        SortUser c = new SortUser();

    List<Integer> result = new ArrayList<>();
            for (User a : c.sort(input)) {
                result.add(a.getAge());
            }

        List<Integer> expect = Arrays.asList(
                4, 6, 7, 17
        );
        assertThat(result, is(expect));
    }


    @Test
    public void sortUsersByNameLength() {
        List<User> input = new ArrayList<>();
        input.add(new User("Tom", 7));
        input.add(new User("Jane", 6));
        input.add(new User("Albert", 4));
        input.add(new User("Jane", 17));
        input.add(new User("Bob", 10));
        SortUser d = new SortUser();

        List<String> result = new ArrayList<>();
        for (User a : d.sortNameLength(input)) {
            result.add(a.getName());
        }

        List<String> expect = Arrays.asList(
                "Tom", "Bob", "Jane", "Jane", "Albert"
        );
        assertThat(result, is(expect));
    }

    @Test
    public void sortUsersByNameFirstThenByAge() {
        List<User> input = new ArrayList<>();
        input.add(new User("Tom", 7));
        input.add(new User("Jane", 6));
        input.add(new User("Albert", 4));
        input.add(new User("Jane", 17));
        input.add(new User("Bob", 10));
        SortUser b = new SortUser();

        List<String> result = new ArrayList<>();
        for (User a : b.sortByAllFields(input)) {
            result.add(a.getName() + ", " + a.getAge());
        }

        List<String> expect = Arrays.asList(
                "Albert, 4", "Bob, 10", "Jane, 6", "Jane, 17", "Tom, 7"
        );
        assertThat(result, is(expect));
    }


}



