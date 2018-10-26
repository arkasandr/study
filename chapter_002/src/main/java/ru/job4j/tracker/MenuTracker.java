package ru.job4j.tracker;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuTracker {

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранилище заявок.
     */
    private Tracker tracker;
    /**
     * Массив содержит допустимые действия.
     */
    private List<UserAction> actions = new ArrayList<>();


    /**
     * Метод формирует массив пунктов меню.
     */
//    public int[] getActions() {
//        int[] range = new int[this.actions.size()];
//        for (int i = 0; i < actions.size(); i++) {
//            range[i] = i;
//        }
//        return range;
//    }

    public List<UserAction> getActions() {
        List<UserAction> range = new ArrayList<>();
        for (UserAction action:actions
             ) {
            range.add(action);
        }
        return range;
    }
    /**
     * Класс реализует редактирование заявки.
     */
    class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите Id заявки :");
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание  заявки :");
            Item item = new Item(name, desc);
            item.setId(id);
            tracker.replace(id, item);
        }
    }

    /**
     * Класс реализует поиск заявки по имени.
     */
    class ShowItemByName extends BaseAction {
        public ShowItemByName(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- Поиск заявки по имени  ----------");
            String name = input.ask("Введите имя заявки :");
            tracker.findByName(name);
            for (int index = 0; index < tracker.findByName(name).size(); index++) {
                if (!tracker.findByName(name).equals(null)) {
                    System.out.println("------- Список найденных заявок с уникальным номером Id: ");
                    System.out.println(tracker.findByName(name).get(index).getId());
                }
            }
        }
    }


    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод инициализирует события.
     */
    public void fillActions() {
        System.out.println("Меню.");
        this.actions.add(new AddItem(0, "Добавить новую заявку."));
        this.actions.add(new MenuTracker.ShowItems(1, "Показать все заявки."));
        this.actions.add(new EditItem(2, "Редактировать заявку."));
        this.actions.add(new DeleteItem(3, "Удалить заявку."));
        this.actions.add(new MenuTracker.ShowItemById(4, "Найти заявку по id."));
        this.actions.add(new ShowItemByName(5, "Найти заявку по имени."));
        this.actions.add(new ExitTracker(6, "Выйти из программы."));
    }


    /**
     * Метод реализует выбранное событие.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на печать меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание  заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
        }
    }

    /**
     * Метод реализует вывод всех заявок из хранилища.
     */
    private static class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Список всех заявок --------------");
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    System.out.println(String.format("%s. %s", "Имя заявки " + item.getName(), "Id заявки " + item.getId() + "."));
                    System.out.println("---------------------------------------------");
                }
            }
        }
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите Id заявки :");
            tracker.delete(id);
        }
    }

    /**
     * Метод реализует вывод заявки из хранилища по Id
     */
    private static class ShowItemById extends BaseAction {
        public ShowItemById(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------ Поиск заявки по уникальному номеру Id ------");
            String id = input.ask("Введите Id заявки :");
            tracker.findById(id);
            System.out.println("------- Найдена заявка с именем: " + tracker.findById(id).getName());
                }
    }

    /**
     * Метод реализует выход из программы
     */
    private static class ExitTracker extends BaseAction {
        public ExitTracker(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
        }
    }


}
