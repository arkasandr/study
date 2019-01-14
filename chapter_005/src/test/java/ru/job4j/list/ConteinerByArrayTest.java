package ru.job4j.list;


import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ConteinerByArrayTest {

    @Test
    public void whenAddTwoElementsThenSumResult() {
        ConteinerByArray<Integer> conteiner = new ConteinerByArray<>();
        conteiner.add(1);
        conteiner.add(2);
        int result = conteiner.get(0) + conteiner.get(1);
        assertThat(result, is(3));
    }

    @Test
    public void whenAddAndCheckNext() {
        ConteinerByArray<Integer> conteiner = new ConteinerByArray<>(1);
        conteiner.add(1);
        Iterator<Integer> it = conteiner.iterator();
        assertThat(it.next(), is(1));
    }
}