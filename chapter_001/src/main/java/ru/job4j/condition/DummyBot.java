package ru.job4j.condition;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DummyBot {
	/**
	 * �������� �� �������.
	 * @param question ������ �� �������.
	 * @return �����.
	 */
	 
	 public String answer(String question) {
		 String rsl = "��� ������ ���� � �����. �������� ������ ������.";
		 if ("������, ���.".equals(question)) {
			 rsl = "������, �����.";
		 } else if ("����.".equals(question)) {
			 rsl = "�� ������ �������.";
		 }
		 return rsl;
	 }
}