package ru.job4j.lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiapasonFunctionTest {
    @Test
    public void whenLinearThen579(){
        DiapasonFunction linear = new DiapasonFunction();
        List<Double> result = linear.diapason(1,4,
                x -> x*2 +3);
        assertThat(result, is(Arrays.asList(5D, 7D, 9D)));
    }

    @Test
    public void whenQuadraticThen51119(){
        DiapasonFunction linear = new DiapasonFunction();
        List<Double> result = linear.diapason(1,4,
                x -> x*x + 3*x + 1);
        assertThat(result, is(Arrays.asList(5D, 11D, 19D)));
    }

    @Test
    public void whenCubicThen52053(){
        DiapasonFunction linear = new DiapasonFunction();
        List<Double> result = linear.diapason(1,4,
                x -> x*x*x +3*x*x - x + 2);
        assertThat(result, is(Arrays.asList(5D, 20D, 53D)));
    }
}
