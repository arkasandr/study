package ru.job4j.map;

import java.util.*;

public class CustomMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Entry[] table;
    private final float defaultLoadFactor = 0.6f;
    private final int defaultInitialCapacity = 1 << 4;
    private int size = 0;
    private int modCount = 0;

    public CustomMap() {
        this.table = new Entry[defaultInitialCapacity];
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        int hash;

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }


        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public int getHash() {
            return hash;
        }
    }

    /**
     * Метод вычисляет хеш по ключу.
     */

    public int hash(Object key) {
        int result = 0;
        if (key == null) {
            return  result;
        } else {
            result = key.hashCode() ^ (result >>> 16);
        }
        return result;
    }

    /**
     * Метод добавляет элемент в map.
     */
    private boolean put(K key, V value) {
        int h = hash(key.hashCode());
        int index = h & (table.length - 1);
        if (table[index] != null) {
            return false;
        } else {
            table[index] = new Entry(key, value, h);
            size++;
            modCount++;
            return true;
        }
    }

    /**
     * Метод увеличивает map.
     */
    private void resize() {
        Entry<K, V>[] resized = new Entry[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                resized[hash(table[i].getKey())] = table[i];
            }
        }
        table = resized;
    }
    /**
     * Метод вставляет значение в позицию, соответствующую ключу.
     */
    public boolean insert(K key, V value) {
        if (table.length * defaultLoadFactor > size) {
            return put(key, value);
        } else {
            resize();
            return put(key, value);
        }
    }

    /**
     * Метод получает значение по ключу.
     */
    public V get(K key) {
        int h = hash(key.hashCode());
        if (table[h] != null) {
            if (table[h].getKey().equals(key)) {
                return (V) table[h].getValue();
            }
        }
        return null;
    }

    /**
     * Метод удаляет элемент из map.
     */
    public boolean delete(K key) {
        int h = hash(key.hashCode());
        if (table[h] != null) {
            if (table[h].getKey().equals(key)) {
                table[h] = null;
                modCount--;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод создает список из значений map.
     */
    public List<V> getValues() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> e : table) {
            if (e != null) {
                values.add(e.getValue());
            }
        }
        return values;
    }

    /**
     * Метод возвращает длину map.
     */
    public int getTableLength() {
        return table.length;
    }


    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {

            private int count = 0;
            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public Entry<K, V> next() throws NoSuchElementException {
                Entry<K, V> entry = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = position; i < table.length; i++) {
                    if (table[i] != null) {
                        count++;
                        entry = table[i];
                        position = i + 1;
                        break;

                    }
                }
                return entry;
            }
        };
    }

}
