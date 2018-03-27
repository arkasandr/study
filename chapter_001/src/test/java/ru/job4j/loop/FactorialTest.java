package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/** Alex Arks (arkaleks@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class FactorialTest {

 @Test
 public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
    Factorial five = new Factorial();
    int result = five.calc(5);
    assertThat(result, is(120));
   }
   
   @Test
  public void whenCalculateFactorialForZeroThenOne() {
    Factorial zero = new Factorial();
    int result = zero.calc(0);
    assertThat(result, is(1));
   }



}