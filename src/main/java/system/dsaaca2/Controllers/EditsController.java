package system.dsaaca2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Models.GamesMachine;

import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.allMachines;

public class EditsController implements Initializable {
    public static EditsController editsController = new EditsController();

    @FXML
    public TableView<GamesMachine> machineEditTable = new TableView<>();
    @FXML
    public TableColumn<GamesMachine, String> mName;
    @FXML
    public TableColumn<GamesMachine, String> mMan;
    @FXML
    public TableColumn<GamesMachine, String> mDesc;
    @FXML
    public TableColumn<GamesMachine, String> mType;
    @FXML
    public TableColumn<GamesMachine, String> mMedia;
    @FXML
    public TableColumn<GamesMachine, String> mImage;
    @FXML
    public TableColumn<GamesMachine, Integer> mYear;
    @FXML
    public TableColumn<GamesMachine, Double> mPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        machineEditTable.getItems().addAll(allMachines);
        editsController = this;
        mName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mMan.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        mDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        mType.setCellValueFactory(new PropertyValueFactory<>("type"));
        mMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
        mImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        mYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        mPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}




