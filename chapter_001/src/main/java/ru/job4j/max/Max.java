package ru.job4j.max;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class Max {
	 public int max(int first, int second) {
		int max;
		max = first > second ? first : second;
		return max;
	 }
 }