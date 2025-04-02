package com.example.demo4;
//
//public class studentProfile {
////Student ID	Name	Address	Telephone	Email	Academic Level	Current Semester	Profile Photo	Subjects Registered	Thesis Title	Progress	Password
//    private String studentID, name, address, telephone, email, academicLevel, currentSemester, profilePhoto, subjectsRegistered, thesis, progress, password;
//
//    public studentProfile(String studentID, String name, String address, String telephone, String email, String academicLevel, String currentSemester, String profilePhoto, String subjectsRegistered, String thesis, String progress, String password)
//    {
//        this.studentID = studentID;
//        this.name = name;
//        this.address = address;
//        this.telephone = telephone;
//        this.email = email;
//        this.academicLevel = academicLevel;
//        this.currentSemester = currentSemester;
//        this.profilePhoto = profilePhoto;
//        this.subjectsRegistered = subjectsRegistered;
//        this.thesis = thesis;
//        this.progress = progress;
//        this.password = password;
//    }
//
//
//    public String getStudentID() {
//        return studentID;
//    }
//
//    public void setStudentID(String studentID) {
//        this.studentID = studentID;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAcademicLevel() {
//        return academicLevel;
//    }
//
//    public void setAcademicLevel(String academicLevel) {
//        this.academicLevel = academicLevel;
//    }
//
//    public String getCurrentSemester() {
//        return currentSemester;
//    }
//
//    public void setCurrentSemester(String currentSemester) {
//        this.currentSemester = currentSemester;
//    }
//
//    public String getProfilePhoto() {
//        return profilePhoto;
//    }
//
//    public void setProfilePhoto(String profilePhoto) {
//        this.profilePhoto = profilePhoto;
//    }
//
//    public String getSubjectsRegistered() {
//        return subjectsRegistered;
//    }
//
//    public void setSubjectsRegistered(String subjectsRegistered) {
//        this.subjectsRegistered = subjectsRegistered;
//    }
//
//    public String getThesis() {
//        return thesis;
//    }
//
//    public void setThesis(String thesis) {
//        this.thesis = thesis;
//    }
//
//    public String getProgress() {
//        return progress;
//    }
//
//    public void setProgress(String progress) {
//        this.progress = progress;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.InputStream;
import java.util.Objects;

