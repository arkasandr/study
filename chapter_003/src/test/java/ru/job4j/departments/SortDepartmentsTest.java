package ru.job4j.departments;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortDepartmentsTest {

    @Test
    public void whenK1IsAbsent() {
        SortDepartments firstSet = new SortDepartments();
        String[] input = {"K1\\SK1", "K1\\SK2"};
        String[] result = {"K1"};
        assertThat(firstSet.findAllDeps(input).toArray(), is(result));
    }

    @Test
    public void whenK1AndK2SK1IsAbsent() {
        SortDepartments secondSet = new SortDepartments();
        String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = {"K1", "K2\\SK1"};
        assertThat(secondSet.findAllDeps(input).toArray(), is(result));
    }

    @Test
    public void whenUpSort() {
        SortDepartments thirdSet = new SortDepartments();
        String[] input = {"K1\\SK1", "K2\\SK1"};
        String[] result = {"K1", "K1\\SK1", "K2", "K2\\SK1"};
        assertThat(thirdSet.sortUpDeps(input).toArray(), is(result));
    }

    @Test
    public void whenDownSort() {
        SortDepartments forthSet = new SortDepartments();
        String[] input = {"K1", "K1\\SK1", "K2\\SK1"};
        String[] result = {"K2\\SK1", "K2", "K1\\SK1", "K1"};
        assertThat(forthSet.sortDownDeps(input).toArray(), is(result));
    }
}
