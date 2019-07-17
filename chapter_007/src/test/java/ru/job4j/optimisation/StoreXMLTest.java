package ru.job4j.optimisation;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

    private final File target = new File("target.xml");

    @Test
    public void whenSaveEntriesToXmlFileThenFileExists() throws ClassNotFoundException, SQLException, IOException {
        StoreXML str = new StoreXML(target);
        Config conf = new Config();
        StoreSQL st = new StoreSQL(conf);
        st.connectDB();
        st.generate(1);
        List<Field> result = st.getEntries();
        str.save(result);
        st.close();
        assertThat(target.exists(), is(true));
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n<entries>\r\n    "
                +
                "<entry>\r\n        <field>1</field>\r\n    </entry>\r\n</entries>";
        Stream<String> lines = Files.lines(Paths.get("target.xml"));
        // Formatting like \r\n will be lost
        // String content = lines.collect(Collectors.joining());
        // UNIX \n, Windows \r\n
        String content = lines.collect(Collectors.joining(System.lineSeparator()));
        assertThat(content, is(expected));
    }
}