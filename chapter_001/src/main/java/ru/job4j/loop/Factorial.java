package ru.job4j.loop;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class Factorial {
	/**
	 * произведение натуральных чисел.
	 * @param число.
	 * @return значение факториала.
	 */
	public int calc(int n) {
	int fact = 0;
	for (int i = 0; i <= n; i++) {
		if (i < 1) {
		fact = 1;
		} else {
			fact = i * fact;
		}
	   }
		return fact;

	}

}