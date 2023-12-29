package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamesMachine;

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

    public void onGameSelect(MouseEvent mouseEvent) {
        Game selectedGame = gameEditTable.getSelectionModel().getSelectedItem();
        int selectedIndex = gameEditTable.getSelectionModel().getSelectedIndex();

        if (selectedGame != null) {
            // Update text fields with the values of the selected machine

            updateGameName.setText(selectedGame.getName());
            updateGamePub.setText(selectedGame.getPublisher());
            updateGameCover.setText(selectedGame.getCover());
            updateGameDesc.setText(selectedGame.getDescription());
            updateGameYear.setText(String.valueOf(selectedGame.getYear()));
            updateGameDev.setText(selectedGame.getDevelopers());
            updateGamesMachineBox.getItems().set(selectedIndex,selectedGame.getGamesMachine());

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
        gpub.setCellValueFactory(new PropertyValueFactory<>("publisher"));

    }

    public static GameEditController getGameEditController() {
     return null;
    }
}
