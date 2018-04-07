
package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {
	
	public static int[][] multiple(int size) {


		int[][] data = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					data[i][j] = (i + 1) * (j + 1);
			}
			
		}
		return data;
	}
	    
}