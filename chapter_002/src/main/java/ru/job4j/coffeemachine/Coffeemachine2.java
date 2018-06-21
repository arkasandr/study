package ru.job4j.coffeemachine;

import java.util.Arrays;

public class Coffeemachine2 {


    int[] changes(int value, int price) {
        int change = value > price ? value - price : 0;
        int[] result = new int[change];
        int[] values = {10, 5, 2, 1};
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            while (change >= values[i]) {
                change = change - values[i];
                result[count] = values[i];
                count++;
            }
        }
        int[] finalResult = new int[count];
        for (int i = 0; i < count; i++) {
            finalResult[i] = result[i];
        }
        return finalResult;
    }

    public static void main(String[] args) {
        Coffeemachine2 cof = new Coffeemachine2();
        System.out.println(Arrays.toString(cof.changes(100,91)));
    }
}
