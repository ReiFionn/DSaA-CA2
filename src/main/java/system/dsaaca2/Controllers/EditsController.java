package system.dsaaca2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

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

    /*----UPDATE TEXT FIELDS-----*/
    public TextField updateMachineName = new TextField();
    public TextField updateMachineMan = new TextField();
    public TextField updateMachineDesc = new TextField();
    public TextField updateMachineType = new TextField();
    public TextField updateMachinePrice = new TextField();
    public TextField updateMachineYear = new TextField();
    public TextField updateMachineImage = new TextField();
    public TextField updateMachineMedia = new TextField();

    /*----------------------------*/
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
    private void refreshAllViews() {
        gameAPI.currentGamesView.getItems().clear();
        gameAPI.currentGamesView.getItems().addAll(allGames);

        gameAPI.portGameCombo.getItems().clear();
        gameAPI.portGameCombo.getItems().addAll(allGames);

        gameAPI.currentPortsView.getItems().clear();
        gameAPI.currentPortsView.getItems().addAll(allGamePorts);
    }


    private GamesMachine originalMachineCopy(GamesMachine selectedMachine) {
        return new GamesMachine(
                selectedMachine.getName(),
                selectedMachine.getManufacturer(),
                selectedMachine.getDescription(),
                selectedMachine.getType(),
                selectedMachine.getMedia(),
                selectedMachine.getImage(),
                selectedMachine.getYear(),
                selectedMachine.getPrice()
        );
    }

    public void onMachineSelect() {
        GamesMachine selectedMachine = machineEditTable.getSelectionModel().getSelectedItem();


        // Update text fields with the values of the selected machine
        updateMachineName.setText(String.valueOf(selectedMachine.getName()));
        updateMachineMan.setText(String.valueOf(selectedMachine.getManufacturer()));
        updateMachineDesc.setText(String.valueOf(selectedMachine.getDescription()));
        updateMachineType.setText(String.valueOf(selectedMachine.getType()));
        updateMachinePrice.setText(String.valueOf(selectedMachine.getPrice()));
        updateMachineYear.setText("CAN NOT BE CHANGED");
        updateMachineImage.setText(String.valueOf(selectedMachine.getImage()));
        updateMachineMedia.setText(String.valueOf(selectedMachine.getMedia()));
    }

    public void applyMachineUpdate() {

        GamesMachine originalMachine = machineEditTable.getSelectionModel().getSelectedItem();
        System.out.println("Original Machine: " + originalMachine);



        if (originalMachine != null) {
            GamesMachine updatedMachine = originalMachineCopy(originalMachine);
            // Get the updated values from the text fields in the edit popup
            String updatedName = updateMachineName.getText();
            String updatedManufacturer = updateMachineMan.getText();
            String updatedDescription = updateMachineDesc.getText();
            String updatedType = updateMachineType.getText();
            String updatedMedia = updateMachineMedia.getText();
            String updatedImage = updateMachineImage.getText();

            double updatedPrice;
            if (updateMachinePrice.getText().isEmpty()) {
                updatedPrice = originalMachine.getPrice(); // Retain the previous value if no entry
            } else {
                try {
                    updatedPrice = Double.parseDouble(updateMachinePrice.getText());
                } catch (NumberFormatException e) {
                    Utilities.showWarningAlert("ERROR", "PLEASE USE NUMERICAL VALUES ONLY FOR PRICE FIELD.");
                    return;
                }
            }



            allMachines.remove(originalMachine);
            machineEditTable.getItems().remove(originalMachine);
            gameAPI.currentMachinesView.getItems().remove(originalMachine);
            gameAPI.gameMachineCombo.getItems().remove(originalMachine);
            gameAPI.portMachineCombo.getItems().remove(originalMachine);


            updatedMachine.setName(updatedName);
            updatedMachine.setManufacturer(updatedManufacturer);
            updatedMachine.setDescription(updatedDescription);
            updatedMachine.setType(updatedType);
            updatedMachine.setMedia(updatedMedia);
            updatedMachine.setImage(updatedImage);
            updatedMachine.setPrice(updatedPrice);

       if(!allGames.isEmpty()) {
         for (Game g : allGames) {
        if (originalMachine.equals(g.getGamesMachine())) {
            g.setGamesMachine(updatedMachine);
            System.out.println("hi"); /*It is not entering this loop for some reason*/
        }

        for (GamePort gp : g.getPorts()) {
            if (originalMachine.equals(gp.getMachinePortedTo())) {
                gp.setMachinePortedTo(updatedMachine);
            }
            if (originalMachine.equals(gp.getMachinePortedTo())) {
                System.out.println("Updating port - OriginalMachine: " + gp.getOriginalGame().getName());
                gp.setOriginalMachine(updatedMachine);
            }
        }
    }
}


            refreshAllViews();

            allMachines.add(updatedMachine);
            machineEditTable.getItems().add(updatedMachine);
            gameAPI.currentMachinesView.getItems().add(updatedMachine);
            gameAPI.gameMachineCombo.getItems().add(updatedMachine);
            gameAPI.portMachineCombo.getItems().add(updatedMachine);

            Utilities.showInformationAlert("SUCCESS", "UPDATE SUCCESSFUL\nMACHINE: " + originalMachine +
                    "\nUPDATED TO ---->: \n" + updatedMachine);
        } else {
            Utilities.showWarningAlert("ERROR", "PLEASE SELECT A MACHINE TO UPDATE");
        }
    }
}


