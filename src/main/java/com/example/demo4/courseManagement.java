package com.example.demo4;

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
//
////course management class, inherits from managementParent
//public class courseManagement extends managementParent {
//    //list to store all course objects
//    private ArrayList<course> courses;
//    private FilteredList<String> filteredCourses;
//
//    //UI elements for layout
//    private HBox list, desc, adminList, filtering;
//    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, searchField;
//    private Button addButton, deleteButton, filterButton, clearButton;
//    private ObservableList<String> courseStrings;
//
//    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
//    private static final String SHEET_NAME = "Courses";
//
//
//
//    //constructor, called when object of the class is created
//    public courseManagement() {
//        //initializes and loads existing courses from the excel file into the course list
//        courses = new ArrayList<>();
//        loadCoursesFromExcel();
//
//        courseStrings = FXCollections.observableArrayList();
//        updateList(); //updates course list display
//
//        //filtered list that starts by showing all courses
//        filteredCourses = new FilteredList<>(courseStrings, s -> true);
//        listView = new ListView<>(filteredCourses); //displays filtered courses
//
//        courseListUISetup();
//    }
//
//    private void courseListUISetup() {
//        //labels for displaying key course attributes
//        Label code = new Label("Course Code");
//        Label name = new Label("Course Name");
//        Label subcode = new Label("Subject Code");
//        Label secnum = new Label("Section Number");
//        Label capacity = new Label("Capacity");
//        Label lectime = new Label("Lecture Time");
//        Label examdate = new Label("Final Exam Date/Time");
//        Label location = new Label("Location");
//        Label teacher = new Label("Course Teacher");
//
//        //adds the labels to a HBox
//        desc = new HBox(25, code, name, subcode, secnum, capacity, lectime, examdate, location, teacher);
//
//        //text fields so user can input course details
//        tf1 = new TextField(); tf2 = new TextField(); tf3 = new TextField(); tf4 = new TextField();
//        tf5 = new TextField(); tf6 = new TextField(); tf7 = new TextField(); tf8 = new TextField(); tf9 = new TextField();
//
//        //search bar for filter
//        searchField = new TextField();
//        searchField.setPromptText("Search Courses...");
//
//        //buttons for adding, deleting, filtering, and clearing courses
//        addButton = new Button("Add Course");
//        deleteButton = new Button("Delete Course");
//        filterButton = new Button("Filter");
//        clearButton = new Button("Clear");
//
//        //assign actions to buttons (calls on the methods when clicked)
//        addButton.setOnAction(e -> addCourse());
//        deleteButton.setOnAction(e -> deleteCourse());
//        filterButton.setOnAction(e -> applyFilter());
//        clearButton.setOnAction(e -> clearFilter());
//
//        //arranges filtering UI elements and text fields for course input
//        filtering = new HBox(25, searchField, filterButton, clearButton, addButton, deleteButton);
//        adminList = new HBox(25, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9);
//        list = new HBox(listView); //holds the list of courses
//
////        filtering.getChildren().addAll(searchField, filterButton, clearButton);
//        layoutContainer = new VBox(20, desc);
//    }
//
//    //filters course list based on user input in the search bar
//    private void applyFilter() {
//        String filterText = searchField.getText().toLowerCase();
//        filteredCourses.setPredicate(course -> filterText.isEmpty() || course.toLowerCase().contains(filterText));
//    }
//
//    //clears the filter and shows all the courses again
//    private void clearFilter() {
//        searchField.clear();
//        filteredCourses.setPredicate(s -> true);
//    }
//
//    //update the list display when a course is added or removed
//    @Override
//    protected void updateList() {
//        courseStrings.clear(); //clears current display
//        //adds course details to list for the UI display
//        for (course c : courses) {
//            courseStrings.add(c.getCode() + ", " + c.getName() + ", " + c.getSubcode() + ", " + c.getSecnum() + ", " + c.getCapacity() + ", " + c.getLectime() + ", " + c.getExamdate() + ", " + c.getLocation() + ", " + c.getTeacher());
//        }
//    }
//
//    //returns UI layout for admin control (adding and filtering courses)
//    @Override
//    protected VBox getAdminControls() {
//        VBox vb = new VBox();
//        vb.getChildren().addAll(desc, filtering, adminList);
//        return vb;
//    }
//
//    //returns the UI layout that wraps course list and the course description layout
//    @Override
//    protected HBox getListViewWrapper() {
//        return list;
//    }
//    public HBox getDesc()
//    {
//        return desc;
//    }
//
//    //adds a new course to the list only if all fields are filled
//    private void addCourse() {
//        if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() &&
//                !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty() &&
//                !tf7.getText().isEmpty() && !tf8.getText().isEmpty() && !tf9.getText().isEmpty()) {
//
//            // Create a new course object with the inputted values
//            course newCourse = new course(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(),
//                    tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText());
//
//            // Check if the course already exists in the list
//            if (!courses.contains(newCourse)) {
//                courses.add(newCourse);
//                updateList(); //updates the list display after adding a new course
//            } else {
//                System.out.println("Course already exists!"); //message if the course is a duplicate
//            }
//        }
//    }
//
//    //deletes a selected course from the list
//    private void deleteCourse() {
//        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            courses.remove(selectedIndex);
//            updateList(); //updates the list display when a course is removed
//        }
//    }
//
//    //loads courses from the UMS Data excel file into the list
//    private void loadCoursesFromExcel() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
//            if (is == null) {
//                System.err.println("Excel file not found in resources!"); //error message if file is missing
//                return;
//            }
//
//            //reads the excel file
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet("Courses");
//            //loop through each row in the data sheet
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; //skips the header row
//
//                //reads the course details from the excel file
//                String code = getCellAsString(row.getCell(0));
//                String name = getCellAsString(row.getCell(1));
//                String subcode = getCellAsString(row.getCell(2));
//                String secnum = getCellAsString(row.getCell(3));
//                String capacity = getCellAsString(row.getCell(4));
//                String lectime = getCellAsString(row.getCell(5));
//                String examdate = getCellAsString(row.getCell(6));
//                String location = getCellAsString(row.getCell(7));
//                String teacher = getCellAsString(row.getCell(8));
//
//                //adds the course only if it has valid data from the data sheet
//                if (Objects.equals(code, "")) {
//
//                }
//                else {
//                    courses.add(new course(code, name, subcode, secnum, capacity, lectime, examdate, location, teacher));
//                }
//            }
//            workbook.close(); //closes the excel sheet
//        } catch (Exception e) {
//            e.printStackTrace(); //prints error message if something wrong happens
//        }
//    }
//
//    //convert Excel cell into a string
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
//}
//
//package com.example.demo4;

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

