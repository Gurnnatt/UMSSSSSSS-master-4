
package com.example.demo4;

public class student {
    //declaring private variables for needed for student object
    private String id, name, address, telephone, email;
    private String academicLevel, currentSemester;
    private String profilePhoto, subjectsRegistered, thesisTitle, progress, password;

    //constructor to initialize a student object with the given attributes
    public student(String id, String name, String address, String telephone, String email,
                   String academicLevel, String currentSemester, String profilePhoto,
                   String subjectsRegistered, String thesisTitle, String progress, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.academicLevel = academicLevel;
        this.currentSemester = currentSemester;
        this.profilePhoto = profilePhoto;
        this.subjectsRegistered = subjectsRegistered;
        this.thesisTitle = thesisTitle;
        this.progress = progress;
        this.password = password;
    }

//all of the getter methods to access the student attributes
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getAcademicLevel() { return academicLevel; }
    public String getCurrentSemester() { return currentSemester; }
    public String getProfilePhoto() { return profilePhoto; }
    public String getSubjectsRegistered() { return subjectsRegistered; }
    public String getThesisTitle() { return thesisTitle; }
    public String getProgress() { return progress; }
    public String getPassword() { return password; }
}
