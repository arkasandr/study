package ru.job4j.tracker;

import java.util.List;
import java.util.function.Predicate;

    public enum TrackerSingleOne {
        INSTANCE;

        private static final Tracker TRACKER = new Tracker();

        public Item add(Item item) {
            return TRACKER.add(item);
        }

        /**
         * Метод ведет поиск заявки по уникальному ключу.
         *
         * @param id уникальный ключ.
         * @return заявка.
         */
        public Item findById(Predicate<String> predicate) {
            return TRACKER.findById(predicate);
        }

        /**
         * метод возвращает весь список заявок
         *
         * @return
         */
        public List<Item> findAll() {
            return TRACKER.findAll();
        }

        /**
         * метод получает список по имени.
         *
         * @return
         */
        public List<Item> findByName(Predicate<String> predicate) {
            return TRACKER.findByName(predicate);
        }

        /**
         * метод заменяет заявку.
         *
         * @return
         */
        public void replace(String id, Item item) {
            TRACKER.replace(id, item);
        }

        /**
         * метод удаляет заявку.
         *
         * @return
         */
        public void delete(String id) {
            TRACKER.delete(id);
        }
    }
