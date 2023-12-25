package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.GamesMachine;

import java.net.URL;
import java.util.ResourceBundle;

public class GamesMachineAPI implements Initializable {
    public static SillyList<GamesMachine> allGamesMachines = new SillyList<>();
    private static GamesMachineAPI gamesMachineAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamesMachineAPI = this;
    }



}
