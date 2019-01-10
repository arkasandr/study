package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class UserStoreTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddExcessElement() {
        SimpleArray<User> users = new SimpleArray<>(1);
        users.add(new User("1"));
        users.add(new User("2"));
    }

    private UserStore<User> store = new UserStore(2);
    private User user = new User("7");

    @Test
    public void whenAddUser() {
        store.add(user);
        User result = store.get(0);

        assertThat(result.getId(), is("7"));
    }

//    @Test
//    public void whenDeleteUser() {
//        store.add(user);
//        store.delete(0);
//
//        assertNull(store.get(0));
//    }

    @Test
    public void whenReplaceUser() {
        User newUser = new User("5");

        store.add(user);
        store.replace(0, newUser);
        User result = store.get(0);

        assertThat(result.getId(), is("5"));
    }



}