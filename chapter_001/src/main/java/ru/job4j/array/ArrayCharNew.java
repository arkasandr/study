package ru.job4j.array;
import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class ArrayCharNew {
    private char[] sub;
    private char[] origin;

    //public ArrayChar(String line) {
     //   this.data = line.toCharArray();
   // }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */

    public static boolean contains(String origin, String sub) {

        //boolean result = true;
        char[] a = origin.toCharArray();
        char[] b = sub.toCharArray();
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < b.length; k++) {
            if (a[i] == b[k]) {
                if (b.length != k + 1) {
                    if (a[i + 1] == b[k + 1]) {
                            count++;
                            break;
                    }

            } else {
                    count++;
                    break;
                }
            }
        }
        if (count == b.length) {
        return true;
    }
    }
    return false;
}

}



