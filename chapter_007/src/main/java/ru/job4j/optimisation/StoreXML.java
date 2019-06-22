package ru.job4j.optimisation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Field> entries) {
        Entry entr = new Entry (entries);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entr, target);
            jaxbMarshaller.marshal(entr, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
