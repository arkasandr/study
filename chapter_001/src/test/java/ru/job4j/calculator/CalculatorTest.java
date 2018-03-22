package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	
	@Test
	public void whenSubtrack2Sub1Then1() {
		Calculator calc = new Calculator();
		calc.subtrack(2D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
	
	@Test
	public void whenDiv2On2Then1() {
		Calculator calc = new Calculator();
		calc.div(2D, 2D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
	
	@Test
	public void whenMult2On1Then2() {
		Calculator calc = new Calculator();
		calc.multiple(2D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

}

