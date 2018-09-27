package ru.job4j.search;

import java.util.List;
public class ConvertList2Array {

//        public int[][] toArray(List<Integer> list, int rows) {
//            int cells = list.size() / rows;
//            if (list.size() % cells != 0) {
//                cells++;
//            }
//            int[][] array = new int[rows][cells];
//            int l = 0;
//            int k = 0;
//            for (Integer i:list) {
//                array[l][k] = i;
//                k++;
//                if (k > cells - 1) {
//                    l++;
//                    k = 0;
//                }
//            }
//            return array;
//        }
//    }


    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % cells != 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];

        for (int l = 0, listIndex = 0; l < rows; l++) {
            for (int k = 0; k < cells; k++) {
                int value = listIndex < list.size() ? list.get(listIndex) : 0;
                array[l][k] = value;
                listIndex++;
            }
        }
        return array;
    }
}
