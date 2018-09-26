package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(Integer[][] array) {
        List<Integer> result = new ArrayList<>();
        for (Integer[] a : array) {
            result.addAll(Arrays.asList(a));
        }
        return result;
    }
}


