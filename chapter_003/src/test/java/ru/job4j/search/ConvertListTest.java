package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

        @Test
        public void when7ElementsThen9() {
            ConvertList list = new ConvertList();
            List<int[]> input = new ArrayList();
            input.add(new int[]{1, 2});
            input.add(new int[]{3, 4, 5});
            input.add(new int[]{7, 8, 5});
            List<Integer> result = list.convert(input);
            List<Integer> expect = Arrays.asList(
                    1, 2, 3, 4, 5, 7, 8, 5
            );
            assertThat(result, is(expect));
        }
    }
