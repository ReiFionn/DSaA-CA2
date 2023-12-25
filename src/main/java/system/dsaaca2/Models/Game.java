package system.dsaaca2.Models;

public class Game {
    private String name, publisher, description, developers, cover;
    private GamesMachine gamesMachine;
    private int year;

    public Game(String name, String publisher, String description, String developers, GamesMachine gamesMachine, String cover, int year) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developers = developers;
        this.gamesMachine = gamesMachine;
        this.cover = cover;
        this.year = year;
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
        return "Game{" +
                "name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", developers='" + developers + '\'' +
                ", gamesMachine='" + gamesMachine + '\'' +
                ", cover='" + cover + '\'' +
                ", year=" + year +
                '}';
    }
}

//idk if gamesMachine should be a gamesMachine object or a string and we search for the machine
