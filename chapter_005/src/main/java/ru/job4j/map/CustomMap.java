package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CustomMap {

    Map<User, Object> customMap = new HashMap<User, Object>();

    public CustomMap(Map<User, Object> customMap) {
        this.customMap = customMap;
    }
}
