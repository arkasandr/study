package ru.job4j.profession;

public class Teacher extends Profession {

    public void teachStudent(Student student) {
        System.out.println(getName() + " teaches " + student.getName());
    }
}
