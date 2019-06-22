package ru.job4j.optimisation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fields")
public class Field {

    private int value;

    public Field() {
    }

    public Field(int field) {
        this.value = field;
    }
    @XmlElement(name = "field")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
