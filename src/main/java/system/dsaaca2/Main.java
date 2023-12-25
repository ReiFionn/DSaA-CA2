package system.dsaaca2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.dsaaca2.Controllers.*;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;

import java.io.*;
import java.util.Objects;

public class Main extends Application {
    public static Scene homePageScene,gameScene,systemScene;
    public static Stage mainStage;

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
}

//Main JavaFX stuff here, technically a controller