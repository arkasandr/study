package ru.job4j.chess;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Board {

    Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[index] = figure;
        index++;
    }

    /**
     * метод проверяет, есть ли фигура в клетке source, может ли фигура пройти в указанную клетку dest, и свободен ли путь для фигуры.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        //boolean result = false;
        for (Figure figure : figures) {
            if (!checkCell(source)) {
                Cell[] track = (figure.way(source, dest));
                    for (Figure figure1 : figures) {
                        for (Cell cell : track) {
                        if (figure1 != null && figure1.position.getX() == cell.getX()) {
                            throw new OccupiedWayException("Ход сделать невозможно! Путь занят!");
                        }
                    }
                }
            } else {
                throw new FigureNotFoundException("Клетка пуста! Выберите фигуру!");
            }
            figure.copy(dest);
            return true;
        }
            return false;
    }
    /**
     * метод проверяет, есть ли фигура в клетке source
     */
//    public boolean checkCell(Cell source) throws FigureNotFoundException {
//        boolean result = false;
//        for (Figure figure : figures) {
//            if (figure != null && figure.position.getX() == source.getX() && figure.position.getY() == source.getY()) {
//                break;
//            } else {
//                throw new FigureNotFoundException("Клетка пуста! Выберите фигуру!");
//            }
//        }
//        return result;
//    }


//    public boolean checkCell(Cell source) throws FigureNotFoundException {
//        boolean result = false;
//        Predicate<Figure> predicate = figure -> figure != null && figure.position().equals(source);
//        for (Figure figure : figures) {
//            if (predicate.test(figure)) {
//                break;
//
//            } else {
//                throw new FigureNotFoundException("Клетка пуста! Выберите фигуру!");
//            }
//        }
//        return result;
//    }

    public boolean checkCell(Cell source) throws FigureNotFoundException {
        boolean result = false;
        Predicate<Figure> predicate = figure -> figure != null && figure.position().equals(source);
        Arrays.stream(figures)
                .filter(fig -> predicate.test(fig)).findFirst().orElseThrow(() -> new FigureNotFoundException("Клетка пуста! Выберите фигуру!"));


        return result;
    }

}
