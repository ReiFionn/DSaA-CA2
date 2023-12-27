package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    public TextField machineNameText;
    public TextField machinePriceText;
    public TextField machineManuText;
    public TextField machineDescText;
    public TextField machineTypeText;
    public TextField machineMediaText;
    public TextField machineImageText;
    public TextField machineYearText;
    public TextField gameNameText;
    public TextField gamePubText;
    public TextField gameDescText;
    public TextField gameDevText;
    public TextField gameYearText;
    public TextField gameCoverText;
    public TextField portCoverText;
    public TextField portDevText;
    public TextField portYearText;
    public Button addMachineButton; //might not need
    public Button addGameButton; //might not need
    public Button addPortButton; //might not need
    public ListView<Game> currentGamesView;
    public ListView<GamePort> currentPortsView;
    public Button editMachineButton; //might not need
    public Button deleteMachineButton; //might not need
    public Button editGameButton; //might not need
    public Button deleteGameButton; //might not need
    public Button editPortButton; //might not need
    public Button deletePortButton; //might not need
    public Button saveButton; //might not need
    public Button loadButton; //might not need
    public Button resetButton; //might not need
    public ComboBox<GamesMachine> gameMachineCombo; //machines for games
    public ComboBox<Game> portGameCombo; //games for ports
    public ComboBox<GamesMachine> portMachineCombo; //machines for ports

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

