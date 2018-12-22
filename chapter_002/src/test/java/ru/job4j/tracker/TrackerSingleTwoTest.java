package ru.job4j.tracker;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TrackerSingleTwoTest {

    @Test
    public void whenDoubleSingleTwo() {
        TrackerSingleTwo firstTracker = TrackerSingleTwo.getInstance();
        TrackerSingleTwo secondTracker = TrackerSingleTwo.getInstance();
        assertThat(firstTracker == secondTracker, is(true));
    }
}

