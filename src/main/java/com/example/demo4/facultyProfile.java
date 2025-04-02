package com.example.demo4;
//
//public class facultyProfile {
//
//    private String facultyID, name, degree, interest, email, officeLocation, coursesOffered, password;
//
//    public facultyProfile(String facultyID, String name, String degree, String interest, String email, String officeLocation, String coursesOffered, String password)
//    {
//        this.facultyID = facultyID;
//        this.name = name;
//        this.degree = degree;
//        this.interest = interest;
//        this.email = email;
//        this.officeLocation = officeLocation;
//        this.coursesOffered = coursesOffered;
//        this.password = password;
//    }
//
//
//    public String getFacultyID() {
//        return facultyID;
//    }
//
//    public void setFacultyID(String facultyID) {
//        this.facultyID = facultyID;
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
//    public String getDegree() {
//        return degree;
//    }
//
//    public void setDegree(String degree) {
//        this.degree = degree;
//    }
//
//    public String getInterest() {
//        return interest;
//    }
//
//    public void setInterest(String interest) {
//        this.interest = interest;
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
//    public String getOfficeLocation() {
//        return officeLocation;
//    }
//
//    public void setOfficeLocation(String officeLocation) {
//        this.officeLocation = officeLocation;
//    }
//
//    public String getCoursesOffered() {
//        return coursesOffered;
//    }
//
//    public void setCoursesOffered(String coursesOffered) {
//        this.coursesOffered = coursesOffered;
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

public class facultyProfile {
    //private faculty attributes
    private String facultyID, name, degree, interest, email, officeLocation, coursesOffered, password;

    //UI elements
    private Label idLabel, nameLabel, degreeLabel, emailLabel, locationLabel, coursesLabel;
    private TextField tfInterest, tfPassword;
    private VBox layout;
    private int rowIndex;

    //constructor that initializes faculty profile for a given row in the UMS Data sheet
    public facultyProfile(int rowIndex) {
        this.rowIndex = rowIndex;
        layout = new VBox(15);
        loadProfile(); //loads details from UMS Data
    }

    //loads faculty details from the UMS Data file and initializes the UI components
    private void loadProfile() {
        try {
            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("Faculties ");
            Row row = sheet.getRow(rowIndex);

            //get faculty details from the Excel row
            facultyID = getCell(row, 0);
            name = getCell(row, 1);
            degree = getCell(row, 2);
            interest = getCell(row, 3);
            email = getCell(row, 4);
            officeLocation = getCell(row, 5);
            coursesOffered = getCell(row, 6);
            password = getCell(row, 7);

            //initializes laels with faculty information
            idLabel = new Label("Faculty ID: " + facultyID);
            nameLabel = new Label("Name: " + name);
            degreeLabel = new Label("Degree: " + degree);
            tfInterest = new TextField(interest);
            emailLabel = new Label("Email: " + email);
            locationLabel = new Label("Office Location: " + officeLocation);
            coursesLabel = new Label("Courses Offered: " + coursesOffered);
            tfPassword = new TextField(password);

            //save button to update faculty profile changes
            Button saveBtn = new Button("Save Changes");
            saveBtn.setOnAction(e -> saveProfile());

            //add components to layout
            layout.getChildren().addAll(
                    idLabel, nameLabel, degreeLabel,
                    new Label("Research Interest:"), tfInterest,
                    emailLabel, locationLabel, coursesLabel,
                    new Label("Password:"), tfPassword,
                    saveBtn
            );

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace(); //prints error message should something go wrong
        }
    }

    //saves the updated faculty details in the UMS Data sheet
    private void saveProfile() {
        try {
            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("Faculties ");
            Row row = sheet.getRow(rowIndex);

            //update the research interest and password fields
            row.getCell(3).setCellValue(tfInterest.getText());
            row.getCell(7).setCellValue(tfPassword.getText());

            FileOutputStream out = new FileOutputStream("src/main/resources/UMS_Data.xlsx");
            workbook.write(out); //save changes to the file
            out.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //gets the value of a specific cell from a row in the UMS Data sheet
    private String getCell(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return ""; //returns an empty string if the cell is null
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue(); //returns string value
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue()); //convert numeric value to string value
            default -> "";
        };
    }

    //returns layout containing the faculty profile UI
    public VBox getLayout() {
        return layout;
    }
}
