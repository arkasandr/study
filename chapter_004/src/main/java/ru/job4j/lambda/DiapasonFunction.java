package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DiapasonFunction {

    /**
     * Метод реализует подсчет функции в диапазоне.
     */

   List<Double> diapason(int start, int end, Function<Double, Double> func) {
       List<Double> result = new ArrayList<>();
       for (double i = start; i < end; i++) {
           result.add(func.apply(i));
       }
       return result;
   }
}