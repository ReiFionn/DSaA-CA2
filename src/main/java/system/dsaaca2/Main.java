package system.dsaaca2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main extends Application {

    public static void load() throws Exception {
        Class<?>[] classes = new Class[]{Game.class, GamePort.class, GamesMachine.class, SillyList.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("system.xml"));
        GameAPI.allGames = (SillyList<Game>) in.readObject();
        GamePortAPI.allGamePorts = (SillyList<GamePort>) in.readObject();
        GamesMachineAPI.allGamesMachines = (SillyList<GamesMachine>) in.readObject();
        in.close();
    }

    public static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("system.xml"));
        out.writeObject(GameAPI.allGames);
        out.writeObject(GamePortAPI.allGamePorts);
        out.writeObject(GamesMachineAPI.allGamesMachines);

        out.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
    }
}

//Main JavaFX stuff here, technically a controller