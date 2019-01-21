package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    @Override
//    public int hashCode() {
//        return 7 * Objects.hashCode(name)
//                + 11 * new Integer(children).hashCode()
//                + 13 * Objects.hashCode(birthday);
//    }


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
        return Objects.equals(name, ((User) obj).name)
                && children == other.children
                && Objects.equals(birthday, other.birthday);
    }
}
