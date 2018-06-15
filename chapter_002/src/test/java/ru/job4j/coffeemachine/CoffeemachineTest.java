package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeemachineTest {

    @Test
    public void whenNintyOneThenTwoTwoFive() {
        Coffeemachine cup = new Coffeemachine();
        String[] expect = {null, "по 2 руб - 2шт.", "по 5 руб - 1шт.", null};
        assertThat(cup.changes(100, 91), is(expect));
    }

    @Test
    public void whenFiftysevenThenTenTenTenTenTwoOne() {
        Coffeemachine cup = new Coffeemachine();
        String[] expect = {"по 1 руб - 1шт.", "по 2 руб - 1шт.", null, "по 10 руб - 4шт."};
        assertThat(cup.changes(100, 57), is(expect));
    }
}