package ru.job4j.comparison;

import java.util.Objects;

public class User implements Comparable<User> {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * new Integer(id).hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return false;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", id="
                + id
                + '}';
    }
}
