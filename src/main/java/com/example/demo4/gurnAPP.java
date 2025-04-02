//
//package com.example.demo4;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//
//
//
//
//public class gurnAPP extends Application {final int ADMIN = 1;
//    final int FACULTY = 2;
//    final int STUDENT = 3;
//
//    int role = 0, students = 10, courses = 6, faculty = 5, events = 2;
//
//    private Scene courseScene = null, facultyScene = null, studentScene = null,
//            subjectScene = null, eventScene = null, dashboardScene = null,
//            profileScene = null;
//
//    private boolean actionFired = false;
//
//    private Dashboard dashboard = new Dashboard(students, courses, faculty, events);
//    private courseManagement CMclass = new courseManagement();
//    private subjectManagement SMclass = new subjectManagement();
//    private studentManagement StMclass = new studentManagement();
//    private eventManagement EMclass = new eventManagement();
//    private facultyManagement FMclass = new facultyManagement();
//    private profileManagement PMclass = new profileManagement();
//    private UserCheck userCheck = new UserCheck();
//
//    private VBox choice;
//    private Button back = new Button("Back"), logout;
//    private Stage stage;
//    private Scene loginScene, moduleMenuScene, sceneProfile;
//
//    @Override
//    public void start(Stage stage) {
//        this.stage = stage;
//
//        GridPane main = new GridPane();
//        BorderPane root = new BorderPane();
//        BorderPane profileRoot = new BorderPane();
//
//        Scene scene = new Scene(main, 800, 800);
//        moduleMenuScene = new Scene(root, 800, 800);
//        loginScene = scene;
//        sceneProfile = new Scene(profileRoot, 800, 800);
//
//        stage.setTitle("University Management System");
//        main.setHgap(15);
//        main.setVgap(15);
//
//        Button submit = new Button("Login");
//        Label user = new Label("Username:");
//        Label password = new Label("Password:");
//
//        choice = new VBox(10);
//        ComboBox<String> modules = new ComboBox<>();
//        modules.setPromptText("Select Module");
//        modules.setPrefWidth(300);
//
//        modules.setOnAction(e -> {
//            if (!actionFired) {
//                actionFired = true;
//                String selected = modules.getSelectionModel().getSelectedItem();
//                if (selected != null) {
//                    switch (selected) {
//                        case "Course Management" -> stage.setScene(courseManagementControl());
//                        case "Subject Management" -> stage.setScene(subjectManagementControl());
//                        case "Faculty Management" -> stage.setScene(facultyManagementControl());
//                        case "Student Management" -> stage.setScene(studentManagementControl());
//                        case "Event Management" -> stage.setScene(eventManagementControl());
//                        case "Dashboard" -> stage.setScene(dashboardManagementControl());
//                        case "My Profile" -> {
//                            int rowIndex = (role == FACULTY) ? userCheck.getLoggedInFacultyRow() : userCheck.getLoggedInStudentRow();
//                            if (rowIndex != -1) {
//                                stage.setScene(profileManagementControl(rowIndex));
//                            } else {
//                                Alert alert = new Alert(Alert.AlertType.ERROR);
//                                alert.setTitle("Profile Error");
//                                alert.setHeaderText("Login Required");
//                                alert.setContentText("Please log in again to access your profile.");
//                                alert.showAndWait();
//                            }
//                        }
//                    }
//                }
//                actionFired = false;
//            }
//        });
//
//        choice.getChildren().add(modules);
//        TextField userName = new TextField();
//        PasswordField pass = new PasswordField();
//
//        submit.setOnAction(e -> {
//            String userNameCheck = userName.getText();
//            String passCheck = pass.getText();
//
//            userCheck = new UserCheck();
//
//            if (userNameCheck.equals("admin") && passCheck.equals("admin123")) {
//                role = ADMIN;
//            } else if (userCheck.validateLogin(userNameCheck, passCheck, FACULTY)) {
//                role = FACULTY;
//            } else if (userCheck.validateLogin(userNameCheck, passCheck, STUDENT)) {
//                role = STUDENT;
//            } else {
//                main.add(new Label("Invalid Credentials."), 1, 3);
//                return;
//            }
//
//            CMclass.setRole(role);
//            SMclass.setRole(role);
//            StMclass.setRole(role);
//            FMclass.setRole(role);
//            EMclass.setRole(role);
//
//            stage.setScene(moduleMenuScene);
//            root.setCenter(choice);
//            modules.getItems().clear();
//
//            if (role == ADMIN) {
//                modules.getItems().addAll("Course Management", "Subject Management", "Faculty Management", "Student Management", "Event Management", "Dashboard");
//            } else {
//                modules.getItems().addAll("Course Management", "Event Management", "My Profile", "Dashboard");
//            }
//        });
//
//        logout = new Button("Logout");
//        logout.setOnAction(e -> {
//            role = 0;
//            modules.getItems().clear();
//            modules.getSelectionModel().clearSelection();
//            choice.getChildren().clear();
//
//            modules.setPromptText("Select Module");
//            choice.getChildren().add(modules);
//
//            stage.setScene(loginScene);
//        });
//
//        back = new Button("Back");
//        back.setOnAction(e -> {
//            stage.setScene(moduleMenuScene);
//            modules.getSelectionModel().clearSelection();
//
//            if (modules.getItems().isEmpty()) {
//                if (role == ADMIN) {
//                    modules.getItems().addAll("Course Management", "Subject Management", "Faculty Management", "Student Management", "Event Management", "Dashboard");
//                } else {
//                    modules.getItems().addAll("Course Management", "Event Management", "My Profile", "Dashboard");
//                }
//            }
//
//            if (!choice.getChildren().contains(modules)) {
//                choice.getChildren().add(modules);
//            }
//        });
//
//        main.add(user, 0, 0);
//        main.add(password, 0, 1);
//        main.add(userName, 1, 0);
//        main.add(pass, 1, 1);
//        main.add(submit, 1, 2);
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//    public Scene courseManagementControl() {
//        CMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(CMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        courseScene = new Scene(container, 800, 800);
//        return courseScene;
//    }
//
//    public Scene facultyManagementControl() {
//        FMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(FMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        facultyScene = new Scene(container, 800, 800);
//        return facultyScene;
//    }
//
//    public Scene studentManagementControl() {
//        StMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(StMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        studentScene = new Scene(container, 800, 800);
//        return studentScene;
//    }
//
//    public Scene subjectManagementControl() {
//        SMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(SMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        subjectScene = new Scene(container, 800, 800);
//        return subjectScene;
//    }
//
//    public Scene eventManagementControl() {
//        EMclass.setRole(role, userCheck); // ✅ fixed
//        VBox container = new VBox(10);
//        container.getChildren().add(EMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        eventScene = new Scene(container, 800, 800);
//        return eventScene;
//    }
//
//
//    public Scene dashboardManagementControl() {
//        VBox container = new VBox(10);
//        container.getChildren().add(dashboard.setDashboard());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        dashboardScene = new Scene(container, 800, 800);
//        return dashboardScene;
//    }
//
//    public Scene profileManagementControl(int rowIndex) {
//        profileScene = PMclass.showProfile(role, rowIndex, stage, moduleMenuScene, getBackButton());
//        return profileScene;
//    }
//
//    public Button getBackButton() {
//        return back;
//    }}

