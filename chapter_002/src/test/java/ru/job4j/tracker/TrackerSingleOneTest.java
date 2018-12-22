package ru.job4j.tracker;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


    public class TrackerSingleOneTest {

        @Test
        public void whenDoubleSingleOne() {
            TrackerSingleOne firstTracker = TrackerSingleOne.INSTANCE;
            TrackerSingleOne secondTracker = TrackerSingleOne.INSTANCE;
            assertThat(firstTracker == secondTracker, is(true));
        }
    }
