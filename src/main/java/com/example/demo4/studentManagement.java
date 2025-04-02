
package com.example.demo4;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.InputStream;
import java.util.Objects;
//
//public class studentManagement extends managementParent {
//    //lists to store student objects
//    private ArrayList<student> students;
//    private ObservableList<String> studentStrings;
//    private FilteredList<String> filteredStudents;
//
//    //declaring UI components
//    private HBox list, desc, adminControls, filtering;
//
//    //initializing text fields for student details
//    private TextField tfId, tfName, tfAddress, tfPhone, tfEmail, tfLevel, tfSemester;
//    private TextField tfPhoto, tfSubjects, tfThesis, tfProgress, tfPassword;
//    private TextField searchField;
//
//    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
//    private static final String SHEET_NAME = "Students ";
//
//
//    //declaring buttons for future use
//    private Button addButton, deleteButton, filterButton, clearButton;
//
//    //constructor that initializes the student management system, loads students from UMS Data sheet,
//    //and sets up the UI components
//    public studentManagement() {
//        students = new ArrayList<>();
//        loadStudentsFromExcel(); //load data from UMS Data sheet
//
//        studentStrings = FXCollections.observableArrayList();
//        updateList(); //update the student list with UMS data
//
//        filteredStudents = new FilteredList<>(studentStrings, s -> true);
//        listView = new ListView<>(filteredStudents);
//
//        studentListUISetup(); //setup the UI components
//    }
//
//    //sets up UI components for student management
//    private void studentListUISetup() {
//        //labels for student attributes
//        Label id = new Label("Student ID");
//        Label name = new Label("Name");
//        Label address = new Label("Address");
//        Label phone = new Label("Telephone");
//        Label email = new Label("Email");
//        Label level = new Label("Academic Level");
//        Label semester = new Label("Semester");
//        Label photo = new Label("Profile Photo");
//        Label subjects = new Label("Subjects");
//        Label thesis = new Label("Thesis");
//        Label progress = new Label("Progress");
//        Label password = new Label("Password");
//
//        //puts the new labels in a HBox
//        desc = new HBox(10, id, name, address, phone, email, level, semester,
//                photo, subjects, thesis, progress, password);
//
//        //initializes text fields for user input
//        tfId = new TextField();
//        tfName = new TextField();
//        tfAddress = new TextField();
//        tfPhone = new TextField();
//        tfEmail = new TextField();
//        tfLevel = new TextField();
//        tfSemester = new TextField();
//        tfPhoto = new TextField();
//        tfSubjects = new TextField();
//        tfThesis = new TextField();
//        tfProgress = new TextField();
//        tfPassword = new TextField();
//
//        //search field for filtering students
//        searchField = new TextField();
//        searchField.setPromptText("Search Students...");
//
//        //initialize the buttons that were declared earlier
//        addButton = new Button("Add Student");
//        deleteButton = new Button("Delete Student");
//        filterButton = new Button("Filter");
//        clearButton = new Button("Clear");
//
//        //setting the action of each button (which methods they access)
//        addButton.setOnAction(e -> addStudent());
//        deleteButton.setOnAction(e -> deleteStudent());
//        filterButton.setOnAction(e -> applyFilter());
//        clearButton.setOnAction(e -> clearFilter());
//
//        //layout for filtering and admin controls
//        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton);
//        adminControls = new HBox(10, tfId, tfName, tfAddress, tfPhone, tfEmail, tfLevel, tfSemester,
//                tfPhoto, tfSubjects, tfThesis, tfProgress, tfPassword);
//
//        list = new HBox(listView);
//        layoutContainer = new VBox(20, desc, filtering, adminControls, list);
//    }
//
//    //applies a filter to the student list based on the text entered in the search field
//    private void applyFilter() {
//        String filterText = searchField.getText().toLowerCase();
//        filteredStudents.setPredicate(student -> filterText.isEmpty() || student.toLowerCase().contains(filterText));
//    }
//
//    //clears the applied filter and restores the student list
//    private void clearFilter() {
//        searchField.clear();
//        filteredStudents.setPredicate(s -> true);
//    }
//
//    @Override
//    protected void updateList() {
//        studentStrings.clear();
//        for (student s : students) {
//            studentStrings.add(
//                    s.getId() + ", " + s.getName() + ", " + s.getAddress() + ", " +
//                            s.getTelephone() + ", " + s.getEmail() + ", " + s.getAcademicLevel() + ", " +
//                            s.getCurrentSemester() + ", " + s.getProfilePhoto() + ", " +
//                            s.getSubjectsRegistered() + ", " + s.getThesisTitle() + ", " +
//                            s.getProgress() + ", " + s.getPassword()
//            );
//        }
//    }
//
//    @Override
//    protected VBox getAdminControls() {
//        VBox vb = new VBox();
//        vb.getChildren().addAll(desc, filtering, adminControls);
//        return vb;
//    }
//
//    @Override
//    protected HBox getListViewWrapper() {
//        return list;
//    }
//
//
//    private void addStudent() {
//        //adds a new student to the list only if all text fields are filled and the student does not already exist
//        if (!tfId.getText().isEmpty() && !tfName.getText().isEmpty() && !tfAddress.getText().isEmpty()
//                && !tfPhone.getText().isEmpty() && !tfEmail.getText().isEmpty()
//                && !tfLevel.getText().isEmpty() && !tfSemester.getText().isEmpty()
//                && !tfPhoto.getText().isEmpty() && !tfSubjects.getText().isEmpty()
//                && !tfThesis.getText().isEmpty() && !tfProgress.getText().isEmpty() && !tfPassword.getText().isEmpty()) {
//            //creates new student object
//            student newStudent = new student(
//                    tfId.getText(), tfName.getText(), tfAddress.getText(), tfPhone.getText(),
//                    tfEmail.getText(), tfLevel.getText(), tfSemester.getText(),
//                    tfPhoto.getText(), tfSubjects.getText(), tfThesis.getText(),
//                    tfProgress.getText(), tfPassword.getText()
//            );
//
//            //ensures the student does not already exist before adding
//            if (!students.contains(newStudent)) {
//                students.add(newStudent);
//                updateList();
//                saveStudentsToExcel();
//
//            } else {
//                System.out.println("Student already exists!");
//            }
//        }
//    }
//
//    //deletes a selected student from the list
//    private void deleteStudent() {
//        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            students.remove(selectedIndex);
//            updateList(); //displays the new list with the student removed
//            saveStudentsToExcel();
//
//        }
//    }
//
//    //method to load data from UMS Data sheet
//    private void loadStudentsFromExcel() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            if (is == null) {
//                System.err.println("Excel file not found in resources!");
//                return;
//            }
//
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet("Students ");
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; //skips the header row in the data sheet
//
//                String id = getCellAsString(row.getCell(0));
//                String name = getCellAsString(row.getCell(1));
//                String address = getCellAsString(row.getCell(2));
//                String phone = getCellAsString(row.getCell(3));
//                String email = getCellAsString(row.getCell(4));
//                String level = getCellAsString(row.getCell(5));
//                String semester = getCellAsString(row.getCell(6));
//                String photo = getCellAsString(row.getCell(7));
//                String subjects = getCellAsString(row.getCell(8));
//                String thesis = getCellAsString(row.getCell(9));
//                String progress = getCellAsString(row.getCell(10));
//                String password = getCellAsString(row.getCell(11));
//
//                if (!Objects.equals(id, "")) {
//                    students.add(new student(id, name, address, phone, email, level, semester,
//                            photo, subjects, thesis, progress, password));
//                }
//            }
//
//            workbook.close(); //closes the data sheet
//        } catch (Exception e) {
//            e.printStackTrace(); //prints an error message should something go wrong
//        }
//    }
//
//    //method that returns an excel cell as a string
//    private String getCellAsString(Cell cell) {
//        if (cell == null) return "";
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//            case FORMULA -> cell.getCellFormula();
//            default -> "";
//        };
//    }
//    private void saveStudentsToExcel() {
//        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheet(SHEET_NAME);
//            if (sheet == null) {
//                sheet = workbook.createSheet(SHEET_NAME);
//            }
//
//            // Clear old data (except header)
//            int lastRow = sheet.getLastRowNum();
//            for (int i = lastRow; i > 0; i--) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    sheet.removeRow(row);
//                }
//            }
//
//            // Write updated student list
//            int rowNum = 1;
//            for (student s : students) {
//                Row row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(s.getId());
//                row.createCell(1).setCellValue(s.getName());
//                row.createCell(2).setCellValue(s.getAddress());
//                row.createCell(3).setCellValue(s.getTelephone());
//                row.createCell(4).setCellValue(s.getEmail());
//                row.createCell(5).setCellValue(s.getAcademicLevel());
//                row.createCell(6).setCellValue(s.getCurrentSemester());
//                row.createCell(7).setCellValue(s.getProfilePhoto());
//                row.createCell(8).setCellValue(s.getSubjectsRegistered());
//                row.createCell(9).setCellValue(s.getThesisTitle());
//                row.createCell(10).setCellValue(s.getProgress());
//                row.createCell(11).setCellValue(s.getPassword());
//            }
//
//            fis.close(); // before writing output
//            try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
//                workbook.write(fos);
//            }
//
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class studentManagement extends managementParent {
    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
    private static final String SHEET_NAME = "Students ";

    private ArrayList<student> students;
    private ObservableList<String> studentStrings;
    private FilteredList<String> filteredStudents;

    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, searchField;
    private Button addButton, deleteButton, editButton, filterButton, clearButton;
    private HBox list, inputFields, filtering;

    private int role = 0;

    public studentManagement() {
        students = new ArrayList<>();
        loadStudentsFromExcel();

        studentStrings = FXCollections.observableArrayList();
        updateList();

        filteredStudents = new FilteredList<>(studentStrings, s -> true);
        listView = new ListView<>(filteredStudents);
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal.intValue()));

        setupUI();
    }

    public void setRole(int role) {
        this.role = role;
        updateLayoutForRole();
    }

    private void setupUI() {
        tf1 = new TextField(); tf2 = new TextField(); tf3 = new TextField(); tf4 = new TextField();
        tf5 = new TextField(); tf6 = new TextField(); tf7 = new TextField(); tf8 = new TextField();
        tf9 = new TextField(); tf10 = new TextField(); tf11 = new TextField(); tf12 = new TextField();

        tf1.setPromptText("Student ID");
        tf2.setPromptText("Name");
        tf3.setPromptText("Address");
        tf4.setPromptText("Telephone");
        tf5.setPromptText("Email");
        tf6.setPromptText("Academic Level");
        tf7.setPromptText("Current Semester");
        tf8.setPromptText("Profile Photo");
        tf9.setPromptText("Subjects Registered");
        tf10.setPromptText("Thesis Title");
        tf11.setPromptText("Progress");
        tf12.setPromptText("Password");

        searchField = new TextField();
        searchField.setPromptText("Search Students...");

        addButton = new Button("Add Student");
        deleteButton = new Button("Delete Student");
        editButton = new Button("Edit Student");
        filterButton = new Button("Filter");
        clearButton = new Button("Clear");

        addButton.setOnAction(e -> addStudent());
        deleteButton.setOnAction(e -> deleteStudent());
        editButton.setOnAction(e -> editStudent());
        filterButton.setOnAction(e -> applyFilter());
        clearButton.setOnAction(e -> clearFilter());

        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton, editButton);
        inputFields = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12);
        list = new HBox(listView);

        layoutContainer = new VBox(15);
        layoutContainer.getChildren().addAll(inputFields, filtering, list);
    }

    private void updateLayoutForRole() {
        boolean isAdmin = (role == 1);
        inputFields.setVisible(isAdmin);
        inputFields.setManaged(isAdmin);
        addButton.setVisible(isAdmin);
        addButton.setManaged(isAdmin);
        deleteButton.setVisible(isAdmin);
        deleteButton.setManaged(isAdmin);
        editButton.setVisible(isAdmin);
        editButton.setManaged(isAdmin);
    }

    private void populateFields(int index) {
        if (index >= 0 && index < students.size()) {
            student s = students.get(index);
            tf1.setText(s.getId());
            tf2.setText(s.getName());
            tf3.setText(s.getAddress());
            tf4.setText(s.getTelephone());
            tf5.setText(s.getEmail());
            tf6.setText(s.getAcademicLevel());
            tf7.setText(s.getCurrentSemester());
            tf8.setText(s.getProfilePhoto());
            tf9.setText(s.getSubjectsRegistered());
            tf10.setText(s.getThesisTitle());
            tf11.setText(s.getProgress());
            tf12.setText(s.getPassword());
        }
    }

    private void clearFields() {
        tf1.clear(); tf2.clear(); tf3.clear(); tf4.clear(); tf5.clear(); tf6.clear();
        tf7.clear(); tf8.clear(); tf9.clear(); tf10.clear(); tf11.clear(); tf12.clear();
    }

    private void addStudent() {
        student s = new student(
                tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText(),
                tf10.getText(), tf11.getText(), tf12.getText()
        );

        if (!students.contains(s)) {
            students.add(s);
            updateList();
            saveStudentsToExcel();
            clearFields();
        }
    }

    private void deleteStudent() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            students.remove(index);
            updateList();
            saveStudentsToExcel();
            clearFields();
        }
    }

    private void editStudent() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            student s = students.get(index);
            students.set(index, new student(
                    tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                    tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText(),
                    tf10.getText(), tf11.getText(), tf12.getText()
            ));
            updateList();
            saveStudentsToExcel();
            clearFields();
        }
    }

    private void applyFilter() {
        String text = searchField.getText().toLowerCase();
        filteredStudents.setPredicate(s -> s.toLowerCase().contains(text));
    }

    private void clearFilter() {
        searchField.clear();
        filteredStudents.setPredicate(s -> true);
    }

    @Override
    protected void updateList() {
        studentStrings.clear();
        for (student s : students) {
            studentStrings.add(s.getId() + ", " + s.getName() + ", " + s.getEmail() + ", " + s.getAcademicLevel() + ", " + s.getProgress());
        }
    }

    private void loadStudentsFromExcel() {
        System.out.println("Attempting to load students from Excel..."); // ✅ Debug

        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                System.out.println("Sheet '" + SHEET_NAME + "' not found.");
                return;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String id = getCellAsString(row.getCell(0));
                String name = getCellAsString(row.getCell(1));
                String address = getCellAsString(row.getCell(2));
                String telephone = getCellAsString(row.getCell(3));
                String email = getCellAsString(row.getCell(4));
                String academicLevel = getCellAsString(row.getCell(5));
                String currentSemester = getCellAsString(row.getCell(6));
                String profilePhoto = getCellAsString(row.getCell(7));
                String subjectsRegistered = getCellAsString(row.getCell(8));
                String thesisTitle = getCellAsString(row.getCell(9));
                String progress = getCellAsString(row.getCell(10));
                String password = getCellAsString(row.getCell(11));

                students.add(new student(id, name, address, telephone, email, academicLevel, currentSemester,
                        profilePhoto, subjectsRegistered, thesisTitle, progress, password));
                System.out.println("Loaded students: " + students.size());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveStudentsToExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) sheet = workbook.createSheet(SHEET_NAME);

            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i >= 1; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }

            int rowNum = 1;
            for (student s : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getId());
                row.createCell(1).setCellValue(s.getName());
                row.createCell(2).setCellValue(s.getAddress());
                row.createCell(3).setCellValue(s.getTelephone());
                row.createCell(4).setCellValue(s.getEmail());
                row.createCell(5).setCellValue(s.getAcademicLevel());
                row.createCell(6).setCellValue(s.getCurrentSemester());
                row.createCell(7).setCellValue(s.getProfilePhoto());
                row.createCell(8).setCellValue(s.getSubjectsRegistered());
                row.createCell(9).setCellValue(s.getThesisTitle());
                row.createCell(10).setCellValue(s.getProgress());
                row.createCell(11).setCellValue(s.getPassword());
            }

            try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCellAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    @Override
    protected VBox getAdminControls() {
        return layoutContainer;
    }

    @Override
    protected HBox getListViewWrapper() {
        return list;
    }
}

