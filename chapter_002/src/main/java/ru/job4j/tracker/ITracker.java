package ru.job4j.tracker;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> findAll();
    List<Item> findByName(Predicate<String> predicate);
    Item findById(Predicate<String> predicate);
    default String generateId(Random rn) {
        return String.valueOf(System.currentTimeMillis() + rn.nextInt(100)); }
}
