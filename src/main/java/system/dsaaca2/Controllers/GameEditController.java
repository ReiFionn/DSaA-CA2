package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.allGames;
import static system.dsaaca2.Controllers.GameAPI.allMachines;

public class GameEditController implements Initializable {




    public static GameEditController gameEditController = new GameEditController();
    public TableView<Game> gameEditTable;
    public TableColumn<GamesMachine, String> mName;
    public TableColumn<Game, String> gNam;
    public TableColumn<Game, String> gpub;
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
    public ChoiceBox<GamesMachine> updateGamesMachineBox;

    public void onGameSelect() {
        Game selectedGame = gameEditTable.getSelectionModel().getSelectedItem();

        if (selectedGame != null) {
            // Update text fields with the values of the selected machine
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
                    selectedGame.setYear(Integer.parseInt(updateGameYear.getText())); /* Parsing the string value input to convert to a numerical value */
                    if (!Utilities.intValidRange(selectedGame.getYear(), 1900, 2024)) {
                        Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID YEAR BETWEEN 1900-2023");
                        return;
                    }
                } catch (NumberFormatException e) {
                    Utilities.showWarningAlert("ERROR", "PLEASE ENTER A VALID NUMERICAL YEAR");
                    return;
                }

                EditsController.getEditsController().refreshAllViews();
                gameEditTable.getItems().clear();
                gameEditTable.getItems().addAll(allGames);
                Utilities.showInformationAlert("SUCCESS", "UPDATE SUCCESSFUL!");
            } else
                Utilities.showWarningAlert("ERROR", "PLEASE DO NOT LEAVE ANY FIELDS EMPTY");
        } else
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A GAME TO UPDATE");
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
        gpub.setCellValueFactory(new PropertyValueFactory<>("publisher"));

    }

    public static GameEditController getGameEditController() {
     return null;
    }
}
