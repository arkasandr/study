package ru.job4j.optimisation;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "entries")
//@XmlAccessorType(XmlAccessType.FIELD)

public class Entry {

    public Entry() {
    }

    private List<Field> entries;

    public Entry(List<Field> entries) {
        this.entries = entries;
    }
    @XmlElement(name = "entry")
    public List<Field> getEntries() {
        return entries;
    }


    public void setEntries(List<Field> entries) {
        this.entries = entries;
    }
}
