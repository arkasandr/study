package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
	public static int[] sort(int[] array) {
			for (int j = array.length - 1; j > 0; j--) {
				for (int i = 0; i < j; i++) {
				if (array[i] > array[i + 1]) {
					int value = array[i];
					array[i] = array[i + 1];
					array[i + 1] = value;
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

