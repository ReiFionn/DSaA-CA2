package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemAPI implements Initializable {

    private static SystemAPI systemAPI;
    public ToggleGroup machineFilter;

    public void searchByMachine() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemAPI = this;
    }

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }
}
