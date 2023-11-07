module com.example.mainlogin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.mainlogin to javafx.fxml;
    exports com.example.mainlogin;
}