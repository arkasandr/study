package ru.job4j.optimisation;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenConnectionExistThenTrue() throws ClassNotFoundException, SQLException {
        Config conf = new Config();
        StoreSQL st = new StoreSQL(conf);
        assertThat(st.connectDB(), is(true));
    }

    @Test
    public void whenGenerateTenValuesThenSchemaContainsTheseValues() throws ClassNotFoundException, SQLException {
        Config conf = new Config();
        StoreSQL st = new StoreSQL(conf);
        st.connectDB();
        st.generate(10);
        List<Field> result = st.getEntries();
        st.close();
        assertThat(result.size(), is(10));
    }










}