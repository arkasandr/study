package ru.job4j.search;

import java.util.*;

public class SortUser {


                public Set<User> sort(List<User> input) {
                    Set<User> result = new TreeSet<>();
                    for (User a : input) {
                        result.addAll(Arrays.asList(a));
                    }
                    return result;
                }
            }