public class courseManagement extends managementParent {
    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
    private static final String SHEET_NAME = "Courses";

    private ArrayList<course> courses;
    private ObservableList<String> courseStrings;
    private FilteredList<String> filteredCourses;

    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    private TextField searchField;
    private Button addButton, deleteButton, editButton, filterButton, clearButton;

    private HBox list, inputFields, filtering;
    private int role = 0;

    public courseManagement() {
        courses = new ArrayList<>();
        loadCoursesFromExcel();

        courseStrings = FXCollections.observableArrayList();
        updateList();

        filteredCourses = new FilteredList<>(courseStrings, s -> true);
        listView = new ListView<>(filteredCourses);
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal.intValue()));

        setupUI();
    }

    public void setRole(int role) {
        this.role = role;
        updateLayoutForRole();
    }

    private void setupUI() {
        tf1 = new TextField(); tf2 = new TextField(); tf3 = new TextField();
        tf4 = new TextField(); tf5 = new TextField(); tf6 = new TextField();
        tf7 = new TextField(); tf8 = new TextField(); tf9 = new TextField();

        tf1.setPromptText("Course Code");
        tf2.setPromptText("Course Name");
        tf3.setPromptText("Subject Code");
        tf4.setPromptText("Section Number");
        tf5.setPromptText("Capacity");
        tf6.setPromptText("Lecture Time");
        tf7.setPromptText("Exam Date");
        tf8.setPromptText("Location");
        tf9.setPromptText("Teacher");

        searchField = new TextField();
        searchField.setPromptText("Search Courses...");

        addButton = new Button("Add Course");
        deleteButton = new Button("Delete Course");
        editButton = new Button("Edit Course");
        filterButton = new Button("Filter");
        clearButton = new Button("Clear");

        addButton.setOnAction(e -> addCourse());
        deleteButton.setOnAction(e -> deleteCourse());
        editButton.setOnAction(e -> editCourse());
        filterButton.setOnAction(e -> applyFilter());
        clearButton.setOnAction(e -> clearFilter());

        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton, editButton);
        inputFields = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9);
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
        if (index >= 0 && index < courses.size()) {
            course c = courses.get(index);
            tf1.setText(c.getCode());
            tf2.setText(c.getName());
            tf3.setText(c.getSubcode());
            tf4.setText(c.getSecnum());
            tf5.setText(c.getCapacity());
            tf6.setText(c.getLectime());
            tf7.setText(c.getExamdate());
            tf8.setText(c.getLocation());
            tf9.setText(c.getTeacher());
        }
    }

    private void addCourse() {
        course c = new course(
                tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(),
                tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText()
        );

        if (!courses.contains(c)) {
            courses.add(c);
            updateList();
            saveCoursesToExcel();
            clearFields();
        }
    }

    private void deleteCourse() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            courses.remove(index);
            updateList();
            saveCoursesToExcel();
        }
    }

    private void editCourse() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            course c = courses.get(index);
            c.setCode(tf1.getText());
            c.setName(tf2.getText());
            c.setSubcode(tf3.getText());
            c.setSecnum(tf4.getText());
            c.setCapacity(tf5.getText());
            c.setLectime(tf6.getText());
            c.setExamdate(tf7.getText());
            c.setLocation(tf8.getText());
            c.setTeacher(tf9.getText());

            updateList();
            saveCoursesToExcel();
            clearFields();
        }
    }

    private void applyFilter() {
        String text = searchField.getText().toLowerCase();
        filteredCourses.setPredicate(s -> s.toLowerCase().contains(text));
    }

    private void clearFilter() {
        searchField.clear();
        filteredCourses.setPredicate(s -> true);
    }

    @Override
    protected void updateList() {
        courseStrings.clear();
        for (course c : courses) {
            courseStrings.add(c.getCode() + ", " + c.getName() + ", " + c.getSubcode() + ", " + c.getSecnum() + ", " +
                    c.getCapacity() + ", " + c.getLectime() + ", " + c.getExamdate() + ", " + c.getLocation() + ", " + c.getTeacher());
        }
    }

    private void loadCoursesFromExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) return;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String code = getCellAsString(row.getCell(0));
                String name = getCellAsString(row.getCell(1));
                String subcode = getCellAsString(row.getCell(2));
                String secnum = getCellAsString(row.getCell(3));
                String capacity = getCellAsString(row.getCell(4));
                String lectime = getCellAsString(row.getCell(5));
                String examdate = getCellAsString(row.getCell(6));
                String location = getCellAsString(row.getCell(7));
                String teacher = getCellAsString(row.getCell(8));

                courses.add(new course(code, name, subcode, secnum, capacity, lectime, examdate, location, teacher));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCoursesToExcel() {
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
            for (course c : courses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(c.getCode());
                row.createCell(1).setCellValue(c.getName());
                row.createCell(2).setCellValue(c.getSubcode());
                row.createCell(3).setCellValue(c.getSecnum());
                row.createCell(4).setCellValue(c.getCapacity());
                row.createCell(5).setCellValue(c.getLectime());
                row.createCell(6).setCellValue(c.getExamdate());
                row.createCell(7).setCellValue(c.getLocation());
                row.createCell(8).setCellValue(c.getTeacher());
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
    private void clearFields() {
        tf1.clear(); tf2.clear(); tf3.clear(); tf4.clear(); tf5.clear();
        tf6.clear(); tf7.clear(); tf8.clear(); tf9.clear();
    }

}