package com.example.demo4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class gurnAPP extends Application {
    final int ADMIN = 1;
    final int FACULTY = 2;
    final int STUDENT = 3;

    int role = 0, students = 10, courses = 6, faculty = 5, events = 2;

    private Scene courseScene = null, facultyScene = null, studentScene = null,
            subjectScene = null, eventScene = null, dashboardScene = null,
            profileScene = null;

    private boolean actionFired = false;

    private Dashboard dashboard = new Dashboard(students, courses, faculty, events);
    private courseManagement CMclass = new courseManagement();
    private subjectManagement SMclass = new subjectManagement();
    private studentManagement StMclass = new studentManagement();
    private eventManagement EMclass = new eventManagement();
    private facultyManagement FMclass = new facultyManagement();
    private profileManagement PMclass = new profileManagement();
    private UserCheck userCheck = new UserCheck();

    private VBox choice;
    private Button back = new Button("Back"), logout;
    private Stage stage;
    private Scene loginScene, moduleMenuScene, sceneProfile;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        GridPane main = new GridPane();
        BorderPane root = new BorderPane();
        BorderPane profileRoot = new BorderPane();

        Scene scene = new Scene(main, 800, 800);
        moduleMenuScene = new Scene(root, 800, 800);
        loginScene = scene;
        sceneProfile = new Scene(profileRoot, 800, 800);

        stage.setTitle("University Management System");
        main.setHgap(15);
        main.setVgap(15);

        Button submit = new Button("Login");
        Label user = new Label("Username:");
        Label password = new Label("Password:");

        choice = new VBox(10);
        ComboBox<String> modules = new ComboBox<>();
        modules.setPromptText("Select Module");
        modules.setPrefWidth(300);

        modules.setOnAction(e -> {
            if (!actionFired) {
                actionFired = true;
                String selected = modules.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    switch (selected) {
                        case "Course Management" -> stage.setScene(courseManagementControl());
                        case "Subject Management" -> stage.setScene(subjectManagementControl());
                        case "Faculty Management" -> stage.setScene(facultyManagementControl());
                        case "Student Management" -> stage.setScene(studentManagementControl());
                        case "Event Management" -> stage.setScene(eventManagementControl());
                        case "Dashboard" -> stage.setScene(dashboardManagementControl());
                        case "My Profile" -> {
                            int rowIndex = (role == FACULTY) ? userCheck.getLoggedInFacultyRow() : userCheck.getLoggedInStudentRow();
                            if (rowIndex != -1) {
                                stage.setScene(profileManagementControl(rowIndex));
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Profile Error");
                                alert.setHeaderText("Login Required");
                                alert.setContentText("Please log in again to access your profile.");
                                alert.showAndWait();
                            }
                        }
                    }
                }
                actionFired = false;
            }
        });

        choice.getChildren().add(modules);
        TextField userName = new TextField();
        PasswordField pass = new PasswordField();

        submit.setOnAction(e -> {
            String userNameCheck = userName.getText();
            String passCheck = pass.getText();

            userCheck = new UserCheck();

            if (userNameCheck.equals("admin") && passCheck.equals("admin123")) {
                role = ADMIN;
            } else if (userCheck.validateLogin(userNameCheck, passCheck, FACULTY)) {
                role = FACULTY;
            } else if (userCheck.validateLogin(userNameCheck, passCheck, STUDENT)) {
                role = STUDENT;
            } else {
                main.add(new Label("Invalid Credentials."), 1, 3);
                return;
            }

            CMclass.setRole(role);
            SMclass.setRole(role);
            StMclass.setRole(role);
            FMclass.setRole(role);
            EMclass.setRole(role, userCheck);

            stage.setScene(moduleMenuScene);
            root.setCenter(choice);
            modules.getItems().clear();

            if (role == ADMIN) {
                modules.getItems().addAll("Course Management", "Subject Management", "Faculty Management", "Student Management", "Event Management", "Dashboard");
            } else {
                modules.getItems().addAll("Course Management", "Event Management", "My Profile", "Dashboard");
            }
        });

        logout = new Button("Logout");
        logout.setOnAction(e -> {
            role = 0;
            modules.getItems().clear();
            modules.getSelectionModel().clearSelection();
            choice.getChildren().clear();

            modules.setPromptText("Select Module");
            choice.getChildren().add(modules);

            stage.setScene(loginScene);
        });

        back = new Button("Back");
        back.setOnAction(e -> {
            stage.setScene(moduleMenuScene);
            modules.getSelectionModel().clearSelection();

            if (modules.getItems().isEmpty()) {
                if (role == ADMIN) {
                    modules.getItems().addAll("Course Management", "Subject Management", "Faculty Management", "Student Management", "Event Management", "Dashboard");
                } else {
                    modules.getItems().addAll("Course Management", "Event Management", "My Profile", "Dashboard");
                }
            }

            if (!choice.getChildren().contains(modules)) {
                choice.getChildren().add(modules);
            }
        });

        main.add(user, 0, 0);
        main.add(password, 0, 1);
        main.add(userName, 1, 0);
        main.add(pass, 1, 1);
        main.add(submit, 1, 2);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

