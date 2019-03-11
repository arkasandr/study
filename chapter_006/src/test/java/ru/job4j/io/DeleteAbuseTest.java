package ru.job4j.io;

import org.junit.Test;


import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeleteAbuseTest {

    @Test
    public void whenStreamEndWithEvenNumberThenTrue() throws IOException {
        DeleteAbuse test = new DeleteAbuse();
        String[] abuse = {"three four"};
        String  input = "one two three four, five";
        String result = "one two  , five";
        try (InputStream in = new ByteArrayInputStream(input.getBytes());
             OutputStream out = new ByteArrayOutputStream()) {
            test.dropAbuses(in, out, abuse);
            assertThat(out.toString(), is(result));
        }
    }

}