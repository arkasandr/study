package ru.job4j.chess;
import java.util.Arrays;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bishop extends Figure {

    @Override
    Figure copy(Cell dest) {
        return new Bishop(dest);
    }


    public Bishop(Cell position) {
        super(position);
    }

    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int xsource = source.getX();
        int ysource = source.getY();
        int xdest = dest.getX();
        int ydest = dest.getY();
        int size = Math.abs(xsource - xdest);
        Cell[] cells = new Cell[size];

        if (Math.abs(xsource - xdest) == Math.abs(ysource - ydest)) {
                if (xdest - xsource > 0) {
                    if (ydest - ysource > 0) {
                        for (int i = 0; i < size; i++) {
                        cells[i] = new Cell(xsource + (i + 1), ysource + (i + 1));
                  }
                    } else {
                        for (int i = 0; i < size; i++) {
                        cells[i] = new Cell(xsource + (i + 1), ysource - (i + 1));
                    }
               }
                } else {
                    if (ydest - ysource > 0) {
                   for (int i = 0; i < size; i++) {
                        cells[i] = new Cell(xsource - (i + 1), ysource + (i + 1));
                   }
                    } else {
                   for (int i = 0; i < size; i++) {
                        cells[i] = new Cell(xsource - (i + 1), ysource - (i + 1));
                    }
                }
            }
        } else {
            throw new ImpossibleMoveException("Так ходить нельзя!");
        }
        return cells;
    }
}