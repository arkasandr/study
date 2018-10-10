package ru.job4j.search;

public class User implements Comparable<User> {
        private int id;
        private int age;
        private String name;
        private String city;

        public User(int id, String name, String city) {
            this.id = id;
            this.name = name;
            this.city = city;
        }

    public User(String name, int age) {
            this.name = name;
            this.age = age;
    }

    public int getId() {
            return id;
        }

    public int getAge() {
        return age;
    }

    public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }



    @Override
    public int compareTo(User o) {
        return age - o.age;
    }
    }

