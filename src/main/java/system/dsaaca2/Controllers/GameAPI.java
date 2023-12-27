package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

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

    public SillyList<GamesMachine> allMachines = new SillyList<>();


    public boolean isMachineDuplicate(String n, String man) {
        /*Ensures no duplicates in the system */
        for (GamesMachine m : allMachines) {
            if (m.getName().equalsIgnoreCase(n) && m.getManufacturer().equalsIgnoreCase(man)) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }





    /*
     * Method for adding a new games machine.
     * Checks for empty fields, checks for valid inputs where necessary and prevents duplicate entries.
     */
    public void addGamesMachine() {
        // Check for null fields
        if (!machineNameText.getText().isEmpty() && !machinePriceText.getText().isEmpty() && !machineManuText.getText().isEmpty() &&
                !machineDescText.getText().isEmpty() && !machineTypeText.getText().isEmpty() && !machineMediaText.getText().isEmpty() &&
                !machineImageText.getText().isEmpty() && !machineYearText.getText().isEmpty()) {

            // get input values
            String name = machineNameText.getText();
            String manufacturer = machineManuText.getText();
            String description = machineDescText.getText();
            String type = machineTypeText.getText();
            String media = machineMediaText.getText();
            String image = machineImageText.getText();

            // makes sure price input is a number
            float price;
            try {
                price = Float.parseFloat(machinePriceText.getText());
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("Invalid Price", "Please enter a valid price.");
                return;
            }

            // makes sure year input is a number
            int year;
            try {
                year = Integer.parseInt(machineYearText.getText());
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("Invalid Year", "Please enter a valid year.");
                return;
            }

            //new GamesMachine object
            GamesMachine machineToAdd = new GamesMachine(name, manufacturer, description, type, media, image, year, price);

            // makes sure it is not a duplicate entry
            if (isMachineDuplicate(name, manufacturer)) {
                Utilities.showWarningAlert("Duplicate Machine", "A machine with the same name and manufacturer already exists.");
                return;
            }

            // Add the new machine to the system
            allMachines.add(machineToAdd);
            currentMachinesView.getItems().add(machineToAdd);
            gameMachineCombo.getItems().add(machineToAdd);
            portMachineCombo.getItems().add(machineToAdd);

            // Clear input fields
            machineNameText.clear();
            machinePriceText.clear();
            machineManuText.clear();
            machineDescText.clear();
            machineTypeText.clear();
            machineMediaText.clear();
            machineImageText.clear();
            machineYearText.clear();

            // Show success alert
            Utilities.showInformationAlert("SUCCESS!", "GAMES MACHINE ADDED SUCCESSFULLY :D");

        } else {
            // Show warning for empty fields
            Utilities.showWarningAlert("No Empty Fields Allowed!!", "Please enter a value for all fields!");
        }
    }


    public void switchSceneGame() {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void switchSceneSystem() {
        Main.mainStage.setScene(Main.systemScene);
    }
}

