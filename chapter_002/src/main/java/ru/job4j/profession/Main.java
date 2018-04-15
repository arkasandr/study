package ru.job4j.profession;

public class Main {
    public static void main(String[] args) {
        Doctor doc1 = new Doctor();
        doc1.nameDoctor();
        System.out.println("Doc1" + " " + doc1.getName());


        Doctor doc2 = new Doctor();
        doc2.setName("Dantist");
        System.out.println("Doc2" + " " + doc2.getName());


        Patient pat1 = new Patient();
        pat1.setName("Dave");
        System.out.println("Patient1" + " " + pat1.getName());


        Patient pat2 = new Patient(500);
        System.out.println(pat1.getName() + " pays " + pat2.getPayment() + " RUB");

        Patient pat3 = new Patient();
        pat3.setName("Bill");
        System.out.println("Doctor treats " + doc2.treatPatient(pat3));

        House building1 = new House();
        building1.setType("brick");

        Engineer master = new Engineer();
        master.treatPat(building1);


        Student stu1 = new Student();
        stu1.setName("Jack");
        stu1.setDepartment("CS50");
        System.out.println(stu1.toString());

        Teacher prof = new Teacher();
        prof.setName("Mr.Chong");
        prof.teachStudent(stu1);

    }
}
