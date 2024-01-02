package system.dsaaca2.Controllers;

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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.Models.Hashable;
import system.dsaaca2.utils.Utilities;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

public class SystemController implements Initializable {
    public static SystemController sysControl = new SystemController();
    public TextField searchMachines;
    public ListView<String> searchResults = new ListView<>();
    public TextField searchGamesAndPorts;
    public ComboBox<String> machineFilter = new ComboBox<>();
    public ComboBox<String> machineSort = new ComboBox<>();
    public ComboBox<String> gameAndPortFilter = new ComboBox<>();
    public ComboBox<String> gameAndPortSort = new ComboBox<>();
    private final SillyList<String> searchResultsList = new SillyList<>();

    @FXML
    public Label portNameLabel = new Label();
    @FXML
    public Label portOrigMachine = new Label();
    @FXML
    public Label portMachineToLabel = new Label();
    @FXML
    public Label portGameLabel = new Label();
    @FXML
    public Label portYearLabel = new Label();
    @FXML
    public Label portDevLabel = new Label();
    @FXML
    public Label portCoverLabel = new Label();
    @FXML
    public ImageView gameImage;
    @FXML
    public Label gameNameLabel = new Label();
    @FXML
    public Label gamePubLabel = new Label();
    @FXML
    public Label gameDevLabel = new Label();
    @FXML
    public Label gameYearLabel = new Label();
    @FXML
    public Label gameDescLabel = new Label();
    @FXML
    public Label gameCoverLabel = new Label();
    @FXML
    public Label gamesPortsLabel = new Label();
    @FXML
    public Label gameMachineLabel = new Label();
    @FXML
    public ImageView machineImage;
    @FXML
    public Label machineNameLabel = new Label();
    @FXML
    public Label machineManLabel = new Label();
    @FXML
    public Label machineTypeLabel = new Label();
    @FXML
    public Label machineMediaLabel = new Label();
    @FXML
    public Label machinePriceLabel = new Label();
    @FXML
    public Label mYearLabel = new Label();
    @FXML
    public Label mImageLabel = new Label();
    @FXML
    public Label mGamesLabel = new Label();
    @FXML
    public Label mPortsLabel = new Label();

    public void switchSceneBack() {
        Main.mainStage.setScene(Main.gameScene);
        searchResults.getItems().clear();
    }

    public void populateSearchFiltersAndSorts() {
        machineFilter.getItems().addAll("Name", "Description", "Year", "Manufacturer", "Type", "Media");
        machineSort.getItems().addAll("Price Lowest ---> Highest", "Alphabetically", "Oldest ---> Newest");
        gameAndPortFilter.getItems().addAll("Game Name", "Games by Publisher", "Games by Description", "Developers", "Games Machine", "Year");
        gameAndPortSort.getItems().addAll("A ---> Z", "Newest ---> Oldest ", "Oldest ---> Newest");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sysControl = this;
        populateSearchFiltersAndSorts();
    }

