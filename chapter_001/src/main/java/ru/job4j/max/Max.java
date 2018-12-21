package ru.job4j.max;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class Max {
	/**
	 * метод выбирает максимальное из двух чисел
	 */
	 public int max(int first, int second) {
		return first > second ? first : second;
	 }

	/**
	 * метод выбирает максимальное из трех чисел
	 */
	public int max2(int first, int second, int third) {
		int temp = this.max((this.max(first, second)), third);
		return temp;
	}
 }