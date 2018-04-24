package ru.job4j.tracker;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {


    public class StubInput implements Input {
        /**
         * Это поле содержит последовательность ответов пользователя.
         * Например. Если пользователь
         * хочет выбрать добавление новой заявки ему нужно ввести:
         * 0 - выбор пункта меня "добавить новую заявку".
         * name - имя заявки
         * desc - описание заявки
         * 6 - выйти из трекера.
         */
        private final String[] value;

        /**
         * Поле считает количество вызовом метода ask.
         * При каждом вызове надо передвинуть указатель на новое число.
         */
        private int position;

        public StubInput(final String[] value) {
            this.value = value;
        }

        /**
         * Давайте рассмотрим, как работает этот метод.
         * у нас есть объект в котором содержатся заранее продуманные ответы.
         * При последовательном вызове метода ask нам надо возвращать соответствующие данные.
         * Как если бы мы симулировали поведение пользователя.
         * Для этого при каждом вызове метода ask мы увеличиваем счетчик и
         * при следующем вызове он вернет нам новое значение.
         */
        @Override
        public String ask(String question) {
            return this.value[this.position++];
        }
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







}
