package ru.job4j.jdk11;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparator<Student>{

    private String name;
    private int scope;

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public Student() {

    }

    @Override
    public int compare(Student s1, Student s2) {return s1.getScope() - s2.getScope();
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getScope))
                //.filter(s -> Optional.ofNullable(s.getName()))
                //.ofNullable()
                //.flatMap(key -> Stream.ofNullable(System.getProperty(key.getName())))
               .flatMap(Stream::ofNullable)
                .dropWhile(x -> x.getScope()< bound)
                .collect(Collectors.toList());
    }
}
