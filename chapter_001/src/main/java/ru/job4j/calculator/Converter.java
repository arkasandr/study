package ru.job4j.calculator;

/**
 * Корвертор валюты.
 */
public class Converter {

    private int result;
    private int dollar = 60;
    private int euro = 70;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */

    public int rubleToEuro(int value) {
        return value / euro;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */

    public int rubleToDollar(int value) {
        return value / dollar;
    }

    /**
     * Конвертируем евро в рубли .
     * @param value рубли.
     * @return Евро.
     */

    public int euroToRuble(int value) {
        return value * euro;
    }


    /**
     * Конвертируем доллары в рубли .
     * @param value рубли.
      * @return Евро.
     */
    public int dollarToRuble(int value) {
        return value * dollar;
    }

}
