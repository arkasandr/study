package ru.job4j.profession;

public class Student extends Teacher {
    public String department;


    public String toString() {
        return getName() + " studies on " + department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
