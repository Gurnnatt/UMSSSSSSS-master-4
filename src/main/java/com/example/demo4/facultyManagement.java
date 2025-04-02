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
//
//public class facultyManagement extends managementParent {
//    private ArrayList<faculty> faculties; //list to store faculty objects
//    private ObservableList<String> facultyStrings; //list for dynamic UI updates
//    private FilteredList<String> filteredFaculties; //list for dynamic UI updates
//
//    //UI elements
//    private HBox list, desc, adminControls, filtering;
//    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
//    private TextField searchField;
//    private Button addButton, deleteButton, filterButton, clearButton;
//
//    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
//    private static final String SHEET_NAME = "Faculties ";
//
//
//    //constructor that initializes the faculty list and UI
//    public facultyManagement() {
//        faculties = new ArrayList<>(); //creates empty list for faculty members
//        loadFacultiesFromExcel(); //loads faculty data from UMS Data excel sheet
//
//        facultyStrings = FXCollections.observableArrayList();
//        updateList(); //update UI list with existing data
//
//        filteredFaculties = new FilteredList<>(facultyStrings, s -> true); //default the list to show all
//        listView = new ListView<>(filteredFaculties);
//
//        facultyListUISetup(); //sets up the UI components
//    }
//
//    //configures the UI layout for faculty management
//    private void facultyListUISetup() {
//        //labels for the faculty attributes
//        Label id = new Label("ID");
//        Label name = new Label("Name");
//        Label degree = new Label("Degree");
//        Label research = new Label("Research Interest");
//        Label email = new Label("Email");
//        Label location = new Label("Office Location");
//        Label courses = new Label("Courses Offered");
//        Label password = new Label("Password");
//
//        //organizes labels into an HBox
//        desc = new HBox(15, id, name, degree, research, email, location, courses, password);
//
//        //user input fields for faculty details
//        tf1 = new TextField(); // ID
//        tf2 = new TextField(); // Name
//        tf3 = new TextField(); // Degree
//        tf4 = new TextField(); // Research Interest
//        tf5 = new TextField(); // Email
//        tf6 = new TextField(); // Office Location
//        tf7 = new TextField(); // Courses Offered
//        tf8 = new TextField(); // Password
//
//        //search functionality
//        searchField = new TextField();
//        searchField.setPromptText("Search Faculty...");
//
//        //buttons for adding, deleting, filtering, and clearing faculty operations
//        addButton = new Button("Add Faculty");
//        deleteButton = new Button("Delete Faculty");
//        filterButton = new Button("Filter");
//        clearButton = new Button("Clear");
//
//        //setting the action for each button (which method it calls)
//        addButton.setOnAction(e -> addFaculty());
//        deleteButton.setOnAction(e -> deleteFaculty());
//        filterButton.setOnAction(e -> applyFilter());
//        clearButton.setOnAction(e -> clearFilter());
//
//        //layout arrangement
//        filtering = new HBox(15, searchField, filterButton, clearButton, addButton, deleteButton);
//        adminControls = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8);
//        list = new HBox(listView);
//
//        layoutContainer = new VBox(20, desc, filtering, adminControls, list);
//    }
//
//    //filters faculty based on search input
//    private void applyFilter() {
//        String filterText = searchField.getText().toLowerCase();
//        filteredFaculties.setPredicate(faculty -> filterText.isEmpty() || faculty.toLowerCase().contains(filterText));
//    }
//
//    //clears the search field and resets the list
//    private void clearFilter() {
//        searchField.clear();
//        filteredFaculties.setPredicate(s -> true);
//    }
//
//    //updates the displayed faculty list
//    @Override
//    protected void updateList() {
//        facultyStrings.clear();
//        for (faculty f : faculties) {
//            facultyStrings.add(
//                    f.getId() + ", " + f.getName() + ", " + f.getDegree() + ", " +
//                            f.getDepartment() + ", " + f.getEmail() + ", " +
//                            f.getLocation() + ", " + f.getOffered() + ", " +
//                            f.getPassword()
//            );
//        }
//    }
//
//    //returns the admin control panel
//    @Override
//    protected VBox getAdminControls() {
//        VBox vb = new VBox();
//        vb.getChildren().addAll(desc, filtering, adminControls);
//        return vb;
//    }
//
//    //returns the faculty list view wrapper
//    @Override
//    protected HBox getListViewWrapper() {
//        return list;
//    }
//
//    //adds a new faculty member to the system
//    private void addFaculty() {
//        //creates new faculty object only if all text fields are filled in
//        if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()
//                && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() &&
//                !tf6.getText().isEmpty() && !tf7.getText().isEmpty() && !tf8.getText().isEmpty()) {
//
//            faculty newFaculty = new faculty(
//                    tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(),
//                    tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText()
//            );
//
//            if (!faculties.contains(newFaculty)) {
//                faculties.add(newFaculty);
//                updateList(); //updates the list once faculty member is added
//                saveFacultiesToExcel();
//
//            } else {
//                System.out.println("Faculty already exists!");
//            }
//        }
//    }
//
//    //deletes a selected faculty member from the list
//    private void deleteFaculty() {
//        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            faculties.remove(selectedIndex);
//            updateList(); //updates the list once faculty member is removed
//            saveFacultiesToExcel();
//
//        }
//    }
//
//    //loads faculty data from the UMS Data excel file
//    private void loadFacultiesFromExcel() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            if (is == null) {
//                System.err.println("Excel file not found in resources!");
//                return;
//            }
//
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet("Faculties ");
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; //skips header row of data sheet
//
//                //reads faculty details from each row of the data sheet
//                String id = getCellAsString(row.getCell(0));
//                String name = getCellAsString(row.getCell(1));
//                String degree = getCellAsString(row.getCell(2));
//                String department = getCellAsString(row.getCell(3));
//                String email = getCellAsString(row.getCell(4));
//                String location = getCellAsString(row.getCell(5));
//                String courses = getCellAsString(row.getCell(6));
//                String password = getCellAsString(row.getCell(7));
//
//                if (!Objects.equals(id, "")) {
//                    faculties.add(new faculty(id, name, degree, department, email, location, courses, password));
//                }
//            }
//
//            workbook.close(); //closes UMS Data excel file
//        } catch (Exception e) {
//            e.printStackTrace(); //prints error message if something goes wrong
//        }
//    }
//
//    //converts an excel cell from UMS Data to a string value
//    private String getCellAsString(Cell cell) {
//        if (cell == null) return "";
//
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//            case FORMULA -> cell.getCellFormula();
//            default -> "";
//        };
//    }
//    private void saveFacultiesToExcel() {
//        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheet(SHEET_NAME);
//            if (sheet == null) {
//                sheet = workbook.createSheet(SHEET_NAME);
//            }
//
//            // Clear existing data except header
//            int lastRow = sheet.getLastRowNum();
//            for (int i = lastRow; i > 0; i--) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    sheet.removeRow(row);
//                }
//            }
//
//            // Write updated faculty data
//            int rowNum = 1;
//            for (faculty f : faculties) {
//                Row row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(f.getId());
//                row.createCell(1).setCellValue(f.getName());
//                row.createCell(2).setCellValue(f.getDegree());
//                row.createCell(3).setCellValue(f.getDepartment());
//                row.createCell(4).setCellValue(f.getEmail());
//                row.createCell(5).setCellValue(f.getLocation());
//                row.createCell(6).setCellValue(f.getOffered());
//                row.createCell(7).setCellValue(f.getPassword());
//            }
//
//            fis.close(); // Close input before writing
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

