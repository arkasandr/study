package ru.job4j.departments;


import java.util.*;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortDepartments {
    /**
     * Метод реализует поиск недостающих департментов
     */
    public Set<String> findAllDeps(String[] str) {
        Set<String> depart = new TreeSet<>();
        List<String> strings = Arrays.asList(str);
        for (String s:strings) {
            for (var i = 0; i < s.length(); i++) {
                 if (s.charAt(i) == '\\') {
                     String newDepart = s.substring(0, i);
                     if (!strings.contains(newDepart)) {
                         depart.add(newDepart);
                     }
                 }
        }

        }
        return depart;
    }

    /**
     * Метод реализует сортировку департментов по возрастанию
     */
    public Set<String> sortUpDeps(String[] str) {
        Set<String> division = new TreeSet<>(Arrays.asList(str));
        for (String s:findAllDeps(str)) {
            division.add(s);
        }
        return division;
    }

    /**
     * Метод реализует сортировку департментов по убыванию
     */
    public Set<String> sortDownDeps(String[] str) {
        Set<String> division = new TreeSet<>(Arrays.asList(str));
        for (String s:findAllDeps(str)
        ) {
            division.add(s);
        }
        return ((TreeSet<String>) division).descendingSet();
    }
}
