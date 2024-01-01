package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.utils.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

public class SystemController implements Initializable {
    public static SystemController sysControll = new SystemController();
    public TextField searchMachines;
    public ListView<String> searchResults = new ListView<>();
    public TextField searchGamesAndPorts;
    public ComboBox<String> machineFilter = new ComboBox<>();
    public ComboBox<String> machineSort = new ComboBox<>();
    public ComboBox<String> gameAndPortFilter = new ComboBox<>();
    public ComboBox<String> gameAndPortSort = new ComboBox<>();

    /*Port viewer fx-ids----------*/
    @FXML
    private ImageView portImage;

    @FXML
    public Label portNameLabel = new Label();

    @FXML
    public Label portOrigMachine= new Label();

    @FXML
    public Label portMachineToLabel= new Label();

    @FXML
    public Label portGameLabel= new Label();

    @FXML
    public Label portYearLabel= new Label();

    @FXML
    public Label portDevLabel= new Label();

    @FXML
    public Label portCoverLabel= new Label();

    /*------------------------------*/
  /*GAME VIEWER FX-IDS-------------*/
    @FXML
    public ImageView gameImage;

    @FXML
    public Label gameNameLabel= new Label();

    @FXML
    public Label gamePubLabel= new Label();

    @FXML
    public Label gameDevLabel= new Label();

    @FXML
    public Label gameYearLabel= new Label();

    @FXML
    public Label gameDescLabel= new Label();

    @FXML
    public Label gameCoverLabel= new Label();

    @FXML
    public Label gamesPortsLabel= new Label();
    @FXML
    public Label gameMachineLabel= new Label();
    /*------------------------------*/

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void populateSearchFiltersAndSorts() {
        /*Adds filtering/sorting options*/
        machineFilter.getItems().addAll("Name", "Description", "Year", "Manufacturer", "Type", "Media");
        machineSort.getItems().addAll("Price Highest ---> Lowest ", "Price Lowest ---> Highest", "Oldest ---> Newest");
        gameAndPortFilter.getItems().addAll("Game Name", "Games by Publisher", "Games by Description", "Developers", "Games Machine", "Year");
        gameAndPortSort.getItems().addAll("A ---> Z", "Newest ---> Oldest ", "Oldest ---> Newest");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sysControll = this;
        populateSearchFiltersAndSorts();
    }

    /*A list that stores found results from searches and is then added to the listview
     * for the purpose of sorting later on (maybe)*/

    SillyList<GamesMachine> machineSearchResults = new SillyList<>();


