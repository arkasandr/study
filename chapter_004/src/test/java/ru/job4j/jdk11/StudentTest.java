package ru.job4j.jdk11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StudentTest {
    @Test
    public void whenMore4Then4456() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jim", 6));
        students.add(new Student("Bob", 2));
        students.add(new Student("Martin", 4));
        students.add(new Student("Sally", 4));
        students.add(new Student("Alberta", 5));
        Student student = new Student();

        List<String> result = new ArrayList<>();
        for (Student a : student.levelOf(students, 4)) {
            result.add(a.getName());
        }

        List<String> expect = Arrays.asList(
                "Martin", "Sally", "Alberta", "Jim"
        );
        assertThat(result, is(expect));
    }


//    @Test
//    public void whenMore5Then56() {
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Jim", 6));
//        students.add(new Student("Bob", 2));
//        students.add(new Student("Martin", 4));
//        students.add(new Student());
//        students.add(new Student("Sally", 4));
//        students.add(new Student("Alberta", 5));
//        Student student = new Student();
//
//        List<Student> result = new ArrayList<>();
//        for (Student a : student.levelOf(students, 1)) {
//            result.add(a);
//        }
//
//        List<String> expect = Arrays.asList(
//                "Martin", "Sally", "Alberta", "Jim"
//        );
//        assertThat(result, is(expect));
//    }
}