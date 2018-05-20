package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void whenFigureIsOnCheckCellThenFalse() throws FigureNotFoundException {

        Cell source = new Cell(6, 4);
        Bishop bish = new Bishop(new Cell(6, 4));
        Board board = new Board();
        board.add(bish);
        assertThat(board.checkCell(source), is(false));
    }

    @Test
    public void whenFigureMoveToDestThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(6, 4);
        Cell dest = new Cell(2, 8);
        Bishop bish1 = new Bishop(new Cell(6, 4));
        board.add(bish1);
        assertThat(board.move(source, dest), is(true));
    }

    @Test (expected = OccupiedWayException.class)
    public void whenWayIsOccupiedThenException() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(6, 4);
        Cell dest = new Cell(2, 8);
        Bishop bish1 = new Bishop(new Cell(6, 4));
        Bishop bish2 = new Bishop(new Cell(3, 7));
        board.add(bish1);
        board.add(bish2);
        board.move(source, dest);
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenWayIsImpossibleThenException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(6, 4);
        Cell dest = new Cell(1, 1);
        Bishop bish = new Bishop(new Cell(6, 4));
        board.add(bish);
        board.move(source, dest);
    }
}
