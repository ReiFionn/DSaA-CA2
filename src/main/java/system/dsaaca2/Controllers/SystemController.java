package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import system.dsaaca2.Datastructures.HashMap;
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
    public ImageView imageViewer;
    public ImageView gameImage;
    public Label gameName = new Label();
    public Label gameYearLabel = new Label();

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void populateSearchFiltersAndSorts() {
        /*Adds filtering/sorting options*/
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

    /*A list that stores found results from searches and is then added to the listview
     * for the purpose of sorting later on (maybe)*/

    SillyList<String > searchResultsList = new SillyList<>();


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
                        if (g.getName().toLowerCase().startsWith(search.toLowerCase())|| g.getName().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            searchResultsList.add(g.toString());
                            added = true;
                        }
                        /*all ports made for that machine*/
                        for (GamePort p : g.getPorts()) {
                            if (p.getGameName().toLowerCase().startsWith(search.toLowerCase())|| p.getGameName().toLowerCase().contains(search.toLowerCase())) {
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

    public void showGameDetailsPopup(Game selected) throws IOException {
        gameName.setText(selected.getName().toUpperCase());
        Main.viewPopup("/gameViewer.fxml", selected.getName().toUpperCase() + " DETAILS");
    }
    public void showMachineDetailsPopUp(GamesMachine selected) throws IOException {
        gameName.setText(selected.getName().toUpperCase());
        Main.viewPopup("/machineViewer.fxml", selected.getName().toUpperCase() + " DETAILS");
    }

    public void showPortDetailsPopUp(GamePort selected) throws IOException {
        gameName.setText(selected.getMachinePortedTo().getName().toUpperCase());
        Main.viewPopup("/portViewer.fxml", selected.getMachinePortedTo().getName().toUpperCase() + " PORT DETAILS");
    }

    public void resultsClicked(MouseEvent event) {
        String toDrill;
        if(event.getButton().equals(MouseButton.PRIMARY)) {
            if(event.getClickCount() == 2) {
                toDrill = searchResults.getSelectionModel().getSelectedItem();
                Object foundDrill = gameAPI.hashMap.find(toDrill);

                if (foundDrill != null) {
                    if (foundDrill instanceof GamesMachine) {
                        for (Game g : ((GamesMachine) foundDrill).getGames()) {
                            searchResults.getItems().clear();
                            searchResults.getItems().add(g.toString());
                        }
                    } else if (foundDrill instanceof Game) {
                        for (GamePort p : ((Game) foundDrill).getPorts()) {
                            searchResults.getItems().clear();
                            searchResults.getItems().add(p.toString());
                        }
                    }
                }
            }
        }
    }

    public void sortResults() {
       String sort = machineSort.getSelectionModel().getSelectedItem();
       SillyList<Hashable> results = new SillyList<>();

       for (String s : searchResultsList) {
           results.add(gameAPI.hashMap.find(s));
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
                   for (i = e; i >=1; i--) {
                       int eye;

                       if(results.get(i-1) instanceof GamesMachine) {
                           eye = ((GamesMachine) results.get(i-1)).getYear();
                       } else if (results.get(i-1) instanceof Game) {
                           eye = ((Game)  results.get(i-1)).getYear();
                       } else {
                           eye = ((GamePort) results.get(i-1)).getYear();
                       }

                       if (eye <= elem)
                           break;
                       else
                           results.swapIndex(i, i-1);
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
                   for (i = e; i >=1; i--) {
                       String eye;

                       if(results.get(i-1) instanceof GamesMachine) {
                           eye = ((GamesMachine) results.get(i-1)).getName();
                       } else if (results.get(i-1) instanceof Game) {
                           eye = ((Game)  results.get(i-1)).getName();
                       } else {
                           eye = ((GamePort) results.get(i-1)).getGameName();
                       }

                       if (eye.compareToIgnoreCase(elem) < 1 )
                           break;
                       else
                           results.swapIndex(i, i-1);
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
                   for (i = e; i >=1; i--) {
                       double eye = ((GamesMachine) results.get(i-1)).getPrice();

                       if (eye <= elem)
                           break;
                       else
                           results.swapIndex(i, i-1);
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
}






