package system.dsaaca2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Scene homePageScene,gameScene,systemScene;
    public static Stage mainStage;
    public static Stage popupStage;

    public static void main(String[] args) {
        launch();
    }
    public Scene newScene(String file) throws IOException {
        /*Method for creating new scenes*/
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(file));
        Parent root = fxmlLoader.load();
        Scene newScene = new Scene(root, 1500, 900);
        newScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet.css")).toExternalForm());
        return newScene; // Return the new scene
    }

    @Override
    public void start(Stage stage) throws IOException {

        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/firstPage.fxml"));
        Parent root = fxmlLoader.load();
        homePageScene = new Scene(root, 1500, 900);

        gameScene = newScene("/gamePage.fxml");
        systemScene = newScene("/systemScene.fxml");
        // Set the initial scene (homePage)
        mainStage.setScene(homePageScene);
        mainStage.setResizable(false);
        mainStage.setTitle("Retro Games System");
        mainStage.show();

    }



    public static Stage newPopup(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(file));
        Parent root = fxmlLoader.load();

        // Create a new stage
        Stage popUp = new Stage();
        popUp.setTitle(title);
        popUp.setResizable(false);


        // Create a new scene with the loaded content
        Scene newScene = new Scene(root, 1000, 700);



        // Set the scene for the stage

        popUp.setScene(newScene);


        // Add stylesheets if needed
        newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());
        return popUp; // Return the new stage


    }

    public static void viewPopup(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(file));
        Parent root = fxmlLoader.load();

        // Create a new stage
        Stage popUp = new Stage();
        popUp.setTitle(title);
        popUp.setResizable(false);

        // Create a new scene with the loaded content
        Scene newScene = new Scene(root, 500, 700);

        // Set the scene for the stage
        popUp.setScene(newScene);

        // Add stylesheets if needed
        newScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/popUpStyle.css")).toExternalForm());

        // Show the stage
        popUp.showAndWait(); // You can use popUp.show() if you don't want it to block the calling code
    }








}
