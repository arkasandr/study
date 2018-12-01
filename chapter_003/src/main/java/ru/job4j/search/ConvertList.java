package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertList {

//        public List<Integer> convert(List<int[]> list) {
//            List<Integer> result = new ArrayList<>();
//            for (int[] a : list) {
//                for (int i : a) {
//                    result.add(i);
//                }
//            }
//            return result;
//        }

        public List<Integer> convert(List<int[]> list) {
            return list.stream()
                    .flatMap(a -> Arrays.stream(a).boxed())
                    .collect(Collectors.toList());
        }

}
