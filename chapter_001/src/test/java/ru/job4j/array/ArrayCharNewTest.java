package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharNewTest {
    @Test
    public void whenAContainsBThenTrue() {
        ArrayCharNew word = new ArrayCharNew();
        boolean result = word.contains("Привет", "иве");
        assertThat(result, is(true));
    }

    @Test
    public void whenANotContainsBThenFalse() {
        ArrayCharNew word = new ArrayCharNew();
        boolean result = word.contains("Привет", "ивр");
        assertThat(result, is(false));
    }
}