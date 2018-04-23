package ru.job4j.tracker;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOWALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FINDBYID = "4";

    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDBYNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findItemById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание  заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с уникальным номером Id : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует вывод всех заявок из хранилища.
     */
    private void showAllItems() {
        System.out.println("------------ Список всех заявок --------------");
        this.tracker.findAll();
        for (Item item : tracker.findAll()) {
            if (item != null) {
                System.out.println("Имя заявки " + item.getName() + "; Id заявки " + item.getId());
                System.out.println("---------------------------------------------");
            }
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание  заявки :");
        Item item = new Item(name, desc);
        item.setId(id);
        this.tracker.replace(id, item);
        System.out.println("---- Заявка с уникальным номером Id : " + item.getId() + " отредактирована ----");
    }

    /**
     * Метод реализует удаление заявки по id.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        this.tracker.delete(id);
        System.out.println("---- Заявка с уникальным номером Id : " + id + " удалена ----");
    }

    /**
     * Метод реализует поиск заявки по id.
     */
    private void findItemById() {
        System.out.println("------ Поиск заявки по уникальному номеру Id ------");
        String id = this.input.ask("Введите Id заявки :");
        this.tracker.findById(id);
        System.out.println("------- Найдена заявка с именем: " + tracker.findById(id).getName() + " -------");
    }

    /**
     * Метод реализует поиск заявки по имени.
     */
    private void findItemByName() {
        System.out.println("---------- Поиск заявки по имени  ----------");
        String key = this.input.ask("Введите имя заявки :");
        System.out.println("------- Список найденных заявок с уникальным номером Id: ");
        for (int index = 0; index < this.tracker.findByName(key).length; index++) {
            System.out.println(this.tracker.findByName(key)[index].getId());
        }
    }
    

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактировать заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по id.");
        System.out.println("5. Найти заявку по имени.");
        System.out.println("6. Выход из программы.");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}