    public void searchMachines() {
        searchResults.getItems().clear();
        searchResultsList.clear();
        String filterChoice = machineFilter.getSelectionModel().getSelectedItem();
        String search = searchMachines.getText().toLowerCase();
        boolean added = false;
        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("Name".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getName().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Description".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getDescription().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Year".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (String.valueOf(gm.getYear()).startsWith(search)) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Manufacturer".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getManufacturer().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Type".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getType().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
                            added = true;
                        }
                    }
                } else if ("Media".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getMedia().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm.toString());
                            searchResultsList.add(gm.toString());
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
        searchResultsList.clear();
        String filterChoice = gameAndPortFilter.getSelectionModel().getSelectedItem();
        String search = searchGamesAndPorts.getText().toLowerCase();
        boolean added = false;

        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("game name".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getName().toLowerCase().startsWith(search.toLowerCase()) || g.getName().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                        for (GamePort p : g.getPorts()) {
                            if (p.getGameName().toLowerCase().startsWith(search.toLowerCase()) || p.getGameName().toLowerCase().contains(search.toLowerCase())) {
                                searchResults.getItems().add(p.toString());
                                searchResultsList.add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("games by publisher".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getPublisher().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                    }
                } else if ("year".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (String.valueOf(g.getYear()).startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                        for (GamePort p : g.getPorts()) {
                            if (String.valueOf(p.getYear()).startsWith(search)) {
                                searchResults.getItems().add(p.toString());
                                searchResultsList.add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("developers".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDevelopers().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getDevelopers().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            searchResultsList.add(p.toString());
                            added = true;
                        }
                    }
                } else if ("games by description".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDescription().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                    }
                } else if ("games machine".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getGamesMachine().getName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getNewPortName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            searchResultsList.add(p.toString());
                            added = true;
                        }
                    }
                }
                if (!added) {
                    Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
                }
            } else {
                Utilities.showWarningAlert("FILTER NOT SELECTED", "PLEASE SELECT A FILTER");
            }
        } else {
            Utilities.showWarningAlert("EMPTY", "THE SEARCH FIELD IS EMPTY, PLEASE ENTER SOMETHING TO SEARCH");
        }
    }




    public void showPortDetailsPopUp(GamePort selected) {

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

            portNameLabel.setText("NEW PORT FOR:   " + selected.getNewPortName().toUpperCase());
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
            Main.getMainStage().setIconified(true);
            popUp.setOnCloseRequest(event -> Main.getMainStage().setIconified(false));

            popUp.setTitle(selected.getMachinePortedTo().getName().toUpperCase() + " PORT DETAILS");
            popUp.setResizable(false);
            Scene newScene = new Scene(root, 500, 700);
            popUp.setScene(newScene);
            newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
            popUp.show();
        } catch (IOException e) {
            Utilities.showWarningAlert("Error", "Error");
        }
    }

    public void showGameDetailsPopup(Game selected) {

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
            StringBuilder namesText = new StringBuilder();

            for (GamePort gp : selected.getPorts()) {
                namesText.append(gp.getMachinePortedTo().getName().toUpperCase()).append("\n");
            }
            gamesPortsLabel.setText(namesText.toString());

            try {
                Image image = new Image(coverURL);
                gameImage.setImage(image);

            } catch (Exception e) {

                Image defaultImage = new Image("/default.png");
                gameImage.setImage(defaultImage);
            }

            Stage popUp = new Stage();
            popUp.setTitle(selected.getGamesMachineName().toUpperCase() + " GAME DETAILS");
            Main.getMainStage().setIconified(true);
            popUp.setOnCloseRequest(event -> Main.getMainStage().setIconified(false));
            popUp.setResizable(false);
            Scene newScene = new Scene(root, 500, 700);
            popUp.setScene(newScene);
            newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
            popUp.show();
        } catch (IOException e) {
            Utilities.showWarningAlert("Error", "Error");
        }
    }

    public void showMachineDetailsPopUp(GamesMachine selected) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/machineViewer.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.setController(this);

            ImageView machineImage = (ImageView) root.lookup("#machineImage");
            String coverURL = selected.getImage();
            Label machineNameLabel = (Label) root.lookup("#machineNameLabel");
            Label machineManLabel = (Label) root.lookup("#machineManLabel");
            Label machineMediaLabel = (Label) root.lookup("#machineMediaLabel");
            Label machinePriceLabel = (Label) root.lookup("#machinePriceLabel");
            Label mYearLabel = (Label) root.lookup("#mYearLabel");
            Label mImageLabel = (Label) root.lookup("#mImageLabel");
            Label mGamesLabel = (Label) root.lookup("#mGamesLabel");
            Label mPortsLabel = (Label) root.lookup("#mPortsLabel");
            Label machineTypeLabel = (Label) root.lookup("#machineTypeLabel");

            machineTypeLabel.setText(selected.getType().toUpperCase());
            machineNameLabel.setText(selected.getName().toUpperCase());
            machineManLabel.setText(selected.getManufacturer().toUpperCase());
            machineMediaLabel.setText(selected.getMedia());
            machinePriceLabel.setText("€" + selected.getPrice());
            mYearLabel.setText(String.valueOf(selected.getYear()));
            gameDescLabel.setText(selected.getDescription().toUpperCase());
            mImageLabel.setText(selected.getImage().toLowerCase());

            String names="";
            for (GamePort gp : allGamePorts) {
                if (gp.getMachinePortedTo().getName().equals(selected.getName()))
                 names+= gp.getGameName().toUpperCase() +" ( " +gp.getMachinePortedTo().getName().toUpperCase()+" ) , ";
            }
            mPortsLabel.setText(names);
            String gamesText = "";
            for (Game g : selected.getGames()) {
                gamesText+= g.getName().toUpperCase() +" ( " +g.getYear()+" ) , ";
            }
            mGamesLabel.setText(gamesText);
            try {
                Image image = new Image(coverURL);
                machineImage.setImage(image);

            } catch (Exception e) {

                Image defaultImage = new Image("/default.png");
                machineImage.setImage(defaultImage);
            }

            Stage popUp = new Stage();
            Main.getMainStage().setIconified(true);
            popUp.setOnCloseRequest(event -> Main.getMainStage().setIconified(false));
            popUp.setTitle(selected.getName().toUpperCase() + " MACHINE DETAILS");
            popUp.setResizable(false);
            Scene newScene = new Scene(root, 500, 700);
            popUp.setScene(newScene);
            newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
            popUp.show();
        } catch (IOException e) {
            Utilities.showWarningAlert("Error", "Error");
        }
    }

    public void resultsClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            String toDrill = searchResults.getSelectionModel().getSelectedItem();
            Object foundDrill = hashMap.find(toDrill);

            if (foundDrill != null) {
                if (foundDrill instanceof GamesMachine) {
                    if (((GamesMachine) foundDrill).getGames().isEmpty()) {
                        Utilities.showInformationAlert("DRILL-DOWN", "No games available for this machine. Cannot drill down any further.");
                    } else {
                        searchResults.getItems().clear();
                        searchResults.getItems().add("--------------------------------------------------------LIST OF GAMES FOR " + ((GamesMachine) foundDrill).getName().toUpperCase() + "----------------------------------------");
                        for (Game g : ((GamesMachine) foundDrill).getGames()) {
                            searchResults.getItems().add(g.toString());
                        }

                    }
                } else if (foundDrill instanceof Game) {
                    if (((Game) foundDrill).getPorts().isEmpty()) {
                        Utilities.showInformationAlert("DRILL-DOWN", "This game does not have any ports to drill further.");
                    } else {
                        for (GamePort p : ((Game) foundDrill).getPorts()) {
                            searchResults.getItems().clear();
                            searchResults.getItems().add("--------------------------------------  LIST OF GAME PORTS FOR " + ((Game) foundDrill).getName().toUpperCase() + "  --------------------------------------------");
                            searchResults.getItems().add(p.toString());
                        }
                    }

                } else if (foundDrill instanceof GamePort) {
                    Utilities.showInformationAlert("DRILL-DOWN", "Cannot drill down any further");
                }
            }
        }
    }




    public void sortResults() {
        String sort = machineSort.getSelectionModel().getSelectedItem();
        SillyList<Hashable> results = new SillyList<>();

        for (String s : searchResultsList) {
            results.add(hashMap.find(s));
        }

        if (sort != null) {
            if ("Oldest ---> Newest".equalsIgnoreCase(sort)) {
                for (int e = 1; e < results.size(); e++) {
                    int elem;

                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getYear();
                    } else if (results.get(e) instanceof Game) {
                        elem = ((Game) results.get(e)).getYear();
                    } else {
                        elem = ((GamePort) results.get(e)).getYear();
                    }

                    int i;
                    for (i = e; i >= 1; i--) {
                        int eye;

                        if (results.get(i - 1) instanceof GamesMachine) {
                            eye = ((GamesMachine) results.get(i - 1)).getYear();
                        } else if (results.get(i - 1) instanceof Game) {
                            eye = ((Game) results.get(i - 1)).getYear();
                        } else {
                            eye = ((GamePort) results.get(i - 1)).getYear();
                        }

                        if (eye <= elem)
                            break;
                        else
                            results.swapIndex(i, i - 1);
                    }
                }
            } else if ("Alphabetically".equalsIgnoreCase(sort)) {
                for (int e = 1; e < results.size(); e++) {
                    String elem;

                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getName();
                    } else if (results.get(e) instanceof Game) {
                        elem = ((Game) results.get(e)).getName();
                    } else {
                        elem = ((GamePort) results.get(e)).getGameName();
                    }

                    int i;
                    for (i = e; i >= 1; i--) {
                        String eye;

                        if (results.get(i - 1) instanceof GamesMachine) {
                            eye = ((GamesMachine) results.get(i - 1)).getName();
                        } else if (results.get(i - 1) instanceof Game) {
                            eye = ((Game) results.get(i - 1)).getName();
                        } else {
                            eye = ((GamePort) results.get(i - 1)).getGameName();
                        }

                        if (eye.compareToIgnoreCase(elem) < 1)
                            break;
                        else
                            results.swapIndex(i, i - 1);
                    }
                }
            } else {
                for (int e = 1; e < results.size(); e++) {
                    double elem;

                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getPrice();
                    } else if (results.get(e) instanceof Game) {
                        Utilities.showWarningAlert("ERR", "UNABLE TO SORT GAMES AND PORTS AS THEY HAVE NO PRICE");
                        break;
                    } else {
                        Utilities.showWarningAlert("ERR", "UNABLE TO SORT GAMES AND PORTS AS THEY HAVE NO PRICE");
                        break;
                    }

                    int i;
                    for (i = e; i >= 1; i--) {
                        double eye = ((GamesMachine) results.get(i - 1)).getPrice();

                        if (eye <= elem)
                            break;
                        else
                            results.swapIndex(i, i - 1);
                    }
                }
            }

            searchResults.getItems().clear();
            for (Hashable h : results) {
                searchResults.getItems().add(h.toString());
            }
        } else
            Utilities.showWarningAlert("ERR", "SELECT A WAY TO SORT");
    }

    public void selectForDetails() {

        Object found;
        String selected = searchResults.getSelectionModel().getSelectedItem();
        if (selected != null && !selected.isEmpty()) {
            found = hashMap.find(selected);

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
    public void edit() throws IOException {
        String selected = searchResults.getSelectionModel().getSelectedItem();
        Object found;

        if (selected != null) {
            found = hashMap.find(selected);
            if (found instanceof Game) {
                 gameAPI.editGames();
                 GameEditController.gameEditController.gameEditTable.getSelectionModel().select((Game) found);
            } else if (found instanceof GamesMachine) {
                gameAPI.editMachine();
                MachineEditController.machineEditController.machineEditTable.getSelectionModel().select((GamesMachine) found);
            } else if (found instanceof GamePort) {
                gameAPI.editPorts();
                PortEditController.portEditController.portEditTable.getSelectionModel().select((GamePort) found);
            } else {
                Utilities.showWarningAlert("ERR", "Invalid selection");
            }
        } else {
            Utilities.showWarningAlert("ERR", "SELECT A RESULT");
        }
   }
}






