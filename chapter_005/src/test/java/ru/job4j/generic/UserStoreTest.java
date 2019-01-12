package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class UserStoreTest {

    private AbstractStore<User> store = new UserStore(1);
    private User user = new User("7");

    @Test
    public void whenAddUser() {
        store.add(user);
        User result = store.get(0);
        assertThat(result.getId(), is("7"));
    }

    @Test
    public void whenDeleteUser() {
        store.add(user);
        store.delete("7");
        assertNull(store.get(0));
    }

    @Test
    public void whenReplaceUser() {
        User newUser = new User("5");
        store.add(user);
        store.replace("7", newUser);
        User result = store.get(0);
        assertThat(result.getId(), is("5"));
    }

    @Test
    public void whenFindByIdUser() {
        store.add(user);
        User result = store.findById("7");
        assertThat(result.getId(), is("7"));
    }


}