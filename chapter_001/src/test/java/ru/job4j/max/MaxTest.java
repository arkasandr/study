package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/** Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class MaxTest {

 @Test
public void whenFirstLessSecond() {
    Max maxim = new Max();
    int result = maxim.max(1, 2);
    assertThat(result, is(2));
}

    @Test
    public void whenFirstLessSecondLessThird() {
        Max maxim = new Max();
        int result = maxim.max2(1, 5, 3);
        assertThat(result, is(5));
    }
}