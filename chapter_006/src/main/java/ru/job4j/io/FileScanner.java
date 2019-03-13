package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class FileScanner {

    /**
     * Метод возвращает список всех файлов, заданного расширения, в указанном каталоге и подкаталогах.
     *
     * @param parent путь к каталогу, с которого стоит начать поиск
     * @param exts   список заданных расширений файлов для поиска
     * @return список файлов с заданным расширением
     */

    public List<File> searchFiles(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        File catalog = new File(parent);
        Queue<File> data = new LinkedList<>();
        Collections.addAll(data, catalog.listFiles());
        while (!data.isEmpty()) {
            File currentFile = data.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(data, currentFile.listFiles());
            } else {
                for (String str : exts) {
                    if (currentFile.getName().endsWith(str)) {
                        result.add(currentFile);
                    }
                }
            }
        }
        return result;
    }

}
