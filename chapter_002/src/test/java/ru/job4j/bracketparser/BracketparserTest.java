package ru.job4j.bracketparser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BracketparserTest {
    @Test
    public void whenSetIsCorrectThenTrue() {
        Bracketparser br = new Bracketparser();
        assertThat(br.parseBrackets("()[]"), is(true));
    }

    @Test
    public void whenSetIsUnCorrectThenFalse() {
        Bracketparser br = new Bracketparser();
        assertThat(br.parseBrackets(")("), is(false));
    }

    @Test
    public void whenSetIsOddThenFalse() {
        Bracketparser br = new Bracketparser();
        assertThat(br.parseBrackets(")(("), is(false));
    }

    @Test
    public void whenSetWithLettersIsCorrectThenTrue() {
        Bracketparser br = new Bracketparser();
        assertThat(br.parseBrackets("[s]fg(e)"), is(true));
    }
}
