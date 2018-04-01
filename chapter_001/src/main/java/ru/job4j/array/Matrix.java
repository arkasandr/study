
package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {
	
	public static int[][] multiple(int size) {
		StringBuilder screen = new StringBuilder();
		String ln = System.lineSeparator();

		int[][] data = new int[size+1][size+1];

		System.out.printf("  *|");

		for (int i = 1; i <= size; i++) {
			System.out.printf(" %2d|", i);
		}
		System.out.printf(ln);
		System.out.printf("    ");
;
		for (int i = 1; i <= size; i++) {
			System.out.printf("----", i);
		}
		System.out.printf(ln);


		for (int i = 1; i <= size; i++) {
				System.out.printf(" %2d|", i);

			for (int j = 1; j <= size; j++) {

				System.out.printf(" %2d|", data[i][j] = i * j);


			}
				System.out.printf(ln);
		}
		System.out.printf("    ");
		for (int i = 1; i <= size; i++) {
			System.out.printf("----", i);
		}
		System.out.printf(ln);
		return data;
	}

	        public static void main(String[] args) {
			Matrix m = new Matrix();

				System.out.println(multiple(9));
		}
}