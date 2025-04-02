package com.example.demo4;

public class faculty {
    //private variables to store important faculty details
    private String id, name, degree, department, email, location, offered, password;

    //constructor to initialize the object with specific details
    public faculty(String id, String name, String degree, String department, String email, String location, String offered, String password) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.department = department;
        this.email = email;
        this.location = location;
        this.offered = offered;
        this.password = password;
    }

    //getter methods for faculty id, name, department, and email
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }

    //getter and setter methods for faculty degree
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    //getter and setter methods for faculty location
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    //getter and setter methods for courses offered by faculty
    public String getOffered() {
        return offered;
    }
    public void setOffered(String offered) {
        this.offered = offered;
    }

    //getter and setter methods for faculty password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
