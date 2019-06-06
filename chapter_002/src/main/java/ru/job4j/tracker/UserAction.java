package ru.job4j.tracker;

public interface UserAction {
    /**
     * Метод запрашивает у пользователя пункт меню для выполнения.
     */
    int key();

    /**
     * Метод реализует основное действие пользователя.
     */
    void execute(Input input, ITracker tracker);

    /**
     * Метод сообщает пользователю информацию о выбранном действии.
     */
    String info();


}
