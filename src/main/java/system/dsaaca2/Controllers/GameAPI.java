package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameAPI implements Initializable {
    public static SillyList<Game> allGames = new SillyList<>();
    private static GameAPI gameAPI;

    //TODO - add fx-ids to all text-fields and import here
    public ListView currentMachinesView;
    public ScrollPane currentGamesView;
    public ScrollPane currentPortsView;

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

