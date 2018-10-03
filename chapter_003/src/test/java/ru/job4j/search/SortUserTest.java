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
}















//                List<User> input = new ArrayList<>();
//                input.add(new User("Tom", 7));
//                input.add(new User("Jane", 6));
//                input.add(new User("Albert", 4));
//                input.add(new User("Jane", 17));
//                SortUser result = new SortUser();
//
//                for(User o: result.sort(input)) {
//                    System.out.println(o.name + " " + o.age);
//                }
//            }
//        }