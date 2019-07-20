package ru.job4j.io;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

public class FileScannerTest {

//    @Test
//    public void whenSearchTwoExistFileExt() throws IOException {
//        FileScanner fs = new FileScanner();
//        List<String> list = new ArrayList<>();
//        list.add(".vsd");
//        list.add(".SLDPRT");
//        String path = System.getProperty("java.io.tmpdir");
//        String testDir = path + "\\testDir";
//        new File(testDir).mkdirs();
//        new File(testDir + "\\firstDir").mkdirs();
//        new File(testDir + "\\secondDir").mkdirs();
//        new File(testDir + "\\secondDir\\thirdDir").mkdirs();
//        File firstFile = new File(testDir + "\\firstFile.vsd");
//        firstFile.createNewFile();
//        File secondFile = new File(testDir + "\\secondDir\\secondFile.SLDPRT");
//        secondFile.createNewFile();
//        List<File> result = fs.searchFiles(testDir, list);
//        assertThat(result, containsInAnyOrder(firstFile, secondFile));
//    }

    private final String temp = String.format("%s/test", System.getProperty("java.io.tmpdir"));

    @Before
    public void before() throws IOException {
        File test = new File(temp);
        test.mkdir();
        File.createTempFile("test", ".txt", test);
        File.createTempFile("test", ".rar", test);
    }

    @After
    public void after() {
        File testDir = new File(temp);
        File[] files = testDir.listFiles();
        if (files != null) {
            for (File f:files) {
                f.delete();
            }
        }
        testDir.delete();
    }

    @Test
    public void whenSearchTwoExistFilesInDirectory() throws IOException {
        FileScanner fs = new FileScanner();
        List<String> list = new ArrayList<>();
        list.add("txt");
        list.add("rar");
        List<File> result = fs.searchFiles(temp, list);
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenSearchOneExistFileInDirectory() throws IOException {
        FileScanner fs = new FileScanner();
        List<String> list = new ArrayList<>();
        list.add("txt");
        list.add("jpeg");
        List<File> result = fs.searchFiles(temp, list);
        assertThat(result.size(), is(1));
    }

    @Test
    public void whenSearchNoExistFileInDirectory() throws IOException {
        FileScanner fs = new FileScanner();
        List<String> list = new ArrayList<>();
        list.add("jpeg");
        List<File> result = fs.searchFiles(temp, list);
        assertThat(result.isEmpty(), is(true));
    }



}