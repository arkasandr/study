package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort order = new BubbleSort();
        int[] input = new int[] {7, 2};
        int[] result = order.sort(input);
        int[] expect = new int[] {2, 7};
        assertThat(result, is(expect));
    }
	
	
	
	 @Test
    public void whenSortArrayAmountOfElementsThenUseBubbleMethod() {
        BubbleSort order = new BubbleSort();
        int[] input = new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] result = order.sort(input);
        int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(result, is(expect));
    }
}