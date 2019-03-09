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
    public boolean isNumber(InputStream in) throws IOException {
        boolean result = false;
        int b = in.read();
                while (b  != -1) {
                    if (b < 57 & b > 48) {
                        result = ((b % 2) == 0);
                    }
                    b = in.read();
                }
            return result;
    }
}
