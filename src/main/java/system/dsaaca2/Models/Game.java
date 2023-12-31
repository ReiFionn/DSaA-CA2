package system.dsaaca2.Models;

import system.dsaaca2.Datastructures.SillyList;

public class Game implements Hashable {
    private String name, publisher, description, developers, cover;
    private GamesMachine gamesMachine;
    private int year;

    SillyList<GamePort> ports = new SillyList<>(); //Every game has its own list of ports

    public Game(String name, String publisher, String description, String developers, GamesMachine gamesMachine, String cover, int year) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developers = developers;
        setGamesMachine(gamesMachine);
        this.cover = cover;
        this.year = year;
    }

    public SillyList<GamePort> getPorts() {
        return ports;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopers() {
        return developers;
    }
    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public GamesMachine getGamesMachine() {
        return gamesMachine;
    }
    public void setGamesMachine(GamesMachine gamesMachine) {
        this.gamesMachine = gamesMachine;
    }

    public String getGamesMachineName() {
        return gamesMachine.getName();
    }

    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void addPort(GamePort p) {
        ports.add(p);
    }
    public void removePort(GamePort p) {
        ports.remove(p);
    }

    @Override
    public String toString() {
        return
                "[Game] "+name.toUpperCase()+ "   Machine: ( "+getGamesMachineName().toUpperCase() +" )  ,  Publisher: ( "+publisher.toUpperCase()+" ) , Year: ( "+year +" )";
    }

    @Override
    public boolean matchKey(String key) {
        return toString().toLowerCase().equals(key);
    }
}
