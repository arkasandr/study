package ru.job4j.generic;

public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    public AbstractStore(int size) {
        store = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < store.getSize(); i++) {
            if (store.get(i) != null && store.get(i).getId().equals(id)) {
                store.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < store.getSize(); i++) {
            if (store.get(i) != null && store.get(i).getId().equals(id)) {
                store.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (int i = 0; i < store.getSize(); i++) {
            if (store.get(i) != null && store.get(i).getId().equals(id)) {
                result = store.get(i);
                break;
            }
        }
        return result;
    }

    public T get(int position) {
        return store.get(position);
    }
}
