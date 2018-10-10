package ru.job4j.bank;

import java.util.Objects;

public class User {
    private String Name;
    private String passport;

    public User(){

    }

    public User(String name, String passport) {
        Name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passport == user.passport &&
                Objects.equals(Name, user.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, passport);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
