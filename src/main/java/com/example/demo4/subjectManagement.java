


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
//public class subjectManagement extends managementParent {
//    //declaring list of subjects
//    private ArrayList<subject> subjects;
//    private ObservableList<String> subjectStrings;
//    private FilteredList<String> filteredSubjects;
//
//    //declaring UI components
//    private HBox list, desc, adminControls, filtering;
//    private TextField tf1, tf2;
//    private TextField searchField;
//    private Button addButton, deleteButton, filterButton, clearButton;
//
//    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
//    private static final String SHEET_NAME = "Subjects";
//
//
//    //constructor that initializes the subject management UI and loads subjects
//    public subjectManagement() {
//        subjects = new ArrayList<>();
//        loadSubjectsFromExcel();
//
//        subjectStrings = FXCollections.observableArrayList();
//        updateList();
//
//        filteredSubjects = new FilteredList<>(subjectStrings, s -> true);
//        listView = new ListView<>(filteredSubjects);
//
//        subjectListUISetup();
//    }
//
//    //sets up the UI for subejct management
//    private void subjectListUISetup() {
//        Label code = new Label("Subject Code");
//        Label name = new Label("Subject Name");
//
//        desc = new HBox(20, code, name);
//
//        tf1 = new TextField();
//        tf2 = new TextField();
//
//        //search field for filter
//        searchField = new TextField();
//        searchField.setPromptText("Search Subjects...");
//
//        //declaring the buttons from earlier
//        addButton = new Button("Add Subject");
//        deleteButton = new Button("Delete Subject");
//        filterButton = new Button("Filter");
//        clearButton = new Button("Clear");
//
//        //setting the action of each button (which methods they call)
//        addButton.setOnAction(e -> addSubject());
//        deleteButton.setOnAction(e -> deleteSubject());
//        filterButton.setOnAction(e -> applyFilter());
//        clearButton.setOnAction(e -> clearFilter());
//
//        filtering = new HBox(15, searchField, filterButton, clearButton, addButton, deleteButton);
//        adminControls = new HBox(10, tf1, tf2);
//        list = new HBox(listView);
//
//        layoutContainer = new VBox(20, desc, filtering, adminControls, list);
//    }
//
//    //applies a filter to the subject list based on user input
//    private void applyFilter() {
//        String filterText = searchField.getText().toLowerCase();
//        filteredSubjects.setPredicate(subject -> filterText.isEmpty() || subject.toLowerCase().contains(filterText));
//    }
//
//    //clears the applied filter and resets the subject list
//    private void clearFilter() {
//        searchField.clear();
//        filteredSubjects.setPredicate(s -> true);
//    }
//
//    @Override
//    protected void updateList() {
//        subjectStrings.clear();
//        for (subject s : subjects) {
//            subjectStrings.add(s.getCode() + ", " + s.getName());
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
//    //method for adding subjects to the list
//    private void addSubject() {
//    //adds a new subject to the list only if text fields are filled and it doesnt already exist
//        if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty()) {
//            subject newSubject = new subject(tf1.getText(), tf2.getText());
//
//            if (!subjects.contains(newSubject)) {
//                subjects.add(newSubject);
//                updateList(); //updates the list with new subject
//                saveSubjectsToExcel();
//
//            } else {
//                System.out.println("Subject already exists!");
//            }
//        }
//    }
//
//    //method for deleting subject from list
//    private void deleteSubject() {
//        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            subjects.remove(selectedIndex);
//            updateList(); //updates the display of the list once subject is removed
//            saveSubjectsToExcel();
//
//        }
//    }
//
//    //method that loads subjects from an excel file and adds to the list
//    private void loadSubjectsFromExcel() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            if (is == null) {
//                System.err.println("Excel file not found in resources!");
//                return;
//            }
//
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0); // First sheet: Subjects
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; //skips the header row in UMS Data sheet
//
//                String code = getCellAsString(row.getCell(0));
//                String name = getCellAsString(row.getCell(1));
//
//                if (!code.isEmpty() && !name.isEmpty()) {
//                    subjects.add(new subject(code, name));
//                }
//            }
//
//            workbook.close(); //closes the workbook
//        } catch (Exception e) {
//            e.printStackTrace(); //prints error message should something go wrong
//        }
//    }
//
//    //method that gives the value of an excel cell as a string
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
//    private void saveSubjectsToExcel() {
//        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheet(SHEET_NAME);
//            if (sheet == null) {
//                sheet = workbook.createSheet(SHEET_NAME);
//            }
//
//            // Clear previous data (skip header row)
//            int lastRow = sheet.getLastRowNum();
//            for (int i = lastRow; i > 0; i--) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    sheet.removeRow(row);
//                }
//            }
//
//            // Write all current subjects
//            int rowNum = 1;
//            for (subject s : subjects) {
//                Row row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(s.getCode());
//                row.createCell(1).setCellValue(s.getName());
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

