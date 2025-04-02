package com.example.demo4;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class profileManagement {

    private Scene profileScene;

    //displays the profile scene based on user role (faculty or student)
    // role = 2 (Faculty), 3 (Student)
    public Scene showProfile(int role, int rowIndex, Stage stage, Scene backScene, Button backButton) {
        Pane root = new Pane();

        //load profile based on role
        if (role == 2) {
            facultyProfile profile = new facultyProfile(rowIndex);
            root.getChildren().add(profile.getLayout());
        } else if (role == 3) {
            studentProfile profile = new studentProfile(rowIndex, role); // ✅ pass role
            root.getChildren().add(profile.getLayout());
        }

        //ensures the back button is only added once
        if (!root.getChildren().contains(backButton)) {
            backButton.setLayoutX(10);
            backButton.setLayoutY(750);
            backButton.setOnAction(e -> stage.setScene(backScene));
            root.getChildren().add(backButton);
        }

        return new Scene(root, 800, 800); //return the constructed scene
    }
}
