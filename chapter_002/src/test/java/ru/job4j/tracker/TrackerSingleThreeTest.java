package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleThreeTest {

        @Test
        public void whenDoubleSingleThree() {
            TrackerSingleThree firstTracker = TrackerSingleThree.getInstance();
            TrackerSingleThree secondTracker = TrackerSingleThree.getInstance();
            assertThat(firstTracker == secondTracker, is(true));
        }
    }
