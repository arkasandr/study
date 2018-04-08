package ru.job4j.array;
import java.util.Arrays;
/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */


public class ArrayResult {

    public static int[] result(int[] a, int[] b) {
        int[] array = new int[a.length + b.length];
        int i = 0, j = 0;

        for (int k = 0; k < array.length; k++) {
                if (i > a.length - 1) {
                    int value = b[j];
                    array[k] = value;
                    j++;

                } else if (j > b.length - 1) {
                    int value = a[i];
                    array[k] = value;
                    i++;
                } else if (a[i] < b[j]) {
                    int value = a[i];
                    array[k] = value;
                    i++;
                } else {
                    int value = b[j];
                    array[k] = value;
                    j++;
                }
        }
        return array;
    }

//    public static void main(String[] args) throws java.lang.Exception {
//        int[] a = {1, 6, 7};
//        int[] b = {4, 9, 18};
//        System.out.println(Arrays.toString(result(a, b)));
//    }

}
