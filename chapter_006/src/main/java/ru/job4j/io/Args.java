package ru.job4j.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Args {


    private String directory;
    private String output;
    /**
     * создаем список для хранения исключаемых при архивации расширений файлов
     */
    private List<String> exts = new ArrayList<>();
    /**
     * создаем отображение для хранения значений по ключам (-d, -e, -o).
     */
    private Map<String, List<String>> params = new HashMap<>();

    /**
     * конструктор класса, который заполняет отображение params ключами и значениями.
     * путем парсинга символьной строки при вводе команды архивации.
     */

    public Args(String[] args) {
        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
        String a = args[i];
        if (a.charAt(0) == '-') {
            if (a.length() < 2) {
                System.err.println("Error at argument " + a);
                return;
            }
            options = new ArrayList<>();
            params.put(a.substring(1), options);
            System.out.println(a.substring(1));
        } else if (options != null) {
            options.add(a);
        } else {
            System.err.println("Illegal parameter usage");
            return;
        }
    }
    }

    /**
     * Метод возвращает путь директории, с которой следует начать архивацию.
     *
     * @return начальная директория.
     */
    public String directory() {
        for (String key : this.params.keySet()) {
            if (!key.equals("d")) {
                System.out.println("Invalid path. Check directory!");
            } else {
                directory = params.get(key).get(0);
            }
        }
        return directory;
    }

    /**
     * Метод возвращает имя создаваемого архива.
     *
     * @return имя архива.
     */
    public String output() {
        for (String key : this.params.keySet()) {
            if (!key.equals("o")) {
                System.out.println("Zip name is absent!");
            }
            output = params.get(key).get(0);
        }
        return output;
    }

    /**
     * Метод возвращает список расширений файлов, которые не должны быть вкключены в архив.
     *
     * @return список исключаемых расширений.
     */
    public List<String> exts() {
        for (String key : this.params.keySet()) {
            if (!key.equals("e")) {
                System.out.println("Check exclude extensions!");
            } else {
            for (String value : params.get(key)) {
                exts.add(value);
            }
            }
        }
        return exts;
    }

}
