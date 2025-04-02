package com.example.demo4;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class managementParent {
    //int constant to represent the admin role
    protected final int ADMIN = 1;
    protected int role; //user role (non admin)

    //UI layout elements
    protected VBox layoutContainer;
    protected ListView<String> listView;

    //sets the role of the user (either admin or user)
    public void setRole(int role) {
        this.role = role;
    }

    //Constructs the main list layout including admin controls if applicable, then returns it
    public VBox getList() {
        layoutContainer.getChildren().clear(); //clears the existing UI elements
        if (role == ADMIN) {
            layoutContainer.getChildren().add(getAdminControls()); //adds admin controls if you are admin
        }
        layoutContainer.getChildren().add(getListViewWrapper()); //add the list view

        return layoutContainer;
    }

    // Abstract methods to be implemented by child classes
    protected abstract void updateList();
    protected abstract VBox getAdminControls();
    protected abstract HBox getListViewWrapper();
}
