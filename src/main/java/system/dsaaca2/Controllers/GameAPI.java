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
    public static SillyList<Game> allGames = new SillyList<>();
    public static SillyList<GamePort> allGamePorts = new SillyList<>();

    public boolean isMachineDuplicate(String n, String man) {
        /*Ensures no duplicates in the system */
        for (GamesMachine m : allMachines) {
            if (m.getName().equalsIgnoreCase(n) && m.getManufacturer().equalsIgnoreCase(man)) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }


    public boolean isGameDuplicate(String n, int y ) {
        /*Ensures no duplicates in the system */
        for (Game g : allGames) {
            if (g.getName().equalsIgnoreCase(n) && g.getYear() == y) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }



    public boolean isPortDuplicate(GamesMachine gm, Game g) {
        /*Ensures no duplicates in the event the original port = new port, the game the year = the original machines year*/
        for (GamePort gp : allGamePorts) {
            if (gp.getOriginalMachine().equals(gm) && gp.getOriginalGame().equals(g) && gp.getPortYear() == gp.getOriginalMachine().getYear()){
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }

    /*
     * Method for adding a new games machine.
     * Checks for empty fields, checks for valid inputs where necessary and prevents duplicate entries.
     */
    /*
     * Method for adding a new games machine.
     * Checks for null or empty fields, validates inputs, and prevents duplicate entries.
     */
    public void addGamesMachine() {
        /* Check for null or empty fields */
        if (!machineNameText.getText().isEmpty() && !machinePriceText.getText().isEmpty() &&
                !machineImageText.getText().isEmpty() && !machineYearText.getText().isEmpty()) {

            /* Get input values */
            String name = machineNameText.getText();
            String manufacturer = machineManuText.getText();
            String description = machineDescText.getText();
            String type = machineTypeText.getText();
            String media = machineMediaText.getText();
            String image = machineImageText.getText();

            /* Ensure year input is a number and within the valid range */
            int year = 0;
            try {
                year = Integer.parseInt(machineYearText.getText()); /* Parsing the string value input to convert to a numerical value */
                if (!Utilities.intValidRange(year, 1900, 2024)) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                    return;
                }
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                return;
            }

            float price = 0;
            /* Ensure price input is a number */
            try {
                price = Float.parseFloat(machinePriceText.getText());
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("ERROR", "PLEASE USE NUMERICAL VALUES ONLY FOR PRICE FIELD.");
                return;
            }

            GamesMachine gm = new GamesMachine(name, manufacturer, description, type, media, image, year, price);

            allMachines.add(gm); /* Add to the global list */
            currentMachinesView.getItems().add(gm);
            gameMachineCombo.getItems().add(gm);
            portMachineCombo.getItems().add(gm);
            /* Alert for successful add */
            Utilities.showInformationAlert("SUCCESS!", gm.getName().toUpperCase() +" ADDED SUCCESSFULLY!");

        } else {
            /* Show warning for empty fields */
            Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS ALLOWED, PLEASE ENTER A VALUE FOR ALL FIELDS!!!");
        }
    }




    /*
     * Method for adding a new game.
     * Checks for selected machine, empty fields, valid year input, and prevents duplicate entries.
     */
    public void addGame() {
        /* Step 1: Get Selected Machine */
        GamesMachine selectedMachine = gameMachineCombo.getSelectionModel().getSelectedItem();

        /* Step 2: Check for Null Selected Machine */
        if (selectedMachine == null) {
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A MACHINE FOR THIS GAME");
            return;
        }

        /* Step 3: Check for Empty Fields */
        if (!gameNameText.getText().isEmpty() && !gamePubText.getText().isEmpty() &&
                !gameDescText.getText().isEmpty() && !gameDevText.getText().isEmpty() &&
                !gameCoverText.getText().isEmpty() && !gameYearText.getText().isEmpty()) {

        } else {
            Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS ALLOWED, PLEASE ENTER A VALUE FOR ALL FIELDS!!!");
            return; /* Don't allow null values */
        }

        /* Ensure year input is a number and within the valid range */
        int year = 0;
        try {
            year = Integer.parseInt(gameYearText.getText()); /* Parsing the string value input to convert to a numerical value */
            if (!Utilities.intValidRange(year, 1900, 2024)) {
                Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                return;
            }
        } catch (NumberFormatException e) {
            Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
            return;
        }

        /* Don't allow a game to exist before the year of its machine */
        if (year < selectedMachine.getYear()) {
            Utilities.showWarningAlert("ERROR", "A GAME FOR THIS MACHINE CAN NOT EXIST BEFORE THE YEAR THE MACHINE WAS RELEASED");
        }

        /* Get input values */
        String name = gameNameText.getText();
        String pub = gamePubText.getText();
        String description = gameDescText.getText();
        String dev = gameDevText.getText();
        String cover = gameCoverText.getText();


        Game g = new Game(name, pub, description, dev, selectedMachine, cover, year);

        /* Step 6: Don't allow duplicate games */
        if (isGameDuplicate(name, year)) {
            Utilities.showWarningAlert("DUPLICATE ERROR", g.getName().toUpperCase() + " IS ALREADY IN THE SYSTEM");
            return; /* Alert if duplicate found */
        }

        /* Step 7: Add Game to system and update GUI */
        allGames.add(g); /* Adds game to the global list */
        selectedMachine.addGameToMachinesList(g); /* Adds game to the selected machine's list of games */
        currentGamesView.getItems().add(g);
        portGameCombo.getItems().add(g);

        /* Step 8: Clear fields */
        gameNameText.clear();
        gamePubText.clear();
        gameDescText.clear();
        gameDevText.clear();
        gameCoverText.clear();
        gameYearText.clear();

        /* Step 9: Alert for successful add */
        Utilities.showInformationAlert("SUCCESS!", g.getName().toUpperCase() + " ADDED SUCCESSFULLY TO SYSTEM" +
                "\nAND TO " + selectedMachine.getName().toUpperCase() + "'S CATEGORY");

    }



    /**
     * Adds a new game port, linking a selected game with a machine, after validating input fields,
     * checking for duplicates, ensuring the year input is a number within a valid range,
     * and verifying that the port year doesn't come before the release years of both the machine and the game.
     */
    public void addGamePort() {
        /* Step 1: Get selected machine and game from combo boxes */
        GamesMachine selectedMachine = portMachineCombo.getSelectionModel().getSelectedItem();
        Game selectedGame = portGameCombo.getSelectionModel().getSelectedItem();

        /* Step 2: Don't allow null selections */
        if (selectedGame != null && selectedMachine != null) {
            /* Step 3: Get values from text fields */
            String developers = portDevText.getText();
            String cover = portCoverText.getText();

            /* Step 4: Don't allow empty inputs */
            if (portDevText.getText().isEmpty() && portCoverText.getText().isEmpty() && portYearText.getText().isEmpty()) {
                Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS!!!");
            }

            /* Step 5: Ensure year input is a number and within the valid range */
            int year = 0;
            try {
                year = Integer.parseInt(portYearText.getText()); /* Parsing the string value input to convert to a numerical value */
                if (!Utilities.intValidRange(year, 1900, 2024)) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                    return;
                }
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                return;
            }

            /* Step 6: Don't allow a port to exist before the year of its game or machine */
            if (year < selectedMachine.getYear() && year < selectedGame.getYear()) {
                Utilities.showWarningAlert("ERROR", "A PORT FOR THIS MACHINE CAN NOT EXIST BEFORE THE YEAR THE MACHINE/GAME WAS RELEASED");
            }

            /* Step 7: Don't allow a port with duplicate game and machine it will already be a port in system */
            if (isPortDuplicate(selectedMachine, selectedGame)) {
                Utilities.showWarningAlert("ERROR", "A PORT FOR THIS GAME AND MACHINE ALREADY EXISTS!");
            }

            /* Step 8: Create a new GamePort and add to system and update GUI */
            GamePort newGamePort = new GamePort(selectedGame, selectedMachine, selectedGame.getGamesMachine(), developers, cover, year);
            allGamePorts.add(newGamePort); /* add to global list */
            selectedGame.addPortToGamesListOfPorts(newGamePort); /* add to the selected games list of ports*/
            currentPortsView.getItems().add(newGamePort);
        } else {
            /* Step 9: Show warning if Game and Machine not selected */
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A GAME AND A MACHINE TO CONTINUE");
        }
    }









    public void switchSceneGame() {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void switchSceneSystem() {
        Main.mainStage.setScene(Main.systemScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameAPI.gameAPI = this;
    }
}

