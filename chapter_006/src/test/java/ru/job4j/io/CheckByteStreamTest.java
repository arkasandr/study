package ru.job4j.io;

import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckByteStreamTest {

    @Test
    public void whenStreamEndWithEvenNumberThenTrue() throws IOException {
        CheckByteStream test = new CheckByteStream();
        String str = "1234";
        try (InputStream in = new ByteArrayInputStream(str.getBytes())) {
            assertThat(test.isNumber(in), is(true));
        }
    }

    @Test
    public void whenStreamNotNumberThenFalse() throws IOException {
        CheckByteStream test = new CheckByteStream();
        String str = "string";
        try (InputStream in = new ByteArrayInputStream(str.getBytes())) {
            assertThat(test.isNumber(in), is(false));
        }
    }

}