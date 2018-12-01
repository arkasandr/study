package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertMatrix2List {
    public List<Integer> toList(Integer[][] array) {
//        List<Integer> result = new ArrayList<>();
//        for (Integer[] a : array) {
//            result.addAll(Arrays.asList(a));
//        }
//        return result;
//    }

        return Arrays.stream(array)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}


