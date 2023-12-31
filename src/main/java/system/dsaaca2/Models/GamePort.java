package system.dsaaca2.Models;

public class GamePort implements Hashable{
    private final Game originalGame;
    private GamesMachine machinePortedTo;
    private final GamesMachine originalMachine;
    private String developers, cover;
    private int portYear;

    public GamePort(Game originalGame, GamesMachine machinePortedTo, GamesMachine originalMachine, String developers, String cover, int portYear) {
        this.originalGame = originalGame;
        this.machinePortedTo = machinePortedTo;
        this.originalMachine = originalMachine;
        this.developers = developers;
        this.cover = cover;
        this.portYear = portYear;
    }

    public Game getOriginalGame() {
        return originalGame;
    }
    public String getGameName() {
        return originalGame.getName();
    }

    public String getNewPortName(){
       return machinePortedTo.getName();
    }

    public GamesMachine getMachinePortedTo() {
        return machinePortedTo;
    }
    public void setMachinePortedTo(GamesMachine machinePortedTo) {
        this.machinePortedTo = machinePortedTo;
    }

    public GamesMachine getOriginalMachine() {
        return originalMachine;
    }

    public String getDevelopers() {
        return developers;
    }
    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getYear() {
        return portYear;
    }
    public void setPortYear(int portYear) {
        this.portYear = portYear;
    }

    @Override
    public String toString() {
        return
                "[Port] " + getOriginalGame().getName().toUpperCase() + "   From Machine: ( " + originalMachine.getName().toUpperCase() +" )   For --> " +
                        " ( "+ machinePortedTo.getName().toUpperCase()+" ) ,  Developers: ( "+ developers.toUpperCase() + " ) ,  Year: ( "+ portYear +  " )";

    }

    @Override
    public boolean matchKey(String key) {
        return toString().toLowerCase().equals(key);
    }
}
