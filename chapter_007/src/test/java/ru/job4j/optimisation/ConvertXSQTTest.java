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

public class ConvertXSQTTest {

//    private final File target = new File("C:\\projects\\study\\chapter_007\\src\\main\\java\\ru\\job4j\\"
//            +
//            "optimisation\\target.xml");
//    private final File dest = new File("C:\\projects\\study\\chapter_007\\src\\main\\java\\ru\\job4j\\"
//            +
//            "optimisation\\dest.xml");
//    private final File schema = new File("C:\\projects\\study\\chapter_007\\src\\main\\java\\ru\\job4j\\"
//            +
//            "optimisation\\schema.xsl");
//            "optimisation\\schema.xsl");

        private final File target = new File("target.xml");
    private final File dest = new File("dest.xml");
    private final File schema = new File("schema.xsl");

    @Test
    public void whenConvertXmlThenAnotherXmlCreate() throws ClassNotFoundException, SQLException, IOException {
        Config conf = new Config();
        StoreSQL st = new StoreSQL(conf);
        st.connectDB();
        st.generate(1);
        StoreXML str = new StoreXML(target);
        List<Field> result = st.getEntries();
        str.save(result);
        assertThat(result.size(), is(1));
        ConvertXSQT xsl = new ConvertXSQT();
        xsl.convert(target, dest, schema);
        assertThat(dest.exists(), is(true));
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><field href=\"1\"/></entries>";
        Stream<String> lines = Files.lines(Paths.get("C:\\projects\\study\\chapter_007\\src\\main\\java\\ru\\job4j\\"
                +
                "optimisation\\dest.xml"));
        // Formatting like \r\n will be lost
        // String content = lines.collect(Collectors.joining());
        // UNIX \n, Windows \r\n
        String content = lines.collect(Collectors.joining(System.lineSeparator()));
        assertThat(content, is(expected));
        st.close();
    }
}