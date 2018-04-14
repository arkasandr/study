package ru.job4j.profession;

public class Doctor extends Profession {

    public void nameDoctor() {
        setName("Surgeon");
    }

//    public  void treatPat(Patient patient) {
//       System.out.println("Doctor treats" + " " + patient.getName());

        public String treatPatient(Patient patient) {
            return patient.getName();
        }
    }

