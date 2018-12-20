package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean prime1 = data[0][0];
        boolean prime2 = data[data.length - 1][0];
        for (int i = 1; i < data.length; i++) {
            if (data[i][i] != prime1 || data[data.length - 1 - i][i] != prime2) {
                result = false;
                break;
            }
        }
        return result;
    }
}
