package system.dsaaca2;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePortAPI implements Initializable {
    public static SillyList<GamePort> allGamePorts = new SillyList<>();
    private static GamePortAPI gamePortAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePortAPI = this;
    }
}
