package ru.job4j.calculator;

/**
 * Корвертор валюты.
 */
public class Converter {

    private int result;
    private static final int DOLLAR = 60;
    private static final int EURO = 70;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */

    public int rubleToEuro(int value) {
        return value / EURO;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */

    public int rubleToDollar(int value) {
        return value / DOLLAR;
    }

    /**
     * Конвертируем евро в рубли .
     * @param value рубли.
     * @return Евро.
     */

    public int euroToRuble(int value) {
        return value * EURO;
    }


    /**
     * Конвертируем доллары в рубли .
     * @param value рубли.
      * @return Евро.
     */
    public int dollarToRuble(int value) {
        return value * DOLLAR;
    }

}
