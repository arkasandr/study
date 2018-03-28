package ru.job4j.loop;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */


public class Paint {
	 public String rightTrl(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина будет равна высоте. 
        int weight = height; 
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != weight; column++) {
                // если строка равна ячейки, то рисуем галку. 
                // в данном случае строка определяем, сколько галок будет на строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }
	
	public String leftTrl(int height) {
		StringBuilder screen = new StringBuilder();
		int weight = height;
		for (int row = 0; row != height; row++) {
			for (int column = 0; column != weight; column++) {
				if (row >= weight - column - 1) {
                screen.append("^");
				} else {
                screen.append(" ");
				}
			}
        screen.append(System.lineSeparator());
		}
		return screen.toString();
	}
	
	public String pyramid(int h) {
    StringBuilder screen = new StringBuilder();
    int weight = 2 * h - 1;
    for (int row = 0; row != h; row++) {
        for (int column = 0; column != weight; column++) {
            if (row >= h - column - 1 && row + h - 1 >= column) {
                screen.append("^");
            } else {
                screen.append(" ");
            }
        }
        screen.append(System.lineSeparator());
    }
    return screen.toString();
}
	
}
