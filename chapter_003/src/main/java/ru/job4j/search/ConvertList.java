package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertList {

        public List<Integer> convert(List<int[]> list) {
            List<Integer> result = new ArrayList<>();
            for (int[] a : list) {
                for (int i : a) {
                    result.addAll(Arrays.asList(i));
                }
            }
            return result;
        }
}
