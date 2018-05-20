package ru.job4j.chess;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public abstract class Figure {
    final Cell position;
    abstract Cell position();

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;


    abstract Figure copy(Cell dest);
}
