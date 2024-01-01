module system.dsaaca2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens system.dsaaca2 to javafx.fxml, xstream;
    exports system.dsaaca2;
    exports system.dsaaca2.Controllers;
    opens system.dsaaca2.Controllers to javafx.fxml, xstream;
    exports system.dsaaca2.Datastructures;
    opens system.dsaaca2.Datastructures to javafx.fxml, xstream;
    exports system.dsaaca2.Models;
    opens system.dsaaca2.Models to javafx.fxml, xstream;
    exports system.dsaaca2.utils;
    opens system.dsaaca2.utils to javafx.fxml, xstream;
}