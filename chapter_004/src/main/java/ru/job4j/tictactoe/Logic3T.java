package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }


    public boolean checkDiagonalX() {
        boolean resultRight = true;
        boolean resultLeft = true;
        for (var i = 0; i < table.length; i++) {
            resultLeft &= (table[i][i].hasMarkX());
            resultRight &= (table[table.length - 1 - i][i].hasMarkX());
        }
        if (resultLeft || resultRight) {
            return true;
        }
        return false;
    }


    public boolean checkLanesX() {
        boolean rows, cols;
        for (var i = 0; i < table.length; i++) {
            rows = true;
            cols = true;
            for (var k = 0; k < table.length; k++) {
                cols &= (table[i][k].hasMarkX());
                rows &= (table[k][i].hasMarkX());
            }
            if (cols || rows) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerX() {
        for (var i = 0; i < table.length; i++) {
            for (var k = 0; k < table.length; k++) {
                if (checkDiagonalX() || checkLanesX()) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkDiagonalO() {
        boolean resultRight = true;
        boolean resultLeft = true;
        for (var i = 0; i < table.length; i++) {
            resultLeft &= (table[i][i].hasMarkO());
            resultRight &= (table[table.length - 1 - i][i].hasMarkO());
        }
        if (resultLeft || resultRight) {
            return true;
        }
        return false;
    }


    public boolean checkLanesO() {
        boolean rows, cols;
        for (var i = 0; i < table.length; i++) {
            rows = true;
            cols = true;
            for (var k = 0; k < table.length; k++) {
                cols &= (table[i][k].hasMarkO());
                rows &= (table[k][i].hasMarkO());
            }
            if (cols || rows) {
                return true;
            }
        }
        return false;
    }


    public boolean isWinnerO() {
        for (var i = 0; i < table.length; i++) {
            for (var k = 0; k < table.length; k++) {
                if (checkDiagonalO() || checkLanesO()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasGap() {
        return true;
    }
}

