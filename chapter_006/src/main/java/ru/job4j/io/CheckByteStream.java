package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class CheckByteStream {

    /**
     * Метод проверяет, что в байтовом потоке записано четное число.
     *
     * @param in входной поток
     * @return true если поток оканчивается четным числом
     */
    public boolean isNumber(InputStream in) throws IOException{
        boolean result = false;
        int b;
            try (in) {
                while ((b = in.read()) != -1) {
                    if (b < 57 & b > 48) {
                        if ((b % 2) == 0) {
                            result = true;
                        } else {
                            result = false;
                        }
                    }
                }
            }
            return result;
    }
}
