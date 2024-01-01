package system.dsaaca2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Models.GamesMachine;

class SillyListTest {

    @Test
    void clear() {
        SillyList<GamesMachine> games = new SillyList<>();
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        games.add(g1);
        games.add(g2);
        games.add(g3);

        Assertions.assertEquals(3, games.size());
        games.clear();
        Assertions.assertEquals(0, games.size());
    }

    @Test
    void contains() {
        SillyList<GamesMachine> ports = new SillyList<>();
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        ports.add(g1);
        ports.add(g2);

        Assert.assertFalse(ports.contains(g3));
        Assert.assertTrue(ports.contains(g1));
        Assert.assertTrue(ports.contains(g2));

        ports.add(g3);

        Assert.assertTrue(ports.contains(g3));
    }

    @Test
    void add() {
        SillyList<GamesMachine> ports = new SillyList<>();
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);

        Assert.assertFalse(ports.contains(g1));

        ports.add(g1);
        Assert.assertTrue(ports.contains(g1));
        ports.add(g2);
        Assert.assertTrue(ports.contains(g1) && ports.contains(g2));
        ports.add(g3);
        Assert.assertTrue(ports.contains(g1) && ports.contains(g2) && ports.contains(g3));
    }

    @Test
    void get() {
        SillyList<GamesMachine> ports = new SillyList<>();
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        ports.add(g1);
        ports.add(g2);
        ports.add(g3);

        Assertions.assertEquals(g3, ports.get(0));
        Assertions.assertEquals(g2, ports.get(1));
        Assertions.assertEquals(g1, ports.get(2));
    }

    @Test
    void indexOf() {
        SillyList<GamesMachine> ports = new SillyList<>();
        GamesMachine g1 = new GamesMachine("Nintendo DS", "Nintendo", "Handheld console with two screens", "Handheld console", "Cartridge", "https://i.ebayimg.com/images/g/7agAAOSw3Exawhnf/s-l1600.jpg", 2002, 150);
        GamesMachine g2 = new GamesMachine("Xbox 1", "Microsoft", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Microsoft-Xbox-One-X-Console-Set.jpg/300px-Microsoft-Xbox-One-X-Console-Set.jpg", 2003, 200);
        GamesMachine g3 = new GamesMachine("PlayStation 4", "Sony", "Home console", "Console", "Disc", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sony-PlayStation-4-PS4-wDualShock-4.jpg/220px-Sony-PlayStation-4-PS4-wDualShock-4.jpg", 2004, 250);
        ports.add(g1);
        ports.add(g2);
        ports.add(g3);

        Assertions.assertEquals(0, ports.indexOf(g3));
        Assertions.assertEquals(1, ports.indexOf(g2));
        Assertions.assertEquals(2, ports.indexOf(g1));
    }
}