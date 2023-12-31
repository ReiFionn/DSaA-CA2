package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;
import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.allMachines;

public class SystemAPI implements Initializable {

    private static SystemAPI systemAPI;
    public TextField searchMachineLinearField;
    public ToggleGroup machineFilter;
    public RadioButton machineNameTog;
    public RadioButton machineDescTog;
    public RadioButton machineYearTog;
    public RadioButton machineManuTog;
    public RadioButton machineTypeTog;
    public RadioButton machineMediaTog;
    public ToggleGroup machineSort;
    public RadioButton machineAlphaTog;
    public RadioButton machineHTLTog;
    public RadioButton machineOTYTog;
    public ListView<GamesMachine> machineLinearSearchList;
    public TextField searchMachineField;
    public ListView<GamesMachine> machineSearchList;
    public TextField searchGameLinearField;
    public ToggleGroup gameFilter;
    public RadioButton gameNameTog;
    public RadioButton gameDescTog;
    public RadioButton gameYearTog;
    public RadioButton gameManuTog;
    public RadioButton gameTypeTog;
    public RadioButton gameMediaTog;
    public ToggleGroup gameSort;
    public RadioButton gameAlphaTog;
    public RadioButton gameHTLTog;
    public RadioButton gameOTYTog;
    public ListView<Game> gameLinearSearchList;
    public ListView<GamePort> portLinearSearchList;
    public TextField searchGameField;
    public ListView<Game> gameSearchList;
    public ListView<GamePort> portSearchList;

    public void searchByMachine() {
        machineSearchList.getItems().clear();
        if (!searchMachineField.getText().isEmpty()) {
            String search = searchMachineField.getText();
            if (GameAPI.gameAPI.nameMap.find(search) != null) {
                machineSearchList.getItems().add(GameAPI.gameAPI.nameMap.find(search));
            } else {
                Utilities.showWarningAlert("ERROR", "NO SEARCH RESULTS");
            }
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE ENTER TEXT TO SEARCH FOR");
    }

    public void searchByMachineLinear() {
        machineLinearSearchList.getItems().clear();
        if (!searchMachineLinearField.getText().isEmpty()) {
            Toggle selectedToggle = machineFilter.getSelectedToggle();
            String search = searchMachineLinearField.getText();
            String filter;
            if (selectedToggle != null) {
                if (selectedToggle.equals(machineManuTog)) {
                    for (GamesMachine m : allMachines) {
                        if (m.getManufacturer().toLowerCase().startsWith(search.toLowerCase())) {
                            machineLinearSearchList.getItems().add(m);
                        }
                    }
                } else if (selectedToggle.equals(machineMediaTog)) {
                    for (GamesMachine m : allMachines) {
                        if (m.getMedia().toLowerCase().startsWith(search.toLowerCase())) {
                            machineLinearSearchList.getItems().add(m);
                        }
                    }
                } else if (selectedToggle.equals(machineDescTog)) {
                    for (GamesMachine m : allMachines) {
                        if (m.getDescription().toLowerCase().startsWith(search.toLowerCase())) {
                            machineLinearSearchList.getItems().add(m);
                        }
                    }
                } else if (selectedToggle.equals(machineTypeTog)) {
                    for (GamesMachine m : allMachines) {
                        if (m.getType().toLowerCase().startsWith(search.toLowerCase())) {
                            machineLinearSearchList.getItems().add(m);
                        }
                    }
                } else if (selectedToggle.equals(machineNameTog)) {
                    for (GamesMachine m : allMachines) {
                        if (m.getName().toLowerCase().startsWith(search.toLowerCase())) {
                            machineLinearSearchList.getItems().add(m);
                        }
                    }
                } else {
                    for (GamesMachine m : allMachines) {
                        int year;
                        try {
                            year = Integer.parseInt(search); /* Parsing the string value input to convert to a numerical value */
                            if (!Utilities.intValidRange(year, 1900, 2024)) {
                                Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                            return;
                        }
                        if (m.getYear() == year)
                            machineLinearSearchList.getItems().add(m);
                    }
                }
            } else
                Utilities.showWarningAlert("ERROR", "PLEASE SELECT A FILTER");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE ENTER SOMETHING TO SEARCH FOR");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SystemAPI.systemAPI = this;
    }

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }
}
