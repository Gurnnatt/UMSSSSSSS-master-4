package com.example.demo4;
import java.util.Objects;

public class course {

    //private variables to store important course details
    private String code, name, teacher, subcode, secnum, capacity, lectime, examdate, location;

    //constructor to initialize the object with specific details
    public course( String code, String name, String subcode, String secnum, String capacity, String lectime, String examdate, String location, String teacher)
    {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.subcode = subcode;
        this.secnum = secnum;
        this.capacity = capacity;
        this.lectime = lectime;
        this.examdate = examdate;
        this.location = location;
    }

    //getter method to retrieve the course name
    public String getName() {
        return name;
    }
    //setter method to update course name
    public void setName(String name) {
        this.name = name;
    }

    //getter method for course code
    public String getCode() {
        return code;
    }
    //setter method to update course code
    public void setCode(String code) {
        this.code = code;
    }

    //getter method for course teacher
    public String getTeacher() {
        return teacher;
    }
    //setter method to update course teacher
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    //getter and setter methods for subject code
    public String getSubcode() {
        return subcode;
    }
    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    //getter and setter methods for section number
    public String getSecnum() {
        return secnum;
    }
    public void setSecnum(String secnum) {
        this.secnum = secnum;
    }

    //getter and setter methods for class capacity
    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    //getter and setter methods for lecture time
    public String getLectime() {
        return lectime;
    }
    public void setLectime(String lectime) {
        this.lectime = lectime;
    }

    //getter and setter methods for exam date
    public String getExamdate() {
        return examdate;
    }
    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    //getter and setter methods for location
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    //method that checks if the object is being compared to itself or with an object
    //from a different class
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        course other = (course) obj;
        return this.code.equals(other.code) && this.secnum.equals(other.secnum); // Unique by code & section
    }

    //create hash code based on the code and secnum variables
    @Override
    public int hashCode() {
        return Objects.hash(code, secnum);
    }


}


//package com.example.demo4;
//
//public class course {
//
//    //private variables to store important course details
//    private String Cname, code, Sname, section, teacher, capacity, time, exam, location;
//
//    //constructor to initialize the object with specific details
//    public course(String Cname, String code, String name, String teacher, String time, String section, String capacity, String exam, String location)
//    {
//        this.code = code;
//        this.Cname = Cname;
//        this.Sname = name;
//        this.capacity = capacity;
//        this.exam = exam;
//        this.location = location;
//        this.teacher = teacher;
//        this.time = time;
//        this.section = section;
//    }
//
//    //getter method to retrieve the course name
//    public String getCname() {
//        return Cname;
//    }
//    //setter method to update course name
//    public void setName(String name) {
//        this.Cname = Cname;
//    }
//
//    public String getCapacity() { return capacity; }
//    public void setCapacity(String capacity) { this.capacity = capacity; }
//
//    public String getExam() { return exam; }
//    public void setExam(String exam) { this.exam = exam; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public String Sname() { return Sname; }
//    public void setSname(String sname) { Sname = sname; }
//
//    //getter method for course code
//    public String getCode() {
//        return code;
//    }
//
//    //setter method to update course code
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    //getter method to get course time
//    public String getTime() {
//        return time;}
//
//    //setter method to update course time
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    //getter method for course section
//    public String getSection() {
//        return section;
//    }
//
//    //setter method to update course section
//    public void setSection(String section) {
//        this.section = section;
//    }
//
//    //getter method for course teacher
//    public String getTeacher() {
//        return teacher;
//    }
//
//    //setter method to update course teacher
//    public void setTeacher(String teacher) {
//        this.teacher = teacher;
//    }
//}
