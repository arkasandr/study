package ru.job4j.generic;

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(int id, T model);

    boolean delete(int id);

    T findById(int id);
}
