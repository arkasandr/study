package ru.job4j.search;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int lLength = left.length();
        int rLength = right.length();
        int minLength = Math.min(lLength, rLength);
        char rChar, lChar;
        int res = 1;

        for (int i = 0; i <= minLength - 1; i++) {
            lChar = left.charAt(i);
            rChar = right.charAt(i);
            if (lChar != rChar && lChar > rChar) {
                res =  lChar - rChar;
                break;
            } else if (lChar != rChar && lChar < rChar) {
                res =  lChar - rChar;
                break;
            } else if (lChar == rChar) {
                res =  left.length() - right.length();
            }
        }
        return res;
}
}