public class subjectManagement extends managementParent {
    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
    private static final String SHEET_NAME = "Subjects";

    private ArrayList<subject> subjects;
    private ObservableList<String> subjectStrings;
    private FilteredList<String> filteredSubjects;

    private TextField tf1, tf2, searchField;
    private Button addButton, deleteButton, editButton, filterButton, clearButton;
    private HBox list, inputFields, filtering;

    private int role = 0;

    public subjectManagement() {
        subjects = new ArrayList<>();
        loadSubjectsFromExcel();

        subjectStrings = FXCollections.observableArrayList();
        updateList();

        filteredSubjects = new FilteredList<>(subjectStrings, s -> true);
        listView = new ListView<>(filteredSubjects);
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal.intValue()));

        setupUI();
    }

    public void setRole(int role) {
        this.role = role;
        updateLayoutForRole();
    }

    private void setupUI() {
        tf1 = new TextField();
        tf2 = new TextField();

        tf1.setPromptText("Subject Name");
        tf2.setPromptText("Subject Code");

        searchField = new TextField();
        searchField.setPromptText("Search Subjects...");

        addButton = new Button("Add Subject");
        deleteButton = new Button("Delete Subject");
        editButton = new Button("Edit Subject");
        filterButton = new Button("Filter");
        clearButton = new Button("Clear");

        addButton.setOnAction(e -> addSubject());
        deleteButton.setOnAction(e -> deleteSubject());
        editButton.setOnAction(e -> editSubject());
        filterButton.setOnAction(e -> applyFilter());
        clearButton.setOnAction(e -> clearFilter());

        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton, editButton);
        inputFields = new HBox(10, tf1, tf2);
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
        if (index >= 0 && index < subjects.size()) {
            subject s = subjects.get(index);
            tf1.setText(s.getName());
            tf2.setText(s.getCode());
        }
    }

    private void clearFields() {
        tf1.clear();
        tf2.clear();
    }

    private void addSubject() {
        subject s = new subject(tf1.getText(), tf2.getText());
        if (!subjects.contains(s)) {
            subjects.add(s);
            updateList();
            saveSubjectsToExcel();
            clearFields();
        }
    }

    private void deleteSubject() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            subjects.remove(index);
            updateList();
            saveSubjectsToExcel();
            clearFields();
        }
    }

    private void editSubject() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            subject s = subjects.get(index);
            s.setName(tf1.getText());
            s.setCode(tf2.getText());
            updateList();
            saveSubjectsToExcel();
            clearFields();
        }
    }

    private void applyFilter() {
        String text = searchField.getText().toLowerCase();
        filteredSubjects.setPredicate(s -> s.toLowerCase().contains(text));
    }

    private void clearFilter() {
        searchField.clear();
        filteredSubjects.setPredicate(s -> true);
    }

    private void loadSubjectsFromExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) return;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String name = getCellAsString(row.getCell(0));
                String code = getCellAsString(row.getCell(1));

                subjects.add(new subject(name, code));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveSubjectsToExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) sheet = workbook.createSheet(SHEET_NAME);

            // Clear existing rows (except header)
            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i >= 1; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }

            int rowNum = 1;
            for (subject s : subjects) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getName());
                row.createCell(1).setCellValue(s.getCode());
            }

            try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateList() {
        subjectStrings.clear();
        for (subject s : subjects) {
            subjectStrings.add(s.getName() + ", " + s.getCode());
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
