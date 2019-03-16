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
        String path = System.getProperty("java.io.tmpdir");
        String testDir = path + "\\testDir";
        new File(testDir).mkdirs();
        new File(testDir + "\\firstDir").mkdirs();
        new File(testDir + "\\secondDir").mkdirs();
        new File(testDir + "\\secondDir\\thirdDir").mkdirs();
        File firstFile = new File(testDir + "\\firstFile.vsd");
        firstFile.createNewFile();
        File secondFile = new File(testDir + "\\secondDir\\secondFile.SLDPRT");
        secondFile.createNewFile();
        List<File> result = fs.searchFiles(testDir, list);
        assertThat(result, containsInAnyOrder(firstFile, secondFile));
    }
}