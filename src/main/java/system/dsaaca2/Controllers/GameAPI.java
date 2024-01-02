package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.Models.Hashable;
import system.dsaaca2.utils.Persistence;
import system.dsaaca2.utils.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameAPI implements Initializable {
    public static GameAPI gameAPI = new GameAPI();
    /*---------------------Java fx--------------------*/
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
    public ComboBox<GamesMachine> gameMachineCombo; //machines for games
    public ComboBox<Game> portGameCombo; //games for ports
    public ComboBox<GamesMachine> portMachineCombo; //machines for ports
    public static SillyList<GamesMachine> allMachines = new SillyList<>();
    public static SillyList<Game> allGames = new SillyList<>();
    public static SillyList<GamePort> allGamePorts = new SillyList<>();
    public static HashMap<Hashable> hashMap = new HashMap<>(10);
    /*----------------------------------------------------------*/

    /**
     * Adds a new game machine to the system.
     * Validates input fields and checks for duplicates.
     */
    public void addGamesMachine() {
        if (!machineNameText.getText().isEmpty() && !machinePriceText.getText().isEmpty() && !machineImageText.getText().isEmpty() && !machineYearText.getText().isEmpty() && !machineManuText.getText().isEmpty() && !machineDescText.getText().isEmpty() && !machineMediaText.getText().isEmpty() && !machineTypeText.getText().isEmpty()) {
            String name = machineNameText.getText();
            String manufacturer = machineManuText.getText();
            String description = machineDescText.getText();

            String type = machineTypeText.getText();
            String media = machineMediaText.getText();
            String image = machineImageText.getText();
            int year;
            try {
                year = Integer.parseInt(machineYearText.getText());
                if (!Utilities.intValidRange(year, 1900, 2024)) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                    return;
                }
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                return;
            }
            double price;
            try {
                price = Double.parseDouble(machinePriceText.getText());
            } catch (NumberFormatException e) {
                Utilities.showWarningAlert("ERROR", "PLEASE USE NUMERICAL VALUES ONLY FOR PRICE FIELD.");
                return;
            }

            GamesMachine gm = new GamesMachine(name, manufacturer, description, type, media, image, year, price);

            boolean dupe = false;
            for (GamesMachine m : allMachines) {
                if (m.getName().equalsIgnoreCase(gm.getName()) && m.getManufacturer().equalsIgnoreCase(gm.getManufacturer())) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                allMachines.add(gm);
                hashMap.add(gm.toString(), gm);
                gameMachineCombo.getItems().add(gm);
                portMachineCombo.getItems().add(gm);
                MachineEditController.machineEditController.machineEditTable.getItems().add(gm);

                machineNameText.clear();
                machineTypeText.clear();
                machineMediaText.clear();
                machineDescText.clear();
                machineYearText.clear();
                machineImageText.clear();
                machinePriceText.clear();
                machineManuText.clear();

                Utilities.showInformationAlert("SUCCESS!", gm.getName().toUpperCase() + " ADDED SUCCESSFULLY!");
            } else {
                Utilities.showWarningAlert("ERROR", "DUPLICATE GAME MACHINE ENTERED!");
            }

        } else {
            Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS ALLOWED, PLEASE ENTER A VALUE FOR ALL FIELDS!!!");
        }
    }

    /**
     * Adds a new game to the system.
     * Validates input fields, checks for duplicates, and ensures the game is associated with a valid game machine.
     */
    public void addGame() {
        GamesMachine selectedMachine = gameMachineCombo.getSelectionModel().getSelectedItem();

        if (!gameNameText.getText().isEmpty() && !gamePubText.getText().isEmpty() && !gameDescText.getText().isEmpty() && !gameDevText.getText().isEmpty() && !gameCoverText.getText().isEmpty() && !gameYearText.getText().isEmpty() && selectedMachine != null) {
            String name = gameNameText.getText();
            String pub = gamePubText.getText();
            String description = gameDescText.getText();
            String dev = gameDevText.getText();
            String cover = gameCoverText.getText();
            int year ;
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

            if (year >= selectedMachine.getYear()) {
                Game g = new Game(name, pub, description, dev, selectedMachine, cover, year);

                boolean dupe = false;
                for (Game game : allGames) {
                    if (g.getName().equalsIgnoreCase(game.getName()) && g.getDevelopers().equalsIgnoreCase(game.getDevelopers())) {
                        dupe = true;
                        break;
                    }
                }

                if (!dupe) {
                    allGames.add(g);
                    selectedMachine.addGame(g);
                    GameEditController.gameEditController.gameEditTable.getItems().add(g);
                    portGameCombo.getItems().add(g);
                    hashMap.add(g.toString(), g);

                    gameNameText.clear();
                    gamePubText.clear();
                    gameDescText.clear();
                    gameDevText.clear();
                    gameCoverText.clear();
                    gameYearText.clear();
                    gameMachineCombo.getSelectionModel().clearSelection();

                    Utilities.showInformationAlert("SUCCESS!", g.getName().toUpperCase() + " ADDED SUCCESSFULLY TO SYSTEM!");
                } else
                    Utilities.showWarningAlert("ERROR", "DUPLICATE GAME ENTERED");
            } else
                Utilities.showWarningAlert("ERROR", "A GAME FOR THIS MACHINE CAN NOT EXIST BEFORE THE YEAR THE MACHINE WAS RELEASED");
        } else
            Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS ALLOWED, PLEASE ENTER A VALUE FOR ALL FIELDS!!!");
    }


    /**
     * Adds a new game port to the system.
     * Validates input fields, checks for duplicates, and ensures the port is associated with valid game and game machine.
     */
    public void addGamePort() {
        GamesMachine selectedMachine = portMachineCombo.getSelectionModel().getSelectedItem();
        Game selectedGame = portGameCombo.getSelectionModel().getSelectedItem();

        if (selectedGame != null && selectedMachine != null && !portDevText.getText().isEmpty() && !portCoverText.getText().isEmpty() && !portYearText.getText().isEmpty()) {
            String developers = portDevText.getText();
            String cover = portCoverText.getText();
            int year;
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
            if (!selectedMachine.equals(selectedGame.getGamesMachine())) {
                if (year >= selectedMachine.getYear() && year >= selectedGame.getYear()) {
                    GamePort newGamePort = new GamePort(selectedGame, selectedMachine, selectedGame.getGamesMachine(), developers, cover, year);

                    boolean dupe = false;
                    for (GamePort port : allGamePorts) {
                        if (port.getOriginalGame().equals(newGamePort.getOriginalGame()) && port.getMachinePortedTo().equals(newGamePort.getMachinePortedTo())) {
                            dupe = true;
                            break;
                        }
                    }

                    if (!dupe) {
                        allGamePorts.add(newGamePort);
                        hashMap.add(newGamePort.toString(), newGamePort);
                        selectedGame.addPort(newGamePort);
                        PortEditController.portEditController.portEditTable.getItems().add(newGamePort);
                        hashMap.add(newGamePort.toString(), newGamePort);

                        portMachineCombo.getSelectionModel().clearSelection();
                        portGameCombo.getSelectionModel().clearSelection();
                        portDevText.clear();
                        portCoverText.clear();
                        portYearText.clear();

                        Utilities.showInformationAlert("SUCCESS!", newGamePort.getOriginalGame().getName().toUpperCase() + "'S PORT TO " + newGamePort.getMachinePortedTo().getName().toUpperCase() + " ADDED SUCCESSFULLY!");
                    } else
                        Utilities.showWarningAlert("ERROR", "DUPLICATE PORT ENTERED");
                } else
                    Utilities.showWarningAlert("ERROR", "A PORT FOR THIS MACHINE CAN NOT EXIST BEFORE THE YEAR THE MACHINE/GAME WAS RELEASED");
            } else
                Utilities.showWarningAlert("ERROR", "A PORT CANNOT BE FOR THE SAME MACHINE THAT THE ORIGINAL GAME WAS MADE FOR");
        } else {
            Utilities.showWarningAlert("ERROR", "NO EMPTY FIELDS ALLOWED, PLEASE ENTER A VALUE FOR ALL FIELDS!!!");
        }
    }

    /**
     * Saves the current state of the system for persistence.
     */
    public void save() {
        try {
            Persistence.save();
            Utilities.showInformationAlert("SAVED!","PROGRESS SAVED TO SYSTEM");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Saving.");
        }
    }

    /**
     * Loads a previous save of the system from persistent storage.
     * Populates data structures and UI from save.
     */
    public void load() {
        try {
            reset();
            Persistence.load();

            for (GamesMachine machine : allMachines) {
                MachineEditController.machineEditController.machineEditTable.getItems().add(machine);
                gameMachineCombo.getItems().add(machine);
                portMachineCombo.getItems().add(machine);
                hashMap.add(machine.toString(), machine);

                for (Game game : machine.getGames()) {
                    GameEditController.gameEditController.gameEditTable.getItems().add(game);
                    portGameCombo.getItems().add(game);
                    allGames.add(game);

                    hashMap.add(game.toString(), game);

                    for (GamePort port : game.getPorts()) {
                        PortEditController.portEditController.portEditTable.getItems().add(port);

                        allGamePorts.add(port);
                        hashMap.add(port.toString(), port);
                    }
                }
            }

            Utilities.showInformationAlert("LOADED!","PAST SAVE LOADED TO SYSTEM");
        } catch (Exception e) {
            Utilities.showWarningAlert("ERROR","SYSTEM COULD NOT BE LOADED");
            e.printStackTrace();
        }
    }


    /**
     * Resets the system by clearing all data structures and UI.
     */
    public void reset() {
        allGames.clear();
        allMachines.clear();
        allGamePorts.clear();

        GameEditController.gameEditController.gameEditTable.getItems().clear();
        MachineEditController.machineEditController.machineEditTable.getItems().clear();
        PortEditController.portEditController.portEditTable.getItems().clear();
        gameMachineCombo.getItems().clear();
        portGameCombo.getItems().clear();
        portMachineCombo.getItems().clear();
        hashMap = new HashMap<>(10); //clears hashMap
        Utilities.showInformationAlert("RESET COMPLETE","SYSTEM DATA CLEARED");
    }

    /**
     * Opens the machine editor popup for editing existing machines.
     * Only opens if machines are added in the system.
     */
    public void editMachine() throws IOException {
        if (!allMachines.isEmpty()) {
            Main.newPopup("/machineEditor.fxml", "MACHINE EDITOR").show();
        }
    }

    /**
     * Opens the game editor popup for editing existing games.
     * Only opens if games are added in the system.
     */
    public void editGames() throws IOException {
        if (!allGames.isEmpty()) {
            Main.newPopup("/gameEditor.fxml", "GAME EDITOR").show();
        }
    }

    /**
     * Opens the game editor popup for editing existing games.
     * Only opens if ports are added in the system.
     */
    public void editPorts() throws IOException {
        if (!allGamePorts.isEmpty()) {
            Main.newPopup("/portEditor.fxml", "PORT EDITOR").show();
        }
    }

    /**
     * Switches the scene to the main game scene.
     */
    public void switchSceneGame() {
        Main.mainStage.setScene(Main.gameScene);
    }

    /**
     * Switches the scene to the main system scene.
     */
    public void switchSceneSystem() {
        Main.mainStage.setScene(Main.systemScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameAPI.gameAPI = this;
    }
}

