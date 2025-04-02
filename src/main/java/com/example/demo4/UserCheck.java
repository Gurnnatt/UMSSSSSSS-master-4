//package com.example.demo4;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//public class UserCheck {
//    private final Map<String, String> studentCredentials = new HashMap<>();
//    private final Map<String, Integer> studentRowIndex = new HashMap<>(); // Stores student row indices
//    private final Map<String, String> facultyCredentials = new HashMap<>();
//    private final Map<String, Integer> facultyRowIndex = new HashMap<>(); // Stores faculty row indices
//
//    private int loggedInStudentRow = -1; // Tracks logged-in student row
//    private int loggedInFacultyRow = -1; // Tracks logged-in faculty row
//
//    public UserCheck() {
//        loadCredentials();
//    }
//
//    private void loadCredentials() {
//        try (InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx")) {
//            if (is == null) {
//                System.err.println("Excel file not found!");
//                return;
//            }
//
//            Workbook workbook = new XSSFWorkbook(is);
//
//            // Read student credentials from sheet 3 (index 2)
//            Sheet studentSheet = workbook.getSheetAt(2);
//            for (Row row : studentSheet) {
//                if (row.getRowNum() == 0) continue; // Skip header
//
//                String username = getCellAsString(row.getCell(1)); // Name in 2nd column
//                String password = getCellAsString(row.getCell(11)); // Password in 12th column
//
//                if (!username.isEmpty() && !password.isEmpty()) {
//                    studentCredentials.put(username, password);
//                    studentRowIndex.put(username, row.getRowNum()); // Store row index
//                }
//            }
//
//            // Read faculty credentials from sheet 4 (index 3)
//            Sheet facultySheet = workbook.getSheetAt(3);
//            for (Row row : facultySheet) {
//                if (row.getRowNum() == 0) continue; // Skip header
//
//                String username = getCellAsString(row.getCell(1)); // Name in 2nd column
//                String password = getCellAsString(row.getCell(7)); // Password in 8th column
//
//                if (!username.isEmpty() && !password.isEmpty()) {
//                    facultyCredentials.put(username, password);
//                    facultyRowIndex.put(username, row.getRowNum()); // Store row index
//                }
//            }
//
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String getCellAsString(Cell cell) {
//        if (cell == null) return "";
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//            default -> "";
//        };
//    }
//
//    public boolean validateLogin(String username, String password, int role) {
//        if (role == 3 && studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
//            loggedInStudentRow = studentRowIndex.get(username); // Store student row index
//            return true;
//        }
//        if (role == 2 && facultyCredentials.containsKey(username) && facultyCredentials.get(username).equals(password)) {
//            loggedInFacultyRow = facultyRowIndex.get(username); // Store faculty row index
//            return true;
//        }
//        return username.equals("admin") && password.equals("admin123");
//    }
//
//    public int getLoggedInStudentRow() {
//        return loggedInStudentRow;
//    }
//
//    public int getLoggedInFacultyRow() {
//        return loggedInFacultyRow;
//    }
//}
package com.example.demo4;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class UserCheck {

    private int loggedInFacultyRow = -1;
    private int loggedInStudentRow = -1;

    public boolean validateLogin(String username, String password, int role) {
        try {
            InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx");
            if (is == null) {
                System.err.println("UMS_Data.xlsx not found in resources!");
                return false;
            }

            Workbook workbook = new XSSFWorkbook(is);

            if (role == 2) { // FACULTY
                Sheet facultySheet = workbook.getSheet("Faculties ");
                for (int i = 1; i <= facultySheet.getLastRowNum(); i++) {
                    Row row = facultySheet.getRow(i);
                    if (row == null) continue;

                    String id = getCellAsString(row.getCell(0));
                    String pass = getCellAsString(row.getCell(7));

                    if (username.equals(id) && password.equals(pass)) {
                        loggedInFacultyRow = i;
                        workbook.close();
                        return true;
                    }
                }
            }

            if (role == 3) { // STUDENT
                Sheet studentSheet = workbook.getSheet("Students ");
                for (int i = 1; i <= studentSheet.getLastRowNum(); i++) {
                    Row row = studentSheet.getRow(i);
                    if (row == null) continue;

                    String id = getCellAsString(row.getCell(0));
                    String pass = getCellAsString(row.getCell(11));

                    if (username.equals(id) && password.equals(pass)) {
                        loggedInStudentRow = i;
                        workbook.close();
                        return true;
                    }
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getLoggedInFacultyRow() {
        return loggedInFacultyRow;
    }

    public int getLoggedInStudentRow() {
        return loggedInStudentRow;
    }

    private String getCellAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    public String getLoggedInStudentID() {
        if (loggedInStudentRow != -1) {
            try (InputStream is = getClass().getResourceAsStream("/UMS_Data.xlsx")) {
                Workbook workbook = new XSSFWorkbook(is);
                Sheet sheet = workbook.getSheet("Students ");
                Row row = sheet.getRow(loggedInStudentRow);
                if (row != null) {
                    Cell idCell = row.getCell(0); // Student ID column
                    return idCell != null ? idCell.getStringCellValue() : null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
