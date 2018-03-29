package ru.job4j.array;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
	public int[] turn(int[] array) {
		int massiv;
		for (int i = 0; i < array.length / 2; i++) {
			massiv = array[array.length - 1 - i];
			array[array.length - 1 - i] = array[i];
			array[i] = massiv;
			
		}
		return array;
	}
}