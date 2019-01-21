package ru.job4j.map;

import java.util.Calendar;

public class User {

    private String Name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        Name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}
