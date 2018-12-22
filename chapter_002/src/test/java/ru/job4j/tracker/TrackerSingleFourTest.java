package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleFourTest {

    @Test
    public void whenDoubleSingleThree() {
        TrackerSingleFour firstTracker = TrackerSingleFour.getInstance();
        TrackerSingleFour secondTracker = TrackerSingleFour.getInstance();
        assertThat(firstTracker == secondTracker, is(true));
    }
}

