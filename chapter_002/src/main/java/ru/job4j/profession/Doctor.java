package ru.job4j.profession;

public class Doctor extends Profession {

    public void nameDoctor() {
        setName("Surgeon");
    }

        public String treatPatient(Patient patient) {
            return patient.getName();
        }
    }

