package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {

    @Test
    public void whenPushTwoElementsAndPollThemIsSumResult() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        Integer result = stack.poll() + stack.poll();
        assertThat(result, is(3));
    }

    @Test
    public void whenPushThreeElementsAndPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
    }

    @Test
    public void whenPushTwoElementsAndPollAndPush() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.poll();
        stack.push(3);
        assertThat(stack.poll(), is(3));
    }
}
