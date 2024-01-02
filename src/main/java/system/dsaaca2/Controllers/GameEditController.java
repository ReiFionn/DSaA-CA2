package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;
import java.net.URL;
import java.util.ResourceBundle;
import static system.dsaaca2.Controllers.GameAPI.*;

public class GameEditController implements Initializable {

    public static GameEditController gameEditController = new GameEditController();

    public TableView<Game> gameEditTable = new TableView<>();
    public TableColumn<GamesMachine, String> mName;
    public TableColumn<Game, String> gNam;
    public TableColumn<Game, String> gPub;
    public TableColumn<Game, String> gDesc;
    public TableColumn<Game, String> gDev;
    public TableColumn<Game, String> gCover;
    public TableColumn<Game, Integer> gYear;
    public TextField updateGameName;
    public TextField updateGamePub;
    public TextField updateGameDesc;
    public TextField updateGameDev;
    public TextField updateGameCover;
    public TextField updateGameYear;
    public ComboBox<GamesMachine> updateGamesMachineBox;

    public void onGameSelect() {
        Game selectedGame = gameEditTable.getSelectionModel().getSelectedItem();

        if (selectedGame != null) {
            updateGameName.setText(selectedGame.getName());
            updateGamePub.setText(selectedGame.getPublisher());
            updateGameCover.setText(selectedGame.getCover());
            updateGameDesc.setText(selectedGame.getDescription());
            updateGameYear.setText(String.valueOf(selectedGame.getYear()));
            updateGameDev.setText(selectedGame.getDevelopers());
            updateGamesMachineBox.setValue(selectedGame.getGamesMachine());
        }
    }

    public void applyGameUpdate() {
        Game selectedGame = gameEditTable.getSelectionModel().getSelectedItem();

        if (selectedGame != null) {
            if (!updateGameName.getText().isEmpty() && !updateGamePub.getText().isEmpty() && !updateGameCover.getText().isEmpty() && !updateGameDesc.getText().isEmpty() && !updateGameYear.getText().isEmpty() && !updateGameDev.getText().isEmpty() && !updateGamesMachineBox.isShowing()) {
                selectedGame.setName(updateGameName.getText());
                selectedGame.setPublisher(updateGamePub.getText());
                selectedGame.setCover(updateGameCover.getText());
                selectedGame.setDescription(updateGameDesc.getText());
                selectedGame.setDevelopers(updateGameDev.getText());
                selectedGame.setGamesMachine(updateGamesMachineBox.getSelectionModel().getSelectedItem());
                try {
                    selectedGame.setYear(Integer.parseInt(updateGameYear.getText()));
                    if (!Utilities.intValidRange(selectedGame.getYear(), 1900, 2024)) {
                        Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                        return;
                    }
                } catch (NumberFormatException e) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                    return;
                }

                int portsDeleted = 0;
                for (GamePort port : selectedGame.getPorts()) {
                    if (port.getYear() < selectedGame.getYear()) {
                        allGamePorts.remove(port);

                        portsDeleted++;
                    }
                }

                MachineEditController.machineEditController.refreshAllViews();
                gameEditTable.getItems().clear();
                gameEditTable.getItems().addAll(allGames);
                if (portsDeleted > 0) {
                    Utilities.showInformationAlert("SUCCESS", "        ---------------UPDATE SUCCESSFUL-------------\n\n ---> SUCCESSFULLY UPDATED TO ------->\n"+ selectedGame+"\n Ports deleted: " + portsDeleted);
                } else
                    Utilities.showInformationAlert("SUCCESS", "        ---------UPDATE SUCCESSFUL--------\n\n ---> SUCCESSFULLY UPDATED TO ------->\n"+ selectedGame);
            } else
                Utilities.showWarningAlert("ERROR", "PLEASE DO NOT LEAVE ANY FIELDS EMPTY");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A GAME TO UPDATE");
    }
    public void showViewer() {
        Game p = gameEditTable.getSelectionModel().getSelectedItem();
        if (p != null) {

            SystemController.sysControl.showGameDetailsPopup(p);



        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGamesMachineBox.getItems().addAll(allMachines);
        gameEditTable.getItems().addAll(allGames);
        gameEditController = this;

        gNam.setCellValueFactory(new PropertyValueFactory<>("name"));
        mName.setCellValueFactory(new PropertyValueFactory<>("gamesMachineName"));
        gDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        gCover.setCellValueFactory(new PropertyValueFactory<>("cover"));
        gYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        gDev.setCellValueFactory(new PropertyValueFactory<>("developers"));
        gPub.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    }

    public void removeGame() {
        Game selectedGame = gameEditTable.getSelectionModel().getSelectedItem();

        if (selectedGame != null) {
            allGames.remove(selectedGame);

            gameAPI.portGameCombo.getItems().remove(selectedGame);
            GameEditController.gameEditController.gameEditTable.getItems().remove(selectedGame);
            int portsDeleted = 0;

            for (GamePort port : selectedGame.getPorts()) {
                allGamePorts.remove(port);

                PortEditController.portEditController.portEditTable.getItems().remove(port);
                portsDeleted++;
            }

            if (portsDeleted > 0)
                Utilities.showInformationAlert("SUCCESS", selectedGame.getName() +" HAS BEEN REMOVED" + "\n Ports Deleted: " + portsDeleted);
            else
                Utilities.showInformationAlert("SUCCESS", selectedGame.getName() +" HAS BEEN REMOVED");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A GAME TO DELETE");
    }
}
