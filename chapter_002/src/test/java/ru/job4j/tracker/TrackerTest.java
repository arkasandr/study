package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


    public class TrackerTest {

        @Test
        public void whenFindByIdThenTrackerGiveItem() {
            Tracker tracker = new Tracker();
            Item item = new Item("test1", "testDescription", 123L);
            tracker.add(item);
            item.setId("123");
            assertThat(tracker.findById(item.getId()).getName(), is("test1"));
        }

        @Test
        public void whenFindAllThenTrackerGiveItems() {
            Tracker tracker = new Tracker();
            Item item1 = new Item("test1", "testDescription", 123L);
            Item item2 = new Item("test2", "testDescription", 123L);
            tracker.add(item1);
            tracker.add(item2);
            assertThat(tracker.findAll().size(), is(2));
        }

        @Test
        public void whenAddNewItemThenTrackerHasSameItem() {
            Tracker tracker = new Tracker();
            Item item = new Item("test1", "testDescription", 123L);
            tracker.add(item);
            assertThat(tracker.findAll(), is(Arrays.asList(item)));
        }

        @Test
        public void whenFindItemByNameThenTrackerHasNameList() {
            Tracker tracker = new Tracker();
            Item item1 = new Item("test1", "testDescription", 123L);
            Item item2 = new Item("test1", "testDescription", 123L);
            tracker.add(item1);
            tracker.add(item2);
            List<Item> result = Arrays.asList(item1, item2);
            assertThat(tracker.findByName("test1"), is(result));
        }

        @Test
        public void whenDeleteThisItemThenTrackerHasNull() {
            Tracker tracker = new Tracker();
            Item deleted1 = new Item("test1", "testDescription1", 123L);
            Item deleted2 = new Item("test2", "testDescription2", 123L);
            Item deleted3 = new Item("test3", "testDescription3", 123L);
            tracker.add(deleted1);
            tracker.add(deleted2);
            tracker.add(deleted3);
            tracker.delete(deleted2.getId());
            List<Item> result = Arrays.asList(deleted1, deleted3);
            assertThat(tracker.findAll(), is(result));
        }

        @Test
        public void whenReplaceNameThenReturnNewName() {
            Tracker tracker = new Tracker();
            Item previous = new Item("test1", "testDescription", 123L);
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2", 1234L);
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
        }
    }

