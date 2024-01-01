package system.dsaaca2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.dsaaca2.Datastructures.HashMap;
import system.dsaaca2.Models.GamesMachine;

public class HashMapTest {
    @Test
    void add() {
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        HashMap<GamesMachine> gamesMachineHashMap = new HashMap<>(5);

        Assertions.assertEquals(3, gamesMachineHashMap.add(g1.toString(), g1));
        Assertions.assertEquals(3, gamesMachineHashMap.add(g2.toString(), g2));
        Assertions.assertEquals(0, gamesMachineHashMap.add(g3.toString(), g3));
    }

    @Test
    void find() {
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        HashMap<GamesMachine> gamesMachineHashMap = new HashMap<>(5);

        gamesMachineHashMap.add(g1.toString(), g1);
        gamesMachineHashMap.add(g2.toString(), g2);
        gamesMachineHashMap.add(g3.toString(), g3);

        Assertions.assertEquals(gamesMachineHashMap.find(g1.toString()), g1);
        Assertions.assertEquals(gamesMachineHashMap.find(g2.toString()), g2);
        Assertions.assertEquals(gamesMachineHashMap.find(g3.toString()), g3);
    }

    @Test
    void remove() {
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        HashMap<GamesMachine> gamesMachineHashMap = new HashMap<>(5);

        gamesMachineHashMap.add(g1.toString(), g1);
        gamesMachineHashMap.add(g2.toString(), g2);
        gamesMachineHashMap.add(g3.toString(), g3);

        Assertions.assertEquals(gamesMachineHashMap.find(g1.toString()), g1);
        gamesMachineHashMap.remove(g1.toString());
        Assertions.assertNull(gamesMachineHashMap.find(g1.toString()));
        Assertions.assertEquals(gamesMachineHashMap.find(g2.toString()), g2);
        gamesMachineHashMap.remove(g2.toString());
        Assertions.assertNull(gamesMachineHashMap.find(g2.toString()));
        Assertions.assertEquals(gamesMachineHashMap.find(g3.toString()), g3);
        gamesMachineHashMap.remove(g3.toString());
        Assertions.assertNull(gamesMachineHashMap.find(g3.toString()));
    }
}
