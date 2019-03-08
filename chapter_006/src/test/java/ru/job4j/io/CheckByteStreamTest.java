package ru.job4j.io;

import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckByteStreamTest {

    @Test
    public void whenStreamEndWithEvenNumberThenTrue() {
        CheckByteStream test = new CheckByteStream();
        byte[] input = {1, 2, 3, 4};
        InputStream in = new ByteArrayInputStream(input);
        assertThat(test.isNumber(in), is(true));
    }

    @Test
    public void whenStreamEndWithOddNumberThenFalse() {
        CheckByteStream test = new CheckByteStream();
        String str = "string";
        InputStream in = new ByteArrayInputStream(str.getBytes());
        assertThat(test.isNumber(in), is(false));
    }

}