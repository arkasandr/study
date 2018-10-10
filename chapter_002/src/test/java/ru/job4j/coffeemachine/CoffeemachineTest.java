package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CoffeemachineTest {
    private Coffeemachine coffeeMachine = new Coffeemachine();

    List<Integer> coins(Integer... ints) {
        return new ArrayList<>(Arrays.asList(ints));
    }

    @Test
    public void banknote50price35changeFor15() {
        assertEquals(coins(10, 5), coffeeMachine.change(50, 35));
    }

    @Test
    public void changeFor7() {
        assertEquals(coins(5, 2), coffeeMachine.change(100, 93));
    }
}