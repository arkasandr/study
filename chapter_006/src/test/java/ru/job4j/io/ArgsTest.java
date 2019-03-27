package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArgsTest {

    @Test
    public void whenOutputIsRight() throws IOException {
        String[] testString = {"-d", "~/Downloads", "-e", ".pdf", "-o", "zip.rar"};
        String zipName = new Args(testString).output();
        assertThat(zipName, is("zip.rar"));
    }

    @Test
    public void whenDirectoryIsRight() throws IOException {
        String[] testString = {"-d", "~/Downloads", "-e", ".pdf", "-o", "zip.rar"};
        String zipName = new Args(testString).directory();
        assertThat(zipName, is("~/Downloads"));
    }

    @Test
    public void whenExcludeIsRight() throws IOException {
        String[] testString = {"-d", "~/Downloads", "-e", ".pdf", "-o", "zip.rar"};
        List<String> zipName = new Args(testString).exts();
        assertThat(zipName.get(0), is(".pdf"));
    }
}
