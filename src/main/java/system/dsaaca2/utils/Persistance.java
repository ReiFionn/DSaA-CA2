package system.dsaaca2.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import system.dsaaca2.Controllers.GameAPI;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistance {

    @SuppressWarnings("unchecked") /*Just removes annoying warnings*/
    public static void load() throws Exception {
        Class<?>[] classes = new Class[]{Game.class, GamePort.class, GamesMachine.class, SillyList.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("system.xml"));
        GameAPI.allGames = (SillyList<Game>) in.readObject();
        GameAPI.allGamePorts = (SillyList<GamePort>) in.readObject();
        GameAPI.allMachines = (SillyList<GamesMachine>) in.readObject();
        in.close();
    }


    public static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("system.xml"));
        out.writeObject(GameAPI.allGames);
        out.writeObject(GameAPI.allGamePorts);
        out.writeObject(GameAPI.allMachines);

        out.close();
    }
}
