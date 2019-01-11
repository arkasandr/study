package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {

    private AbstractStore<Role> roles = new AbstractStore<>(1);
    private Role role = new Role("Role7");

    private AbstractStore<User> users = new AbstractStore<>(1);
    private User user = new User("User7");


    @Test
    public void whenAddRole() {
        roles.add(role);
        Role result = roles.get(0);
        assertThat(result.getId(), is("Role7"));
    }

    @Test
    public void whenDeleteRole() {
        users.add(user);
        users.delete("User7");
        assertNull(users.get(0));
    }

    @Test
    public void whenReplaceRole() {
        User newUser = new User("User5");
        users.add(user);
        users.replace("User7", newUser);
        User result = users.get(0);
        assertThat(result.getId(), is("User5"));
    }

    @Test
    public void whenFindByIdRole() {
        roles.add(role);
        Role result = roles.findById("Role7");
        assertThat(result.getId(), is("Role7"));
    }

}