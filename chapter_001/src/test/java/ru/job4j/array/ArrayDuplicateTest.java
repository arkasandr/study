package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] except = {"Привет", "Мир", "Супер"};
		ArrayDuplicate duplicate = new ArrayDuplicate();
		String[] result = duplicate.remove(input);
		assertThat(result, arrayContainingInAnyOrder(except));
		
	}
}