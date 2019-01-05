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
    public static Set<String> findAllDeps(String[] str) {
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
     * Класс реализует компаратор по возрастанию
     */
    static class UpComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int indexDep = o1.compareTo(o2);
            int lengthDep = Integer.compare(o1.length(), o2.length());

            return indexDep != 0 ? indexDep : lengthDep;
        }
    }

    /**
     * Класс реализует компаратор по убыванию
     */
    static class DownComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int result = Integer.compare(o1.length(), o2.length());
            int minLength = o1.length() > o2.length() ? o2.length() : o1.length();
            for (int i = 0; i < minLength; i++) {
                int temp = Character.compare(o2.charAt(i), o1.charAt(i));
                if (temp != 0) {
                    result = temp;
                    break;
                }
            }
            return result;
        }
    }


    /**
     * Метод реализует сортировку департментов по возрастанию
     */
        public List<String> sortUpDeps(String[] str) {
            List<String> division = new ArrayList<>(Arrays.asList(str));
            for (String s:findAllDeps(str)) {
                division.add(s);
            }
            division.sort(new UpComparator());
            return division;
        }


    /**
     * Метод реализует сортировку департментов по убыванию
     */
        public List<String> sortDownDeps(String[] str) {

        List<String> division = new ArrayList<>(Arrays.asList(str));
        for (String s:findAllDeps(str)
        ) {
            division.add(s);
        }
            division.sort(new DownComparator());
            return division;
    }
}
