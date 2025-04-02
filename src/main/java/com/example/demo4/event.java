package com.example.demo4;

public class event {
    //private variables to store important event details
    private String name, code, description, location, datetime, capacity, cost, image, registered;

    //constructor that initializes an event object with the following details
    public event(String name, String code, String description, String location, String time, String capacity, String cost, String image, String registered) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.location = location;
        this.datetime = time;
        this.capacity = capacity;
        this.cost = cost;
        this.image = image;
        this.registered = registered;
    }

    //getter and setter methods for the event name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //getter and setter methods for the event code
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    //getter and setter methods for the event description
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    //getter and setter methods for the event location
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    //getter and setter methods for the event date and time
    public String getDatetime() { return datetime; }
    public void setTime(String time) { this.datetime = datetime; }

    //getter and setter methods for event cost
    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    //getter and setter methods for event capacity
    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }

    //getter and setter methods for event image
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

    //getter and setter methods for event registration
    public String getRegistered() {return registered;}
    public void setRegistered(String registered) {this.registered = registered;}
}


