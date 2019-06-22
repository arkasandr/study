package ru.job4j.optimisation;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ConvertXSQT {

    private File source;
    private File dest;
    private File schema;

    public void convert(File source, File dest, File schema) {
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer trans = tf.newTransformer(
                    new StreamSource(schema));
            trans.transform(new StreamSource(source), new StreamResult(dest));
            trans.transform(new StreamSource(source), new StreamResult(System.out));

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
