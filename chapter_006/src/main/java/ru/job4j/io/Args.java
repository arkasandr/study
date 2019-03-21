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

public class Args{

    /**
     * Метод возвращает список всех файлов, заданного расширения, в указанном каталоге и подкаталогах.
     *
     * @param parent путь к каталогу, с которого стоит начать поиск
     * @param exts   список заданных расширений файлов для поиска
     * @return список файлов с заданным расширением
     */


    private String directory;
    private String output;
    private List<String> exts = new ArrayList<>();
    private Map<String, List<String>> params = new HashMap<>();



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
        }
        else if (options != null) {
            options.add(a);
        }
        else {
            System.err.println("Illegal parameter usage");
            return;
        }
    }
    }

    public String directory() {
        for(String key : this.params.keySet()) {
            if(!key.equals("d")) {
                System.out.println("Invalid path. Check directory!");
            }
            directory = params.get(key).get(0);
        }
        return directory;
    }

    public String output() {
        for(String key : this.params.keySet()) {
            if(!key.equals("o")) {
                System.out.println("Zip name is absent!");
            }
            output = params.get(key).get(0);
        }
        return output;
    }

    public List<String> exts() {
        for(String key : this.params.keySet()) {
            if(!key.equals("e")) {
                System.out.println("Check exclude extensions!");
            }
            for(String value : params.get(key)) {
                exts.add(value);
            }
        }
        return exts;
    }

}
