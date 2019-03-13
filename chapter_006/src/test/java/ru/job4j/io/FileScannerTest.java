package ru.job4j.io;

import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FileScannerTest {

    @Test
    public void whenSearchTwoExistFileExt() throws IOException {
        FileScanner fs = new FileScanner();
        List<String> list = new ArrayList<>();
        list.add(".vsd");
        list.add(".SLDPRT");
        List<File> result = fs.searchFiles("C:/Users/Arkaleks/AppData/Local/Temp/", list);
        assertThat(list, containsInAnyOrder(".vsd", ".SLDPRT"));
    }
}