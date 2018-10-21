package ru.job4j.search;

import java.util.*;

public class SortUser {
    /**
     * Метод сортирует список пользователей.
     * В классе User переопределен метод compareTo().
     *
     * @return Упорядоченный набор пользователей (по возрастанию age).
     */

                public Set<User> sort(List<User> input) {
                    Set<User> result = new TreeSet<>();
                    for (User a : input) {
                        result.addAll(Arrays.asList(a));
                    }
                    return result;
                }

    /**
     * Метод сортирует список пользователей.
     * Использует Comparator.
     * @return Упорядоченный набор пользователей по длине имени.
     */

    public List<User> sortNameLength(List<User> users) {
                    Collections.sort(users,
                    new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return o1.getName().length() - o2.getName().length();
                        }
                    }
                    );
                    return users;
                }


    /**
     * Метод сортирует список пользователей.
     * Использует Comparator.
     * @return Упорядоченный набор пользователей по лексикографическому значению и возрасту.
     */

     public List<User> sortByAllFields(List<User> users) {
        Collections.sort(users,
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int rsl = o1.getName().compareTo(o2.getName());
                        return rsl != 0 ? rsl : Integer.compare(o1.getAge(), o2.getAge());
                    }
                }
        );
        return users;
    }
    
}
