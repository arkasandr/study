package ru.job4j.comparison;

import java.util.*;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Analize {
    /**
     * Определить изменения в коллекции
     *
     * @param previous начальные двнные
     * @param current  измененные данные
     * @return
     */
    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> store = new HashMap<>();
        int deleted = 0, changed = 0, added = 0;
        for (User user:current) {
            store.put(user.getId(), user);
        }
        for (User user:previous) {
            User us = store.remove(user.getId());
            if (us == null) {
                deleted++;
            } else if (!us.getName().equals(user.getName())) {
                changed++;
            }
            added = store.size();
        }
            return new Info(added, changed, deleted);
        }
    }
