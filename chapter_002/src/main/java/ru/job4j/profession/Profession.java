package ru.job4j.profession;

public class Profession {
    private String name;
    private int salary;

    public Profession() {

    }

    public Profession(String name) {
        this.name = name;
    }

    public Profession(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Profession(int salary) {
        this.salary = salary;
    }


    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return salary;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}