import java.io.FileOutputStream;
//
//public class studentProfile {
//
//    //declaring private student attirbutes
//    private String studentID, name, address, telephone, email,
//            academicLevel, currentSemester, profilePhoto,
//            subjectsRegistered, thesis, progress, password;
//
//    //UI elements for the student attributes
//    private Label idLabel, nameLabel, emailLabel, levelLabel, semesterLabel, eventLabel;
//    private TextField tfAddress, tfPhone, tfSubjects, tfThesis, tfProgress, tfPassword, tfPhoto;
//    private VBox layout;
//    private int rowIndex;
//
//    //constructor that initializes student profile UI and loads the student's data
//    public studentProfile(int rowIndex) {
//        this.rowIndex = rowIndex;
//        layout = new VBox(15);
//        loadProfile();
//    }
//
//    //method that loads the profile data from the UMS Data sheet
//    private void loadProfile() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet("Students ");
//            Row row = sheet.getRow(rowIndex);
//            if (row == null) {
//                System.err.println("Faculty row not found at index: " + rowIndex);
//                return;
//            }
//
//            //retrieve the student information from the UMS Data sheet
//            studentID = getCell(row, 0);
//            name = getCell(row, 1);
//            address = getCell(row, 2);
//            telephone = getCell(row, 3);
//            email = getCell(row, 4);
//            academicLevel = getCell(row, 5);
//            currentSemester = getCell(row, 6);
//            profilePhoto = getCell(row, 7);
//            subjectsRegistered = getCell(row, 8);
//            thesis = getCell(row, 9);
//            progress = getCell(row, 10);
//            password = getCell(row, 11);
//
//            //labels displaying student information
//            idLabel = new Label("Student ID: " + studentID);
//            nameLabel = new Label("Name: " + name);
//            emailLabel = new Label("Email: " + email);
//            levelLabel = new Label("Academic Level: " + academicLevel);
//            semesterLabel = new Label("Semester: " + currentSemester);
//
//            //text fields that the user can edit to update information
//            tfAddress = new TextField(address);
//            tfPhone = new TextField(telephone);
//            tfPhoto = new TextField(profilePhoto);
//            tfSubjects = new TextField(subjectsRegistered);
//            tfThesis = new TextField(thesis);
//            tfProgress = new TextField(progress);
//            tfPassword = new TextField(password);
//
//            eventLabel = new Label("Registered Events (stored as string): " + progress);
//
//            //save button to update the student profile
//            Button saveBtn = new Button("Save Changes");
//            saveBtn.setOnAction(e -> saveProfile());
//
//            //arrange all of the UI elements in layout
//            layout.getChildren().addAll(
//                    idLabel, nameLabel, emailLabel, levelLabel, semesterLabel,
//                    new Label("Address:"), tfAddress,
//                    new Label("Telephone:"), tfPhone,
//                    new Label("Profile Photo (URL):"), tfPhoto,
//                    new Label("Subjects Registered:"), tfSubjects,
//                    new Label("Thesis Title:"), tfThesis,
//                    new Label("Progress:"), tfProgress,
//                    new Label("Password:"), tfPassword,
//                    saveBtn
//            );
//
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //saves the updated student profile information back in the UMS Data sheet
//    private void saveProfile() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet("Students ");
//            Row row = sheet.getRow(rowIndex);
//
//            //update cell values with user input
//            row.getCell(2).setCellValue(tfAddress.getText());
//            row.getCell(3).setCellValue(tfPhone.getText());
//            row.getCell(7).setCellValue(tfPhoto.getText());
//            row.getCell(8).setCellValue(tfSubjects.getText());
//            row.getCell(9).setCellValue(tfThesis.getText());
//            row.getCell(10).setCellValue(tfProgress.getText());
//            row.getCell(11).setCellValue(tfPassword.getText());
//
//            //write updated data back to the file
//            FileOutputStream out = new FileOutputStream("src/main/resources/UMS_Data.xlsx");
//            workbook.write(out);
//            out.close();
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //gets the value of a given row in a row as a string
//    private String getCell(Row row, int col) {
//        Cell cell = row.getCell(col);
//        if (cell == null) return "";
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            default -> "";
//        };
//    }
//
//    //returns the layout containing all UI elements
//    public VBox getLayout() {
//        return layout;
//    }
//}


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class studentProfile {

    private String studentID, name, address, telephone, email,
            academicLevel, currentSemester, profilePhoto,
            subjectsRegistered, thesis, progress, password;

    private Label idLabel, nameLabel, emailLabel, levelLabel, semesterLabel, eventLabel;
    private TextField tfAddress, tfPhone, tfSubjects, tfThesis, tfProgress, tfPassword, tfPhoto;
    private VBox layout;
    private int rowIndex;
    private int role;

    public studentProfile(int rowIndex, int role) {
        this.rowIndex = rowIndex;
        this.role = role;
        layout = new VBox(15);
        loadProfile();
    }

    private void loadProfile() {
        try {
            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("Students ");
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                System.err.println("Student row not found at index: " + rowIndex);
                return;
            }

            studentID = getCell(row, 0);
            name = getCell(row, 1);
            address = getCell(row, 2);
            telephone = getCell(row, 3);
            email = getCell(row, 4);
            academicLevel = getCell(row, 5);
            currentSemester = getCell(row, 6);
            profilePhoto = getCell(row, 7);
            subjectsRegistered = getCell(row, 8);
            thesis = getCell(row, 9);
            progress = getCell(row, 10);
            password = getCell(row, 11);

            idLabel = new Label("Student ID: " + studentID);
            nameLabel = new Label("Name: " + name);
            emailLabel = new Label("Email: " + email);
            levelLabel = new Label("Academic Level: " + academicLevel);
            semesterLabel = new Label("Semester: " + currentSemester);

            tfAddress = new TextField(address);
            tfPhone = new TextField(telephone);
            tfPhoto = new TextField(profilePhoto);
            tfSubjects = new TextField(subjectsRegistered);
            tfThesis = new TextField(thesis);
            tfProgress = new TextField(progress);
            tfPassword = new TextField(password);

            tfAddress.setEditable(false);
            tfPhone.setEditable(false);
            tfPhoto.setEditable(false);
            tfSubjects.setEditable(false);
            tfThesis.setEditable(false);
            tfProgress.setEditable(false);
            tfPassword.setEditable(false);

            eventLabel = new Label("Registered Events: " + progress);

            layout.getChildren().addAll(
                    idLabel, nameLabel, emailLabel, levelLabel, semesterLabel,
                    new Label("Address:"), tfAddress,
                    new Label("Telephone:"), tfPhone,
                    new Label("Profile Photo (URL):"), tfPhoto,
                    new Label("Subjects Registered:"), tfSubjects,
                    new Label("Thesis Title:"), tfThesis,
                    new Label("Progress:"), tfProgress,
                    new Label("Password:"), tfPassword,
                    eventLabel
            );

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCell(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            default -> "";
        };
    }

    public VBox getLayout() {
        return layout;
    }
}
