module com.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.naming;
    requires java.logging;

    opens com.librarymanagementsystem to javafx.fxml;
    exports com.librarymanagementsystem;
}
