module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
}