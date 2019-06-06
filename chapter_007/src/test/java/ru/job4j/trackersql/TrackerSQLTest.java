package ru.job4j.trackersql;


import org.junit.Test;
import ru.job4j.tracker.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {


    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenFindByIdThenTrackerGiveItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item("test1", "testDescription", 123L);
            tracker.init();
            tracker.add(item);
            assertThat(tracker.findById(id -> id.equals(id)).getName(), is("test1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAllThenTrackerGiveItems() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item1 = new Item("test1", "testDescription", 123L);
            Item item2 = new Item("test2", "testDescription", 123L);
            tracker.init();
            tracker.add(item1);
            tracker.add(item2);
            assertThat(tracker.findAll().size(), is(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item("test1", "testDescription", 123L);
            tracker.init();
            tracker.add(item);
            assertThat(tracker.findAll().get(0).getName(), is("test1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindItemByNameThenTrackerHasNameList() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item1 = new Item("test11", "testDescription", 123L);
            Item item2 = new Item("test11", "testDescription", 123L);
            tracker.init();
            tracker.add(item1);
            tracker.add(item2);
            List<Item> result = Arrays.asList(item1, item2);
            assertThat(tracker.findByName(name -> name.equals("test11")).size(), is(result.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteThisItemThenTrackerHasNull() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item deleted1 = new Item("test14", "testDescription1", 123L);
            Item deleted2 = new Item("test24", "testDescription2", 123L);
            Item deleted3 = new Item("test34", "testDescription3", 123L);
            tracker.init();
            tracker.add(deleted1);
            tracker.add(deleted2);
            tracker.add(deleted3);
            tracker.delete(deleted2.getId());
            assertThat(tracker.findByName(name -> name.equals(deleted2.getName())).size(), is(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item previous = new Item("test1", "testDescription", 123L);
            tracker.init();
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2", 1234L);
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(id -> id.equals(id)).getName(), is("test2"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}