//    public Scene courseManagementControl() {
//        CMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(CMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        courseScene = new Scene(container, 800, 800);
//        return courseScene;
//    }
public Scene courseManagementControl() {
    CMclass.setRole(role); // Set role early

    VBox container = new VBox(10);
    container.getChildren().add(CMclass.getAdminControls()); // ✅ Fix: show full admin layout

    HBox bottomButtons = new HBox(10);
    bottomButtons.getChildren().addAll(back, logout);

    back.setOnAction(e -> stage.setScene(moduleMenuScene));
    logout.setOnAction(e -> {
        role = 0;
        stage.setScene(loginScene);
    });

    container.getChildren().add(bottomButtons);
    courseScene = new Scene(container, 800, 800);
    return courseScene;
}


    public Scene facultyManagementControl() {
        FMclass.setRole(role);
        VBox container = new VBox(10);
        container.getChildren().add(FMclass.getAdminControls());

        HBox bottomButtons = new HBox(10);
        bottomButtons.getChildren().addAll(back, logout);

        back.setOnAction(e -> stage.setScene(moduleMenuScene));
        logout.setOnAction(e -> {
            role = 0;
            stage.setScene(loginScene);
        });

        container.getChildren().add(bottomButtons);
        facultyScene = new Scene(container, 800, 800);
        return facultyScene;
    }

