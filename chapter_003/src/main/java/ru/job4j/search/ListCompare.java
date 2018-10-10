package ru.job4j.search;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
            int result = Integer.compare(left.length(), right.length());
            int minLength = left.length() > right.length() ? right.length() : left.length();
            for (int i = 0; i < minLength; i++) {
                int temp = Character.compare(left.charAt(i), right.charAt(i));
                if (temp != 0) {
                    result = temp;
                    break;
                }
            }
            return result;
}
}
