package system.dsaaca2;

public class Game {
    private String name, publisher, description, developers, gameMachine, cover;
    private int year;

    public Game(String name, String publisher, String description, String developers, String gameMachine, String cover, int year) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developers = developers;
        this.gameMachine = gameMachine;
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

    public String getGameMachine() {
        return gameMachine;
    }

    public void setGameMachine(String gameMachine) {
        this.gameMachine = gameMachine;
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
                ", gameMachine='" + gameMachine + '\'' +
                ", cover='" + cover + '\'' +
                ", year=" + year +
                '}';
    }
}
