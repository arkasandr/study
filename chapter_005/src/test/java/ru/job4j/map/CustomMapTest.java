package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomMapTest {

    @Test
    public void whenOnlyHashCodeIsOverride() {
        User user1 = new User("Alex", 1, new GregorianCalendar(1985, 8, 8));
        User user2 = new User("Alex", 1, new GregorianCalendar(1985, 8, 8));
        Map map = new HashMap<User, Object>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
