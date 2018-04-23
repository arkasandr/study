package ru.job4j.tracker;
import java.util.*;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * создаем массив для хранения заявок
     */
    private Item[] items = new Item[100];
    /**
     * указатель ячейки для новой заявки
     */
    private int position = 0;
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
        this.items[this.position++] = item;
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
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * метод возвращает весь список заявок
     *
     * @return
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * метод получает список по имени.
     *
     * @return
     */
    public Item[] findByName(String key) {
        int index = 0;
        Item[] result = new Item[this.position];
        for (Item item : items) {
            if (item != null && items[index].getName().equals(key)) {
                result[index] = item;
                index++;
            }
        }
        return result;
    }

    /**
     * метод заменяет заявку.
     *
     * @return
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * метод удаляет заявку.
     *
     * @return
     */
    public void delete(String id) {
        for (int index = 0; index < items.length; index++) {
            if (items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, items.length - index - 1);
                break;
            }
        }
    }
}


