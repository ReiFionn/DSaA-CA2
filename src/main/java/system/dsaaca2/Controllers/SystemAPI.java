package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.GamePort;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemAPI implements Initializable {
    public static SillyList<GamePort> allGamePorts = new SillyList<>();
    private static SystemAPI systemAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemAPI = this;
    }

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }
}
/*THIS WAS CHANGED TO SYSTEM API BECAUSE GAME-API MANAGES ADD/DELETE/EDIT FOR ALL MODELS
 THE SECOND VIEW SYSTEM WILL HAVE FUNCTIONALITY STUFF
* LIKE VIEW SYSTEM (DRILL-DOWNS) BASED ON WHAT U SELECT, SEARCH/SORT SYSTEM STUFF, SAVE AND LOAD STUFF ETC
JUST AS A WAY TO KEEP THINGS SIMPLE WITH TWO SCENES :D*/