//    public Scene studentManagementControl() {
//        StMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(StMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        studentScene = new Scene(container, 800, 800);
//        return studentScene;
//    }
public Scene studentManagementControl() {
    StMclass.setRole(role); // ✅ set the role early

    VBox container = new VBox(10);
    container.getChildren().add(StMclass.getAdminControls()); // ✅ show full layout (inputs + list)

    HBox bottomButtons = new HBox(10);
    bottomButtons.getChildren().addAll(back, logout);

    back.setOnAction(e -> stage.setScene(moduleMenuScene));
    logout.setOnAction(e -> {
        role = 0;
        stage.setScene(loginScene);
    });

    container.getChildren().add(bottomButtons);
    studentScene = new Scene(container, 800, 800);
    return studentScene;
}


    //    public Scene subjectManagementControl() {
//        SMclass.setRole(role);
//        VBox container = new VBox(10);
//        container.getChildren().add(SMclass.getList());
//
//        HBox bottomButtons = new HBox(10);
//        bottomButtons.getChildren().addAll(back, logout);
//
//        back.setOnAction(e -> stage.setScene(moduleMenuScene));
//        logout.setOnAction(e -> {
//            role = 0;
//            stage.setScene(loginScene);
//        });
//
//        container.getChildren().add(bottomButtons);
//        subjectScene = new Scene(container, 800, 800);
//        return subjectScene;
//    }
public Scene subjectManagementControl() {
    SMclass.setRole(role); // ✅ Set role early

    VBox container = new VBox(10);
    container.getChildren().add(SMclass.getAdminControls()); // ✅ Full admin UI

    HBox bottomButtons = new HBox(10);
    bottomButtons.getChildren().addAll(back, logout);

    back.setOnAction(e -> stage.setScene(moduleMenuScene));
    logout.setOnAction(e -> {
        role = 0;
        stage.setScene(loginScene);
    });

    container.getChildren().add(bottomButtons);
    subjectScene = new Scene(container, 800, 800);
    return subjectScene;
}


    public Scene eventManagementControl() {
        EMclass.setRole(role, userCheck); // ✅ FIXED: Set role first

        VBox container = new VBox(10);
        container.getChildren().add(EMclass.getAdminControls()); // ✅ FIXED: show full layout, not just list

        HBox bottomButtons = new HBox(10);
        bottomButtons.getChildren().addAll(back, logout);

        back.setOnAction(e -> stage.setScene(moduleMenuScene));
        logout.setOnAction(e -> {
            role = 0;
            stage.setScene(loginScene);
        });

        container.getChildren().add(bottomButtons);
        eventScene = new Scene(container, 800, 800);
        return eventScene;
    }

    public Scene dashboardManagementControl() {
        VBox container = new VBox(10);
        container.getChildren().add(dashboard.setDashboard());

        HBox bottomButtons = new HBox(10);
        bottomButtons.getChildren().addAll(back, logout);

        back.setOnAction(e -> stage.setScene(moduleMenuScene));
        logout.setOnAction(e -> {
            role = 0;
            stage.setScene(loginScene);
        });

        container.getChildren().add(bottomButtons);
        dashboardScene = new Scene(container, 800, 800);
        return dashboardScene;
    }

    public Scene profileManagementControl(int rowIndex) {
        profileScene = PMclass.showProfile(role, rowIndex, stage, moduleMenuScene, getBackButton());
        return profileScene;
    }

    public Button getBackButton() {
        return back;
    }
}
