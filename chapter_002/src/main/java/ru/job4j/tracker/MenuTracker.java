package ru.job4j.tracker;

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
    private UserAction[] actions = new UserAction[7];


    /**
     * Метод формирует массив пунктоа меню.
     */
    public int[] getActions() {
       int[] range = new int[this.actions.length];
        for (int i = 0; i < actions.length; i++) {
             range[i] = i;
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
            for (int index = 0; index < tracker.findByName(name).length; index++) {
                if (tracker.findByName(name)[index] != null) {
                    System.out.println("------- Список найденных заявок с уникальным номером Id: ");
                    System.out.println(tracker.findByName(name)[index].getId());
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
        this.actions[0] = this.new AddItem(0, "Добавить новую заявку.");
        this.actions[1] = new MenuTracker.ShowItems(1, "Показать все заявки.");
        this.actions[2] = new EditItem(2, "Редактировать заявку.");
        this.actions[3] = new DeleteItem(3, "Удалить заявку.");
        this.actions[4] = new MenuTracker.ShowItemById(4, "Найти заявку по id.");
        this.actions[5] = new ShowItemByName(5, "Найти заявку по имени.");
        this.actions[6] = new ExitTracker(6, "Выйти из программы.");
    }


    /**
     * Метод реализует выбранное событие.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
