package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean prime = data[0];
        for (boolean b:data
             ) {
            if(b != prime) {
                result = false;
                break;
            }
        }
        return result;
    }
}
