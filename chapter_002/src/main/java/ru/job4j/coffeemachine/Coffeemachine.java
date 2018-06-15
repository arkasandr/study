package ru.job4j.coffeemachine;

public class Coffeemachine {

    int[] coins = {1, 2, 5, 10};
    String[] result = new String[4];
    String[] result1 = {"по 1 руб - ", "по 2 руб - ", "по 5 руб - ", "по 10 руб - "};
    private int i = 0;
    private int y;
    private int k = coins.length;

    public String[] changes(int value, int price) {
        int delta = value - price;
        while (k != 0 && coins[--k] > delta);
        y = k;
        int temp, count;
        do {
            if ((temp = delta % coins[y]) >= coins[0] || temp == 0) {
                count = delta / coins[y];
                delta = temp;
            } else {
                count = delta / coins[y] - 1;
                delta = temp + coins[y];
            }
            result[y] = result1[y] + count + "шт.";
            while (y != 0 && coins[--y] > delta) ;
        } while (i < coins.length && delta > 0);
       return result;
    }
}
