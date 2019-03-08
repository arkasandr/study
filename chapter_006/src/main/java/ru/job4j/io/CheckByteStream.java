package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;

public class CheckByteStream {
    public boolean isNumber(InputStream in) {
        boolean result = false;
        int b;
            try (in) {
                while ((b = in.read()) != -1) {
                    if (b <= 57 && b >= 48) {
                        return false;
                    } else if ((b % 2) == 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            }
                catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            return result;
        }
}
