package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemAPI implements Initializable {

    private static SystemAPI systemAPI;
    public ToggleGroup machineFilter;
    public RadioButton machineNameTog;
    public RadioButton machineDescTog;
    public RadioButton machineYearTog;
    public TextField searchMachineField;
    public TextField searchGamesField;
    public ScrollPane currentGamesView;
    public ListView<GamesMachine> machineSearchList;
    public ListView<Game> gamesSearchList;
    public RadioButton machineManuTog;
    public RadioButton machineTypeTog;
    public RadioButton machineMediaTog;
    public ListView<GamePort> portsSearchList;
    public ListView machineLinearSearchList;
    public TextField searchMachineField1;
    public ScrollPane currentGamesView1;
    public ToggleGroup gameFilter;
    public RadioButton gameNameTog;
    public RadioButton gameDescTog;
    public RadioButton gameYearTog;

    public void searchByMachine() {
        HashMap<GamesMachine> selectedMap = null;
        if (!searchMachineField.getText().isEmpty()) {
            Toggle selectedToggle = machineFilter.getSelectedToggle();
            String search = searchMachineField.getText();
            if (selectedToggle!=null) {
                if (selectedToggle.equals(machineManuTog)) {
                } else if (selectedToggle.equals(machineMediaTog)) {
                } else if (selectedToggle.equals(machineDescTog)) {
                } else if (selectedToggle.equals(machineTypeTog)) {
                } else if (selectedToggle.equals(machineNameTog)) {
                    selectedMap = GameAPI.gameAPI.nameMap;
                } else {
                }

                if (selectedMap != null) {
                    machineSearchList.getItems().add(selectedMap.find(search));
                } else {
                    Utilities.showWarningAlert("ERROR", "NO SEARCH RESULTS");
                }
            } else
                Utilities.showWarningAlert("ERROR", "PLEASE SELECT A FILTER");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE ENTER TEXT TO SEARCH FOR");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemAPI = this;
    }

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }
}
