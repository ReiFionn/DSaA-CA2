module system.dsaaca2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens system.dsaaca2 to javafx.fxml, xstream;
    exports system.dsaaca2;
}