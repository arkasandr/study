package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

        private AbstractStore<Role> store = new RoleStore(1);
        private Role role = new Role("7");

        @Test
        public void whenAddRole() {
            store.add(role);
            Role result = store.get(0);
            assertThat(result.getId(), is("7"));
        }

        @Test
        public void whenDeleteRole() {
            store.add(role);
            store.delete("7");
            assertNull(store.get(0));
        }

        @Test
        public void whenReplaceRole() {
            Role newUser = new Role("5");
            store.add(role);
            store.replace("7", newUser);
            Role result = store.get(0);
            assertThat(result.getId(), is("5"));
        }

        @Test
        public void whenFindByIdRole() {
            store.add(role);
            Role result = store.findById("7");
            assertThat(result.getId(), is("7"));
        }

}