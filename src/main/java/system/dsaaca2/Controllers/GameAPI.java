package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.*;
import system.dsaaca2.utils.Persistance;
import system.dsaaca2.utils.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameAPI implements Initializable {

    public static GameAPI gameAPI = new GameAPI();

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

    /*List of interface that both classes implement so both objects can be listed together*/

    public static SillyList<GamesMachine> allMachines = new SillyList<>();
    public static SillyList<Game> allGames = new SillyList<>();
    public static SillyList<GamePort> allGamePorts = new SillyList<>();

    public HashMap<Hashable> hashMap = new HashMap<>(10);

    public void addGamesMachine() {
        /* Check for null or empty fields */
        if (!machineNameText.getText().isEmpty() && !machinePriceText.getText().isEmpty() && !machineImageText.getText().isEmpty() && !machineYearText.getText().isEmpty() && !machineManuText.getText().isEmpty() && !machineDescText.getText().isEmpty() && !machineMediaText.getText().isEmpty() && !machineTypeText.getText().isEmpty()) {
            /* Get input values */
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
                //adding to all hashmaps
                hashMap.add(gm.toString(), gm);

                gameMachineCombo.getItems().add(gm);
                portMachineCombo.getItems().add(gm);
                EditsController.editsController.machineEditTable.getItems().add(gm);

                machineNameText.clear();
                machineTypeText.clear();
                machineMediaText.clear();
                machineDescText.clear();
                machineYearText.clear();
                machineImageText.clear();
                machinePriceText.clear();
                machineManuText.clear();

                /* Alert for successful add */
                Utilities.showInformationAlert("SUCCESS!", gm.getName().toUpperCase() + " ADDED SUCCESSFULLY!");
            } else {
                Utilities.showWarningAlert("ERROR", "DUPLICATE GAME MACHINE ENTERED!");
            }

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
        GamesMachine selectedMachine = gameMachineCombo.getSelectionModel().getSelectedItem();

        if (!gameNameText.getText().isEmpty() && !gamePubText.getText().isEmpty() && !gameDescText.getText().isEmpty() && !gameDevText.getText().isEmpty() && !gameCoverText.getText().isEmpty() && !gameYearText.getText().isEmpty() && selectedMachine != null) {
            /* Get input values */
            String name = gameNameText.getText();
            String pub = gamePubText.getText();
            String description = gameDescText.getText();
            String dev = gameDevText.getText();
            String cover = gameCoverText.getText();
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
                    allGames.add(g); /* Adds game to the global list */

                    selectedMachine.addGame(g); /* Adds game to the selected machine's list of games */
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
     * Adds a new game port, linking a selected game with a machine, after validating input fields,
     * checking for duplicates, ensuring the year input is a number within a valid range,
     * and verifying that the port year doesn't come before the release years of both the machine and the game.
     */
    public void addGamePort() {
        GamesMachine selectedMachine = portMachineCombo.getSelectionModel().getSelectedItem();
        Game selectedGame = portGameCombo.getSelectionModel().getSelectedItem();

        if (selectedGame != null && selectedMachine != null && !portDevText.getText().isEmpty() && !portCoverText.getText().isEmpty() && !portYearText.getText().isEmpty()) {
            String developers = portDevText.getText();
            String cover = portCoverText.getText();
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
                        allGamePorts.add(newGamePort); /* add to global list */
                        hashMap.add(newGamePort.toString(), newGamePort);
                        selectedGame.addPort(newGamePort); /* add to the selected games list of ports*/
                        PortEditController.portEditController.portEditTable.getItems().add(newGamePort);

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

    public void save() throws Exception {
        try {
            Persistance.save();
            Utilities.showInformationAlert("SAVED!","PROGRESS SAVED TO SYSTEM");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Saving.");
        }
    }

    public void load() {
        try {
            reset();
            Persistance.load();

            for (GamesMachine machine : allMachines) {
                EditsController.editsController.machineEditTable.getItems().add(machine);
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




    public void reset() {
        allGames.clear();
        allMachines.clear();
        allGamePorts.clear();

        GameEditController.gameEditController.gameEditTable.getItems().clear();
        EditsController.editsController.machineEditTable.getItems().clear();
        PortEditController.portEditController.portEditTable.getItems().clear();
        gameMachineCombo.getItems().clear();
        portGameCombo.getItems().clear();
        portMachineCombo.getItems().clear();
        //TODO CLEAR HASHMAP
        hashMap = new HashMap<>(10);
        Utilities.showInformationAlert("RESET COMPLETE","SYSTEM DATA CLEARED");
    }

    public void editMachine() throws IOException {

        if (!allMachines.isEmpty()) {
            Main.newPopup("/machineEditor.fxml", "MACHINE EDITOR").show();
        }
    }

    public void editGames() throws IOException {

        if (!allGames.isEmpty()) {
            Main.newPopup("/gameEditor.fxml", "GAME EDITOR").show();
        }
    }
    public void editPorts() throws IOException {

        if (!allGamePorts.isEmpty()) {
            Main.newPopup("/portEditor.fxml", "PORT EDITOR").show();
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

