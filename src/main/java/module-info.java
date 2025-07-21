module com.example.labelmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.labelmaker to javafx.fxml;
    exports com.example.labelmaker;
}