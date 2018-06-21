package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

    public class Coffeemachine2Test {

        @Test
        public void whenNintyOneThenTwoTwoFive() {
            Coffeemachine2 cup = new Coffeemachine2();
            int[] expect = {5, 2, 2};
            assertThat(cup.changes(100, 91), is(expect));
        }

        @Test
        public void whenFiftysevenThenTenTenTenTenTwoOne() {
            Coffeemachine2 cup = new Coffeemachine2();
            int[] expect = {10, 10, 10, 10, 2, 1};
            assertThat(cup.changes(100, 57), is(expect));
        }
    }
