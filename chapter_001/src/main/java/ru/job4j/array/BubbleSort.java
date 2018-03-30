package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
	public static int[] sort(int[] array) {
		int previous;
		int next;
		for (int j = 0; j < array.length - 1; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				previous = array[i];
				next = array[i + 1];
				
				if (previous >= next) {
					array[i + 1] = array[i];
					array[i] = next;
					array[i + 1] = previous;
				}
			}
			}
		return array;
	}
	
	
	
	//public static void main(String[] args) throws java.lang.Exception {
		//int[] array = {5, 1, 7, 4};
		//System.out.println(Arrays.toString(BubbleSort.sort(array)));
	//}
}

