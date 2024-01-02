package system.dsaaca2.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;
import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

public class PortEditController implements Initializable {
    public static PortEditController portEditController = new PortEditController();
    public TableView<GamePort> portEditTable= new TableView<>();
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

    public void onGamePortSelect() {
        GamePort selectedPort = portEditTable.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            currentPortGame.setText(selectedPort.getOriginalGame().getName());
            portOrigMac.setText(selectedPort.getOriginalMachine().getName());
            portNewPortName.setText(selectedPort.getMachinePortedTo().getName());
            updatePortDev.setText(selectedPort.getDevelopers());
            updatePortYear.setText(String.valueOf(selectedPort.getYear()));
            updatePortCover.setText(selectedPort.getCover());
        }
    }

    public void removePort() {
        GamePort selectedPort = portEditTable.getSelectionModel().getSelectedItem();

        if (selectedPort != null) {
            allGamePorts.remove(selectedPort);

            portEditTable.getItems().remove(selectedPort);
            selectedPort.getOriginalGame().removePort(selectedPort);
        }
        Utilities.showInformationAlert("SUCCESS!", "SUCCESSFULLY DELETED:\n"+selectedPort);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        portEditController = this;
        portEditTable.getItems().addAll(allGamePorts);
        newMachineBox.getItems().addAll(allMachines);
        newPort.setCellValueFactory(new PropertyValueFactory<>("machinePortedTo"));
        origMachine.setCellValueFactory(new PropertyValueFactory<>("originalMachine"));
        pDev.setCellValueFactory(new PropertyValueFactory<>("developers"));
        pGame.setCellValueFactory(new PropertyValueFactory<>("originalGame"));
        pCover.setCellValueFactory(new PropertyValueFactory<>("cover"));
        pYear.setCellValueFactory(new PropertyValueFactory<>("year"));
    }
}

