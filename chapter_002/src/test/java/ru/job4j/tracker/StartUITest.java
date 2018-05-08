package ru.job4j.tracker;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {


    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

        @Before
        public void loadOutput() {
            System.out.println("execute before method");
            System.setOut(new PrintStream(this.out));
        }

        @After
        public void backOutput() {
            System.setOut(this.stdout);
            System.out.println("execute after method");
        }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "1", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }


    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", "2", item.getId(), "test name", "desc", "1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerHasEmptyValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки
        Item item1 = tracker.add(new Item());
        Item item2 = tracker.add(new Item());
        //присваиваем переменной result ожидаемый результат
        Item[] result = {item2, null};
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", "3", item1.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что список заявок соответствует ожидаемому результату
        assertThat(tracker.findAll(), is(result));
    }


    @Test
    public void whenShowAllThenTrackerGiveAllItems() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки
        Item item1 = tracker.add(new Item());
        Item item2 = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что список заявок соответствует ожидаемому результату
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы." + "\r" + "\n")
                                .append("------------ Список всех заявок --------------" + "\r" + "\n")
                                .append("Имя заявки null. Id заявки " + item1.getId() + "." + "\r" + "\n")
                                .append("---------------------------------------------" + "\r" + "\n")
                                .append("Имя заявки null. Id заявки " + item2.getId() + "." + "\r" + "\n")
                                .append("---------------------------------------------" + "\r" + "\n")
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerGiveFindItem() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки
        Item item1 = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"4", item1.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что список заявок соответствует ожидаемому результату
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы." + "\r" + "\n")
                                .append("------ Поиск заявки по уникальному номеру Id ------" + "\r" + "\n")
                                .append("------- Найдена заявка с именем: null" + "\r" + "\n")
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerGiveFindItem() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"5", "name1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что список заявок соответствует ожидаемому результату
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы." + "\r" + "\n")
                                .append("---------- Поиск заявки по имени  ----------" + "\r" + "\n")
                                .append("------- Список найденных заявок с уникальным номером Id: " + "\r" + "\n" + item1.getId() + "\r" + "\n")
                                .append("Меню." + "\r" + "\n")
                                .append("0. Добавить новую заявку." + "\r" + "\n")
                                .append("1. Показать все заявки." + "\r" + "\n")
                                .append("2. Редактировать заявку." + "\r" + "\n")
                                .append("3. Удалить заявку." + "\r" + "\n")
                                .append("4. Найти заявку по id." + "\r" + "\n")
                                .append("5. Найти заявку по имени." + "\r" + "\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
