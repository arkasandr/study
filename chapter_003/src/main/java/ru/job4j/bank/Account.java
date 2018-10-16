package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private double value;
    private String requisites;

    public Account() {
    }

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites == account.requisites;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, requisites);
    }
}
