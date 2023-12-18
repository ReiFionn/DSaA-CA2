module system.dsaaca2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens system.dsaaca2 to javafx.fxml;
    exports system.dsaaca2;
}