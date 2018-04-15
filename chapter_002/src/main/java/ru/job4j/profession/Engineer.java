package ru.job4j.profession;

public class Engineer extends Profession {

    public  void treatPat(House house) {
        System.out.println("Engineer builds" + " " + house.getType() + " house");
    }

}
