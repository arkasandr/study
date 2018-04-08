package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayResultTest {
    @Test
    public void whenTwoDiffArraysGoToOneResult() {
        ArrayResult array = new ArrayResult();
        int[] a = new int[]{1, 6, 7};
        int[] b = new int[]{4, 9, 18};
        int[] result = array.result(a, b);
        int[] expect = new int[]{1, 4, 6, 7, 9, 18};
        assertThat(result, is(expect));
    }
}