package com.example.redcollar1.models;

public class Person {
    private int id;
    private String name;
    private String age;
    private String email;
    private String login;
    private String pass;

    public Person() {

    }

    public Person(int id, String name, String age, String email, String login, String pass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.login = login;
        this.pass = pass;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
