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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class eventManagement extends managementParent {
    private static final String EXCEL_PATH = "src/main/resources/UMS_Data.xlsx";
    private static final String SHEET_NAME = "Events ";

    private ArrayList<event> events;
    private ObservableList<String> eventStrings;
    private FilteredList<String> filteredEvents;

    private HBox list, desc, adminControls, filtering;
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    private TextField searchField;
    private Button addButton, deleteButton, editButton, filterButton, clearButton, registerButton;

    private int role = 0;
    private UserCheck userCheck;

    public eventManagement() {
        events = new ArrayList<>();
        loadEventsFromExcel();

        eventStrings = FXCollections.observableArrayList();
        updateList();

        filteredEvents = new FilteredList<>(eventStrings, s -> true);
        listView = new ListView<>(filteredEvents);
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal.intValue()));

        eventListUISetup();
    }

    public void setRole(int role, UserCheck userCheck) {
        this.role = role;
        this.userCheck = userCheck;
        updateLayoutForRole();
    }

    private void eventListUISetup() {
        tf1 = new TextField(); tf2 = new TextField(); tf3 = new TextField(); tf4 = new TextField();
        tf5 = new TextField(); tf6 = new TextField(); tf7 = new TextField(); tf8 = new TextField(); tf9 = new TextField();

        tf1.setPromptText("Event Name");
        tf2.setPromptText("Event Code");
        tf3.setPromptText("Description");
        tf4.setPromptText("Location");
        tf5.setPromptText("DateTime");
        tf6.setPromptText("Capacity");
        tf7.setPromptText("Cost");
        tf8.setPromptText("Header Image");
        tf9.setPromptText("Registered Students");

        searchField = new TextField();
        searchField.setPromptText("Search Events...");

        addButton = new Button("Add Event");
        deleteButton = new Button("Delete Event");
        editButton = new Button("Edit Event");
        filterButton = new Button("Filter");
        clearButton = new Button("Clear");
        registerButton = new Button("Register for Event");

        addButton.setOnAction(e -> addEvent());
        deleteButton.setOnAction(e -> deleteEvent());
        editButton.setOnAction(e -> editEvent());
        filterButton.setOnAction(e -> applyFilter());
        clearButton.setOnAction(e -> clearFilter());
        registerButton.setOnAction(e -> registerForEvent());

        filtering = new HBox(10, searchField, filterButton, clearButton, addButton, deleteButton, editButton);
        adminControls = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9);
        list = new HBox(listView);

        layoutContainer = new VBox(15);
        layoutContainer.getChildren().addAll(adminControls, filtering, list, registerButton);
    }

    private void updateLayoutForRole() {
        boolean isAdmin = (role == 1);
        boolean isStudent = (role == 3);

        adminControls.setVisible(isAdmin);
        adminControls.setManaged(isAdmin);
        addButton.setVisible(isAdmin);
        addButton.setManaged(isAdmin);
        deleteButton.setVisible(isAdmin);
        deleteButton.setManaged(isAdmin);
        editButton.setVisible(isAdmin);
        editButton.setManaged(isAdmin);

        registerButton.setVisible(isStudent);
        registerButton.setManaged(isStudent);
    }

    private void applyFilter() {
        String filterText = searchField.getText().toLowerCase();
        filteredEvents.setPredicate(event -> filterText.isEmpty() || event.toLowerCase().contains(filterText));
    }

    private void clearFilter() {
        searchField.clear();
        filteredEvents.setPredicate(s -> true);
    }

    @Override
    protected void updateList() {
        eventStrings.clear();
        for (event e : events) {
            eventStrings.add(e.getName() + ", " + e.getCode() + ", " + e.getDescription() + ", " +
                    e.getLocation() + ", " + e.getDatetime() + ", " + e.getCapacity() + ", " +
                    e.getCost() + ", " + e.getImage() + ", " + e.getRegistered());
        }
    }

    private void populateFields(int index) {
        if (index >= 0 && index < events.size()) {
            event e = events.get(index);
            tf1.setText(e.getName());
            tf2.setText(e.getCode());
            tf3.setText(e.getDescription());
            tf4.setText(e.getLocation());
            tf5.setText(e.getDatetime());
            tf6.setText(e.getCapacity());
            tf7.setText(e.getCost());
            tf8.setText(e.getImage());
            tf9.setText(e.getRegistered());
        }
    }

    private void clearFields() {
        tf1.clear(); tf2.clear(); tf3.clear(); tf4.clear(); tf5.clear();
        tf6.clear(); tf7.clear(); tf8.clear(); tf9.clear();
    }

    private void addEvent() {
        event newEvent = new event(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText());

        if (!events.contains(newEvent)) {
            events.add(newEvent);
            updateList();
            saveEventsToExcel();
            clearFields();
        }
    }

    private void deleteEvent() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            events.remove(selectedIndex);
            updateList();
            saveEventsToExcel();
            clearFields();
        }
    }

    private void editEvent() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            events.set(index, new event(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(),
                    tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText()));
            updateList();
            saveEventsToExcel();
            clearFields();
        }
    }

    private void registerForEvent() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) return;

        String studentID = userCheck.getLoggedInStudentID();
        if (studentID == null || studentID.isEmpty()) return;

        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) return;

            int eventRowIndex = selectedIndex + 1;
            Row row = sheet.getRow(eventRowIndex);
            if (row == null) return;

            Cell registeredCell = row.getCell(8);
            if (registeredCell == null) registeredCell = row.createCell(8);

            String currentValue = registeredCell.getStringCellValue();

            if (currentValue.contains(studentID)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Already Registered");
                alert.setHeaderText(null);
                alert.setContentText("You have already registered for this event.");
                alert.showAndWait();
                return;
            }

            String updated = currentValue.isEmpty() ? studentID : currentValue + "," + studentID;
            registeredCell.setCellValue(updated);

            try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
                workbook.write(fos);
            }

            workbook.close();
            updateList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveEventsToExcel() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) sheet = workbook.createSheet(SHEET_NAME);

            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i > 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }

            int rowNum = 1;
            for (event e : events) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getName());
                row.createCell(1).setCellValue(e.getCode());
                row.createCell(2).setCellValue(e.getDescription());
                row.createCell(3).setCellValue(e.getLocation());
                row.createCell(4).setCellValue(e.getDatetime());
                row.createCell(5).setCellValue(e.getCapacity());
                row.createCell(6).setCellValue(e.getCost());
                row.createCell(7).setCellValue(e.getImage());
                row.createCell(8).setCellValue(e.getRegistered());
            }

            try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
                workbook.write(fos);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadEventsFromExcel() {
        try (InputStream is = new FileInputStream(EXCEL_PATH)) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                System.out.println("Sheet '" + SHEET_NAME + "' not found.");
                return;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String name = getCellAsString(row.getCell(0));
                String code = getCellAsString(row.getCell(1));
                String description = getCellAsString(row.getCell(2));
                String location = getCellAsString(row.getCell(3));
                String datetime = getCellAsString(row.getCell(4));
                String capacity = getCellAsString(row.getCell(5));
                String cost = getCellAsString(row.getCell(6));
                String image = getCellAsString(row.getCell(7));
                String registered = getCellAsString(row.getCell(8));

                if(!Objects.equals(name, "")) {
                    events.add(new event(name, code, description, location, datetime, capacity, cost, image, registered));
                }
            }

            workbook.close();
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
            case FORMULA -> cell.getCellFormula();
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
