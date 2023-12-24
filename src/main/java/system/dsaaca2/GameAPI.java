package system.dsaaca2;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameAPI implements Initializable {
    public static SillyList<Game> allGames = new SillyList<>();
    private static GameAPI gameAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameAPI = this;
    }
}
