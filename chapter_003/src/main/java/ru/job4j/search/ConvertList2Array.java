package ru.job4j.search;

import java.util.List;
public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % cells != 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];

        for (int l = 0, listIndex = 0; l < rows; l++) {
            for (int k = 0; k < cells; k++) {
                if (listIndex < list.size()) {
                    int value = list.get(listIndex);
                     array[l][k] = value;
                     listIndex++;
                } else {
                    break;
                }
            }
        }
        return array;
    }
}
