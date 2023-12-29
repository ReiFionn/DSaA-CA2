package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;

import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.allGamePorts;
import static system.dsaaca2.Controllers.GameAPI.allMachines;

public class PortEditController implements Initializable {
    public static PortEditController portEditController = new PortEditController();

    public TableView<GamePort> portEditTable;
    public TableColumn<GamesMachine,String> newPort;
    public TableColumn<GamesMachine,String> origMachine;
    public TableColumn<Game,String> pGame;
    public TableColumn<GamePort,String> pDev;
    public TableColumn<GamePort,String>pCover;
    public TableColumn<GamePort, Integer> pYear;

    public TextField currentPortGame;
    public TextField updatePortDev;
    public TextField updatePortCover;
    public TextField updatePortYear;
    public TextField portOrigMac;
    public TextField portNewPortName;



    public ComboBox<GamesMachine> newMachineBox;


    public void onGamePortSelect(MouseEvent mouseEvent) {
        GamePort selectedPort = portEditTable.getSelectionModel().getSelectedItem();


        if (selectedPort != null) {
            // Update text fields with the values of the selected machine

            currentPortGame.setText(selectedPort.getOriginalGame().getName());
            portOrigMac.setText(selectedPort.getOriginalMachine().getName());
            portNewPortName.setText(selectedPort.getMachinePortedTo().getName());
            updatePortDev.setText(selectedPort.getDevelopers());
            updatePortYear.setText(String.valueOf(selectedPort.getPortYear()));
            updatePortCover.setText(selectedPort.getCover());


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        portEditController = this;
        portEditTable.getItems().addAll(allGamePorts);
        newMachineBox.getItems().addAll(allMachines);

    }
}

