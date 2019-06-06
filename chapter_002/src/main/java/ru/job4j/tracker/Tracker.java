package ru.job4j.tracker;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements ITracker {
    /**
     * создаем массив для хранения заявок
     */

    private List<Item> items = new ArrayList<>();

    /**
     * поля уникального ключа
     */
    private static final Random RN = new Random();

    /**
     * метод, реализующий добавление заявки в хранилище
     *
     * @param item новая заявка
     */

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * Метод ведет поиск заявки по уникальному ключу.
     *
     * @param id уникальный ключ.
     * @return заявка.
     */
//    public Item findById(String id) {
//        Item result = null;
//        for (Item item : items) {
//            if (item != null && item.getId().equals(id)) {
//                result = item;
//                break;
//            }
//        }
//        return result;
//    }

    public Item findById(Predicate<String> predicate) {
        Item result = null;
        for (Item item : items) {
            if (item != null && predicate.test(item.getId())) {
                result = item;
                break;
            }
        }
        return result;
    }

//    public  Item findById(String id) {
//        final Item[] item = {null};
//        this.items.forEach(i -> {
//            if (i != null && i.getId().equals(id)) {
//                item[0] = i;
//            }
//        });
//        return item[0];
//    }

    /**
     * метод возвращает весь список заявок
     *
     * @return
     */

    public List<Item> findAll() {
        return items;
    }

    /**
     * метод получает список по имени.
     *
     * @return
     */
//    public List<Item> findByName(String key) {
//        List<Item> result = new ArrayList<>();
//        for (Item item:items) {
//            if (item != null && item.getName().equals(key)) {
//                result.add(item);
//            }
//        }
//        return result;
//    }

    public List<Item> findByName(Predicate<String> predicate) {
        List<Item> result = new ArrayList<>();
        for (Item item:items) {
            if (item != null && predicate.test(item.getName())) {
                result.add(item);
            }
        }
        return result;
    }
    /**
     * метод заменяет заявку.
     *
     * @return
     */
//    public void replace(String id, Item item) {
//        for (Item it:items) {
//            if (it.getId().equals(id)) {
//                items.remove(it);
//                items.add(item);
//                break;
//            }
//        }
//    }

    public void replace(String id, Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                if (items.get(i).getId().equals(id)) {
                    items.get(i).setName(item.getName());
                    items.get(i).setDescription(item.getDescription());
                    break;
            }
        }
    }
    }
    /**
     * метод удаляет заявку.
     *
     * @return
     */


//    public void delete(String id) {
//        for (Item item:items) {
//            if (item.getId().equals(id)) {
//                items.remove(item);
//                break;
//            }
//        }
//    }

    public void delete(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                if (items.get(i).getId().equals(id)) {
                    items.remove(i);
                    break;
                }
            }
        }
    }
}


