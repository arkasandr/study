package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DeleteAbuse {

        /**
         * Метод преобразует входной поток в выходной, удаляя слова, указанные в массиве.
         *
         * @param in входной поток
         * @param out выходной поток
         * @param abuse массив запрещенных слов
         */
        public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            ) {
                reader.lines()
                        .forEach(word -> {
                            for (String abuseWord : abuse) {
                                word = word.replaceAll(abuseWord, " ");
                            }
                            try {
                                writer.write(word);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        }
}

