package com.example.demo4;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Dashboard {

    private VBox dash;
    private ComboBox<Button> modules;
    private int students;
    private int courses;
    private int events;
    private int faculty;

    //Constructor that initializes the dashboard with the number of students
    public Dashboard(int students, int courses, int events, int faculty)
    {
        //assigns the passed value to students, creates a vertical layout with 10 pixels,
        // and initializes the drop down menu
        this.students = students;
        this.courses = courses;
        this.events = events;
        this.faculty = faculty;

        dash = new VBox(10);
        modules = new ComboBox<>();

        //creates and add label to display student count in VBox
        Label stud = new Label();
        stud.setText("Students: "+ students);
        Label course = new Label();
        course.setText("Courses: "+ courses);
        Label event = new Label();
        event.setText("Events: "+ events);
        Label fac = new Label();
        fac.setText("Faculty: "+ faculty);
        dash.getChildren().addAll(stud, course, event, fac);
    }


    //method that returns the VBox layout to be used in other parts of program
    public VBox setDashboard()
    {

        return dash;
    }

    public Scene setScene()
    {
        return new Scene(dash, 800, 800);
    }


}
