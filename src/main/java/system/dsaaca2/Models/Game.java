package system.dsaaca2.Models;

import system.dsaaca2.Datastructures.SillyList;

import java.util.Objects;

public class Game {
    private String name, publisher, description, developers, cover;
    private GamesMachine gamesMachine;
    private int year;

    SillyList<GamePort> ports = new SillyList<>(); //Every game has its own list of ports

    public Game(String name, String publisher, String description, String developers, GamesMachine gamesMachine, String cover, int year) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developers = developers;
        this.gamesMachine = gamesMachine;
        this.cover = cover;
        this.year = year;
    }

    public void addPortToGamesListOfPorts(GamePort gp) {
        ports.add(gp); /*Adds the port to its selected games list of ports*/
    }


    public SillyList<GamePort> getPorts() {
        return ports;
    }

    public void setPorts(SillyList<GamePort> ports) {
        this.ports = ports;
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

    @Override
    public String toString() {
        return "|GAME: " + name.toUpperCase() + "| - " + "|PUBLISHER: " + publisher.toUpperCase() + "| - " +
                "|YEAR: " + year + " | - " + "|COVER: " + cover + "| - " +
                "|DEVELOPERS: " + developers.toUpperCase() + " | - " + "|DESCRIPTION: " + description.toUpperCase() + " | - " +
                "|CONSOLE: " + gamesMachine + "|\n" +
                "-----------------------------------------------------------------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getYear() == game.getYear() && Objects.equals(getName(), game.getName()) && Objects.equals(getPublisher(), game.getPublisher()) && Objects.equals(getDescription(), game.getDescription()) && Objects.equals(getDevelopers(), game.getDevelopers()) && Objects.equals(getCover(), game.getCover()) && Objects.equals(getGamesMachine(), game.getGamesMachine()) && Objects.equals(getPorts(), game.getPorts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPublisher(), getDescription(), getDevelopers(), getCover(), getGamesMachine(), getYear(), getPorts());
    }
}

//idk if gamesMachine should be a gamesMachine object or a string and we search for the machine
//^^ It stays as an object because searching will search for the string value match of its name anyway -ava