public class facultyManagement extends managementParent {
    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
    private static final String SHEET_NAME = "Faculties ";

    private ArrayList<faculty> faculties;
    private ObservableList<String> facultyStrings;
    private FilteredList<String> filteredFaculties;

    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, searchField;
    private Button addButton, deleteButton, editButton, filterButton, clearButton;
    private HBox list, inputFields, filtering;

    private int role = 0;

    public facultyManagement() {
        faculties = new ArrayList<>();
        loadFacultiesFromExcel();

        facultyStrings = FXCollections.observableArrayList();
        updateList();

        filteredFaculties = new FilteredList<>(facultyStrings, s -> true);
        listView = new ListView<>(filteredFaculties);
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

        tf1.setPromptText("Faculty ID");
        tf2.setPromptText("Name");
        tf3.setPromptText("Degree");
        tf4.setPromptText("Department");
        tf5.setPromptText("Email");
        tf6.setPromptText("Office Location");
        tf7.setPromptText("Courses Offered");
        tf8.setPromptText("Password");

        searchField = new TextField();
        searchField.setPromptText("Search Faculties...");

        addButton = new Button("Add Faculty");
        deleteButton = new Button("Delete Faculty");
        editButton = new Button("Edit Faculty");
        filterButton = new Button("Filter");
        clearButton = new Button("Clear");

        addButton.setOnAction(e -> addFaculty());
        deleteButton.setOnAction(e -> deleteFaculty());
        editButton.setOnAction(e -> editFaculty());
        filterButton.setOnAction(e -> applyFilter());
        clearButton.setOnAction(e -> clearFilter());

        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton, editButton);
        inputFields = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8);
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
        if (index >= 0 && index < faculties.size()) {
            faculty f = faculties.get(index);
            tf1.setText(f.getId());
            tf2.setText(f.getName());
            tf3.setText(f.getDegree());
            tf4.setText(f.getDepartment());
            tf5.setText(f.getEmail());
            tf6.setText(f.getLocation());
            tf7.setText(f.getOffered());
            tf8.setText(f.getPassword());
        }
    }

    private void clearFields() {
        tf1.clear(); tf2.clear(); tf3.clear(); tf4.clear();
        tf5.clear(); tf6.clear(); tf7.clear(); tf8.clear();
    }

    private void addFaculty() {
        faculty f = new faculty(
                tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                tf6.getText(), tf7.getText(), tf8.getText()
        );

        if (!faculties.contains(f)) {
            faculties.add(f);
            updateList();
            saveFacultiesToExcel();
            clearFields();
        }
    }

    private void deleteFaculty() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            faculties.remove(index);
            updateList();
            saveFacultiesToExcel();
            clearFields();
        }
    }

    private void editFaculty() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            faculties.set(index, new faculty(
                    tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                    tf6.getText(), tf7.getText(), tf8.getText()
            ));
            updateList();
            saveFacultiesToExcel();
            clearFields();
        }
    }

    private void applyFilter() {
        String text = searchField.getText().toLowerCase();
        filteredFaculties.setPredicate(s -> s.toLowerCase().contains(text));
    }

    private void clearFilter() {
        searchField.clear();
        filteredFaculties.setPredicate(s -> true);
    }

    @Override
    protected void updateList() {
        facultyStrings.clear();
        for (faculty f : faculties) {
            facultyStrings.add(f.getId() + ", " + f.getName() + ", " + f.getDegree() + ", " + f.getDepartment() + ", " + f.getEmail());
        }
    }

    private void loadFacultiesFromExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) return;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String id = getCellAsString(row.getCell(0));
                String name = getCellAsString(row.getCell(1));
                String degree = getCellAsString(row.getCell(2));
                String department = getCellAsString(row.getCell(3));
                String email = getCellAsString(row.getCell(4));
                String location = getCellAsString(row.getCell(5));
                String offered = getCellAsString(row.getCell(6));
                String password = getCellAsString(row.getCell(7));

                if(!Objects.equals(id, "")) {
                    faculties.add(new faculty(id, name, degree, department, email, location, offered, password));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFacultiesToExcel() {
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
            for (faculty f : faculties) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(f.getId());
                row.createCell(1).setCellValue(f.getName());
                row.createCell(2).setCellValue(f.getDegree());
                row.createCell(3).setCellValue(f.getDepartment());
                row.createCell(4).setCellValue(f.getEmail());
                row.createCell(5).setCellValue(f.getLocation());
                row.createCell(6).setCellValue(f.getOffered());
                row.createCell(7).setCellValue(f.getPassword());
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
