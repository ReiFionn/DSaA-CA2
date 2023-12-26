package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;

import java.net.URL;
import java.util.ResourceBundle;

public class GameAPI implements Initializable {
    public static SillyList<Game> allGames = new SillyList<>();
    private static GameAPI gameAPI;

    //TODO - add fx-ids to all text-fields and import here
    public ListView<GamesMachine> currentMachinesView;
    public ListView<Game> currentGamesView;
    public ListView<GamePort> currentPortsView; //do we want these list views to be objects?
    public TextField machineNameTextField;
    public TextField machineManuTextField;
    public TextField machineDescTextField;
    public TextField machineTypeTextField;
    public TextField machineMediaTextField;
    public TextField machineImageTextField;
    public TextField machineLaunchYearTextField;
    public TextField machinePriceTextField;
    public TextField gameNameTextField;
    public TextField gamePubTextField;
    public TextField gameDescTextField;
    public TextField gameDevTextField;
    public TextField gameMachineTextField; //combo box?
    public TextField gameCoverTextField;
    public TextField gameLaunchYearTextField;
    public TextField portOriginalTextField; //combo box?
    public TextField portMachineTextField; //combo box?
    public TextField portOrigMachineTextField; //combo box?
    public TextField portDevTextField;
    public TextField portCoverTextField;
    public TextField portLaunchYearTextField;
    public Button addMachineButton;
    public Button addGameButton;
    public Button addPortButton;
    public Button editMachineButton;
    public Button deleteMachineButton;
    public Button editGameButton;
    public Button deleteGameButton;
    public Button editPortButton;
    public Button deletePortButton; //idk if we need IDs on the buttons, can delete later if not

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameAPI = this;
    }

    public void switchSceneGame() {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void switchSceneSystem() {
        Main.mainStage.setScene(Main.systemScene);
    }
}

