package ru.job4j.tracker;

import java.util.*;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }


//    public int ask(String question, int[] range) {
//        int key = Integer.valueOf(this.ask(question));
//        boolean exist = false;
//        for (int value : range) {
//            if (value == key) {
//                exist = true;
//                break;
//            }
//        }
//        if (!exist) {
//            throw new MenuOutException("Вне диапазона");
//        }
//            return key;
//    }

    public int ask(String question, List<UserAction> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (UserAction action : range) {
            if (action.key() == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Вне диапазона");
        }
        return key;
    }
}

