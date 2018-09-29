package ru.job4j.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserConvert {

    List<User> list = new ArrayList<User>();

    public HashMap<Integer, User> process(List<User> list) {
        HashMap newMap = new HashMap<Integer, User>();
        for (User user : list) {
            newMap.put(user.getId(), user);
        }
        return newMap;
    }


    public static void main(String[] args) throws java.lang.Exception {
        List<User> input = new ArrayList();
        input.add(new User(1, "Alex", "Moscow"));
        input.add(new User(2, "Ola", "Minsk"));
        input.add(new User(3, "Dima", "Orel"));
        UserConvert example = new UserConvert();
        System.out.println(example.process(input));
    }

}
