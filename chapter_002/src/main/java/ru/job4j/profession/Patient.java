package ru.job4j.profession;

public class Patient extends Doctor {
    private int payment;
    public Patient() {

    }

    public Patient(int payment) {
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }


    public void setPayment(int payment) {
        this.payment = payment;
    }
}
