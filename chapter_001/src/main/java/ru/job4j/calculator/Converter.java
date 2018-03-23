package ru.job4j.calculator;

/**
 * Корвертор валюты.
 */
public class Converter {

    private int result;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */

    public int rubleToEuro(int value) {
        this.result = value / 70;
        return this.result;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */

    public int rubleToDollar(int value) {
        this.result = value / 60;
        return this.result;
    }

    /**
     * Конвертируем евро в рубли .
     * @param value рубли.
     * @return Евро.
     */

    public int euroToRuble(int value) {
        this.result = value * 70;
        return this.result;
    }


    /**
     * Конвертируем доллары в рубли .
     * @param value рубли.
      * @return Евро.
     */
    public int dollarToRuble(int value) {
        this.result = value * 60;
        return this.result;
    }

}
