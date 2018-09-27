package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertList {

        public List<Integer> convert(List<int[]> list) {
            List<Integer> result = new ArrayList<>();

            for (int[] a : list) {
                for (int i = 0; i <= a.length - 1; i++) {
                    result.addAll(Arrays.asList(a[i]));
                }
            }
            return result;
        }
}