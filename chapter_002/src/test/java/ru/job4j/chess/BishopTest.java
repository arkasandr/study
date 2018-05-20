package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {

    @Test
    public void whenWayIsPossible() {
        Cell source = new Cell(6, 4);
        Cell dest = new Cell(2, 8);
        Bishop bish = new Bishop(source);
        Cell[] expected = {new Cell(5, 5), new Cell(4, 6), new Cell(3, 7), new Cell(2, 8)};
        Cell[] real = bish.way(source, dest);
        for (int i = 0; i < real.length; i++) {
            assertThat(real[i].equals(expected[i]), is(true));
        }
    }
}