    public void searchMachines() {
        searchResults.getItems().clear();
        machineSearchResults.clear();
        String filterChoice = machineFilter.getSelectionModel().getSelectedItem();
        String search = searchMachines.getText().toLowerCase();
        boolean added = false;
        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("Name".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getName().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Description".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getDescription().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Year".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (String.valueOf(gm.getYear()).startsWith(search)) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Manufacturer".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getManufacturer().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Type".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getType().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Media".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getMedia().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            added = true;
                        }
                    }
                }
                if (!added)
                    Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
            } else
                Utilities.showWarningAlert("OOPS", "PICK A FILTER PLEASE");
        } else
            Utilities.showWarningAlert("OOPS", "ENTER SOMETHING TO SEARCH FOR");
    }

    public void searchGamesAndPorts() {
        searchResults.getItems().clear();

        String filterChoice = gameAndPortFilter.getSelectionModel().getSelectedItem();
        String search = searchGamesAndPorts.getText().toLowerCase();
        boolean added = false;

        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("game name".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getName().toLowerCase().startsWith(search.toLowerCase())|| g.getName().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                        /*all ports made for that machine*/
                        for (GamePort p : g.getPorts()) {
                            if (p.getGameName().toLowerCase().startsWith(search.toLowerCase())|| p.getGameName().toLowerCase().contains(search.toLowerCase())) {
                                searchResults.getItems().add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("games by publisher".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getPublisher().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                } else if ("year".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (String.valueOf(g.getYear()).startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                        for (GamePort p : g.getPorts()) {
                            if (String.valueOf(p.getYear()).startsWith(search)) {
                                searchResults.getItems().add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("developers".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDevelopers().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getDevelopers().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            added = true;
                        }
                    }
                } else if ("games by description".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDescription().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                } else if ("games machine".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getGamesMachine().getName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getNewPortName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            added = false;
                        }
                    }
                }
                if (!added)
                    Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
            } else
                Utilities.showWarningAlert("FILTER NOT SELECTED", "PLEASE SELECT A FILTER");
        } else
            Utilities.showWarningAlert("EMPTY", "THE SEARCH FIELD IS EMPTY, PLEASE ENTER SOMETHING TO SEARCH");
    }

    public void drillDownGameMachine(){
                return;
                 /* Going to have it that when u double click drill down will happen
                 * Three different hashtables will be used here
                 In a instance of Game machine it will drill down once to games
                 * in an instance of Game it will drill Once to ports*/


    }

    public void selectForDetails() throws IOException {
        String selected = searchResults.getSelectionModel().getSelectedItem();
        Object found;

        if (selected != null && !selected.isEmpty()) {
            found = gameAPI.hashMap.find(selected);

            if (found instanceof Game) {
                showGameDetailsPopup((Game) found);
            } else if (found instanceof GamesMachine) {
                showMachineDetailsPopUp((GamesMachine) found);
            } else if (found instanceof GamePort) {
                showPortDetailsPopUp((GamePort) found);
            } else {
                Utilities.showWarningAlert("ERR", "ERR");
            }
        } else {
            Utilities.showWarningAlert("ERR", "SELECT A RESULT");
        }
    }
    public void showPortDetailsPopUp(GamePort selected) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/portViewer.fxml"));
            Parent root = fxmlLoader.load();

            ImageView portImageView = (ImageView) root.lookup("#portImage");
            String coverURL = selected.getCover();
            Label portNameLabel = (Label) root.lookup("#portNameLabel");
            Label portOrigMachine = (Label) root.lookup("#portOrigMachine");
            Label portMachineToLabel = (Label) root.lookup("#portMachineToLabel");
            Label portGameLabel = (Label) root.lookup("#portGameLabel");
            Label portYearLabel = (Label) root.lookup("#portYearLabel");
            Label portDevLabel = (Label) root.lookup("#portDevLabel");
            Label portCoverLabel = (Label) root.lookup("#portCoverLabel");

            portNameLabel.setText("NEW PORT FOR:   "+selected.getNewPortName().toUpperCase());
            portGameLabel.setText(selected.getOriginalGame().getName().toUpperCase() + "  Released: " + selected.getOriginalGame().getYear());
            portMachineToLabel.setText(selected.getMachinePortedTo().getName().toUpperCase());
            portOrigMachine.setText(selected.getOriginalMachine().getName().toUpperCase());
            portYearLabel.setText(String.valueOf(selected.getYear()));
            portCoverLabel.setText(selected.getCover());
            portDevLabel.setText(selected.getDevelopers());

            try {
                Image image = new Image(coverURL);
                portImageView.setImage(image);

            } catch (Exception e) {

                Image defaultImage = new Image("/default.png");
                portImageView.setImage(defaultImage);
            }


            Stage popUp = new Stage();
            popUp.setTitle(selected.getMachinePortedTo().getName().toUpperCase() + " PORT DETAILS");
            popUp.setResizable(false);
            Scene newScene = new Scene(root, 500, 700);
            popUp.setScene(newScene);
            newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
            popUp.show();
        } catch (IOException e) {
            Utilities.showWarningAlert("Error","Error");
        }
    }


    public void showGameDetailsPopup(Game selected) throws IOException {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gameViewer.fxml"));
            Parent root = fxmlLoader.load();


            ImageView gameImage = (ImageView) root.lookup("#gameImage");
            String coverURL = selected.getCover();
            Label gameNameLabel = (Label) root.lookup("#gameNameLabel");
            Label gamePubLabel = (Label) root.lookup("#gamePubLabel");
            Label gameDevLabel = (Label) root.lookup("#gameDevLabel");
            Label gameMachineLabel = (Label) root.lookup("#gameMachineLabel");
            Label gameYearLabel = (Label) root.lookup("#gameYearLabel");
            Label gameDescLabel = (Label) root.lookup("#gameDescLabel");
            Label gameCoverLabel = (Label) root.lookup("#gameCoverLabel");
            Label gamesPortsLabel = (Label) root.lookup("#gamesPortsLabel");

            gameNameLabel.setText(selected.getName().toUpperCase());
            gamePubLabel.setText(selected.getPublisher().toUpperCase());
            gameDevLabel.setText(selected.getDevelopers());
            gameMachineLabel.setText(selected.getGamesMachineName().toUpperCase());
            gameYearLabel.setText(String.valueOf(selected.getYear()));
            gameDescLabel.setText(selected.getDescription().toUpperCase());
            gameCoverLabel.setText(selected.getCover().toLowerCase());
            String namesText = "";

            for (GamePort gp : selected.getPorts()) {
                namesText += gp.getMachinePortedTo().getName().toUpperCase() + "\n";
            }
            gamesPortsLabel.setText(namesText);

            try {
                Image image = new Image(coverURL);
                gameImage.setImage(image);

            } catch (Exception e) {

                Image defaultImage = new Image("/default.png");
                gameImage.setImage(defaultImage);
            }


            Stage popUp = new Stage();
            popUp.setTitle(selected.getGamesMachineName().toUpperCase() + " GAME DETAILS");
            popUp.setResizable(false);
            Scene newScene = new Scene(root, 500, 700);
            popUp.setScene(newScene);
            newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
            popUp.show();
        } catch (IOException e) {
            Utilities.showWarningAlert("Error","Error");
        }
    }

    public void showMachineDetailsPopUp(GamesMachine selected) throws IOException {

        Main.viewPopup("/machineViewer.fxml", selected.getName().toUpperCase() + " DETAILS");
    }







}









