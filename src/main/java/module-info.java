module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires password4j;
    requires java.sql;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}