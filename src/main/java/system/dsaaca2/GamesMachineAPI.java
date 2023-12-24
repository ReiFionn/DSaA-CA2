package system.dsaaca2;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GamesMachineAPI implements Initializable {
    public static SillyList<GamesMachine> allGamesMachines = new SillyList<>();
    private static GamesMachineAPI gamesMachineAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamesMachineAPI = this;
    }
}
