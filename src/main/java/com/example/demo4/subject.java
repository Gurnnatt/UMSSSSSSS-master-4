package com.example.demo4;

public class subject {
    private String name, code; //private variables to store subject name and code

    //constructor to initialize the subject object
    public subject(String name, String code) {
        this.name = name;
        this.code = code;
    }

    //getter method to get subject name
    public String getName() {
        return name;
    }

    //setter method to update subject name
    public void setName(String name) {
        this.name = name;
    }

    //getter method to get subject code
    public String getCode() {
        return code;
    }

    //setter method to update subject code
    public void setCode(String code) {
        this.code = code;
    }

}

