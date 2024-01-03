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
    private final SillyList<String> searchResultsList = new SillyList<>();

    /*------------JavaFx Ids----------------------------------*/
    public TextField searchMachines;
    public ListView<String> searchResults = new ListView<>();
    public TextField searchGamesAndPorts;
    public ComboBox<String> machineFilter = new ComboBox<>();
    public ComboBox<String> machineSort = new ComboBox<>();
    public ComboBox<String> gameAndPortFilter = new ComboBox<>();
    public ComboBox<String> gameAndPortSort = new ComboBox<>();
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
    public Label gameNameLabel= new Label();
    public Label gamePubLabel= new Label();
    public Label gameDevLabel= new Label();
    public Label gameYearLabel= new Label();
    public Label gameDescLabel= new Label();
    public Label gameCoverLabel= new Label();
    public Label gamesPortsLabel= new Label();
    public Label gameMachineLabel= new Label();
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
    /*------------------------------------------------------*/

    /**
     * Switches the scene back to the main game scene and clears the search results.
     */
    public void switchSceneBack() {
        Main.mainStage.setScene(Main.gameScene);
        searchResults.getItems().clear();
    }

    /**
     * Populates the search filters and sorts for machines, games, and ports in JavaFx.
     */
    public void populateSearchFiltersAndSorts() {
        machineFilter.getItems().addAll("Name", "Description", "Year", "Manufacturer", "Type", "Media");
        machineSort.getItems().addAll("Price Lowest ---> Highest", "Alphabetically", "Oldest ---> Newest");
        gameAndPortFilter.getItems().addAll("Game Name", "Games by Publisher", "Games by Description", "Developers", "Games Machine", "Year");
        gameAndPortSort.getItems().addAll("A ---> Z", "Newest ---> Oldest ", "Oldest ---> Newest");
    }

    @Override
    /* Populates choices on scene initialisation*/ public void initialize(URL url, ResourceBundle resourceBundle) {
        sysControl = this;
        populateSearchFiltersAndSorts();
    }

    /**
     * Performs a search for machines based on the selected filter and user input.
     */
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
                if (!added) Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
            } else Utilities.showWarningAlert("OOPS", "PICK A FILTER PLEASE");
        } else Utilities.showWarningAlert("OOPS", "ENTER SOMETHING TO SEconARCH FOR");
    }

    /**
     * Performs a search for games and ports based on the selected filter and user input.
     */
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

    /**
     * Displays a pop-up displaying details for the selected game port with its image.
     *
     * @param selected The selected game port.
     */
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

    /**
     * Displays a pop-up with details for the selected game with its image.
     *
     * @param selected The selected game.
     */
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

              StringBuilder names= new StringBuilder();
            for (GamePort gp : selected.getPorts()) {
                names.append("( ").append(gp.getMachinePortedTo().getName().toUpperCase()).append(" ) , ");
            }
            gamesPortsLabel.setText(names.toString());

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
            Utilities.showWarningAlert("Error", "Error");
        }
    }

    /**
     * Displays a pop-up with details for the selected games machine with its image.
     *
     * @param selected The selected games machine.
     */
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

            StringBuilder names = new StringBuilder();
            for (GamePort gp : allGamePorts) {
                if (gp.getMachinePortedTo().getName().equals(selected.getName()))
                    names.append(gp.getGameName().toUpperCase()).append(" ( ").append(gp.getMachinePortedTo().getName().toUpperCase()).append(" ) , ");
            }
            mPortsLabel.setText(names.toString());
            StringBuilder gamesText = new StringBuilder();
            for (Game g : selected.getGames()) {
                gamesText.append(g.getName().toUpperCase()).append(" ( ").append(g.getYear()).append(" ) , ");
            }
            mGamesLabel.setText(gamesText.toString());
            try {
                Image image = new Image(coverURL);
                machineImage.setImage(image);

            } catch (Exception e) {

                Image defaultImage = new Image("/default.png");
                machineImage.setImage(defaultImage);
            }

            Stage popUp = new Stage();
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

    /**
     * Triggered once a double click is registered on the search results and drills down based on the selected item.
     * Hashing is used here to efficiently find and retrieve specific data based on the instance selected.
     * Alerts the user if an instance can not be further expanded.
     * @param event The mouse event.
     */
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
                        searchResults.getItems().clear();
                        searchResults.getItems().add("--------------------------------------  LIST OF GAME PORTS FOR " + ((Game) foundDrill).getName().toUpperCase() + "  --------------------------------------------");
                        for (GamePort p : ((Game) foundDrill).getPorts()) {
                            searchResults.getItems().add(p.toString());
                        }
                    }

                } else if (foundDrill instanceof GamePort) {
                    Utilities.showInformationAlert("DRILL-DOWN", "Cannot drill down any further");
                }
            }
        }
    }

    /**
     * Sorts the search results using Insertion Sort based on the Object instance of the selected sorting criteria.
     * Hashing is used here for effective search and retrieval of results to sort
     */
    public void sortResults() {
        /* Get the selected sorting option from the combo box. */
        String sort = machineSort.getSelectionModel().getSelectedItem();

        /* Create a list to store the results after sorting. */
        SillyList<Hashable> results = new SillyList<>();

        /* Populate the results list with the Hashable objects corresponding to the search results. */
        for (String s : searchResultsList) {
            results.add(hashMap.find(s));
        }

        /* Make sure an option is chosen. */
        if (sort != null) {
            /* Sort based on Old -> New. */
            if ("Oldest ---> Newest".equalsIgnoreCase(sort)) {
                /* Iterate through each element starting from the second one. */
                for (int e = 1; e < results.size(); e++) {
                    /* Extract the year value of the current element. */
                    int elem;
                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getYear();
                    } else if (results.get(e) instanceof Game) {
                        elem = ((Game) results.get(e)).getYear();
                    } else {
                        elem = ((GamePort) results.get(e)).getYear();
                    }

                    /* Insert the current element at the correct position in the sorted order. */
                    int i;
                    for (i = e; i >= 1; i--) {
                        int eye;
                        /* Extract the year value of the element at index (i-1). */
                        if (results.get(i - 1) instanceof GamesMachine) {
                            eye = ((GamesMachine) results.get(i - 1)).getYear();
                        } else if (results.get(i - 1) instanceof Game) {
                            eye = ((Game) results.get(i - 1)).getYear();
                        } else {
                            eye = ((GamePort) results.get(i - 1)).getYear();
                        }

                        /* Compare and swap if necessary to maintain sorted order. */
                        if (eye <= elem) break;
                        else results.swapIndex(i, i - 1);
                    }
                }
            }
            /* Sort based on A-Z. */
            else if ("Alphabetically".equalsIgnoreCase(sort)) {
                /* Iterate through each element starting from the second one. */
                for (int e = 1; e < results.size(); e++) {
                    /* Extract the name value of the current element. */
                    String elem;
                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getName();
                    } else if (results.get(e) instanceof Game) {
                        elem = ((Game) results.get(e)).getName();
                    } else {
                        elem = ((GamePort) results.get(e)).getGameName();
                    }

                    /* Insert the current element at the correct position in the sorted order. */
                    int i;
                    for (i = e; i >= 1; i--) {
                        String eye;
                        /* Extract the name value of the element at index (i-1). */
                        if (results.get(i - 1) instanceof GamesMachine) {
                            eye = ((GamesMachine) results.get(i - 1)).getName();
                        } else if (results.get(i - 1) instanceof Game) {
                            eye = ((Game) results.get(i - 1)).getName();
                        } else {
                            eye = ((GamePort) results.get(i - 1)).getGameName();
                        }

                        /* Compare and swap if necessary */
                        if (eye.compareToIgnoreCase(elem) < 1) break;
                        else results.swapIndex(i, i - 1);
                    }
                }
            }
            /* Sort based on Price Low -> High. */
            else {
                /* Iterate through each element starting from the second one. */
                for (int e = 1; e < results.size(); e++) {
                    /* Extract the price value of the current element. */
                    double elem;
                    if (results.get(e) instanceof GamesMachine) {
                        elem = ((GamesMachine) results.get(e)).getPrice();
                    } else {
                        /* Display a warning alert if trying to sort games and ports without a price. */
                        Utilities.showWarningAlert("ERR", "UNABLE TO SORT GAMES AND PORTS AS THEY HAVE NO PRICE");
                        break;
                    }

                    /* Insert the current element at the correct position in the sorted order. */
                    int i;
                    for (i = e; i >= 1; i--) {
                        double eye = ((GamesMachine) results.get(i - 1)).getPrice();

                        /* Compare and swap if necessary. */
                        if (eye <= elem) break;
                        else results.swapIndex(i, i - 1);
                    }
                }
            }

            /* Clear the search results in the Listview. */
            searchResults.getItems().clear();

            /* Repopulate the ListView with the sorted results. */
            for (Hashable h : results) {
                searchResults.getItems().add(h.toString());
            }
        } else {
            /* Display a warning alert if no sorting option is selected. */
            Utilities.showWarningAlert("ERR", "SELECT A WAY TO SORT");
        }
    }

    /**
     * Opens a pop-up window displaying detailed information about the selected result Object
     * based on the instance selected.
     */
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

    /**
     * Edits the selected item from the search results.
     * The method identifies the type of the selected item (Game, GamesMachine, or GamePort)
     * and invokes the corresponding edit method from the gameAPI.
     * It also selects the found object in the respective controller's TableView.
     *
     * @throws IOException If an input or output exception occurs.
     */
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






