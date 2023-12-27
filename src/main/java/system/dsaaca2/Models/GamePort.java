package system.dsaaca2.Models;

import java.util.Objects;

public class GamePort {
    private Game originalGame;
    private GamesMachine machinePortedTo;
    private GamesMachine originalMachine;
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

    public void setOriginalGame(Game originalGame) {
        this.originalGame = originalGame;
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

    public void setOriginalMachine(GamesMachine originalMachine) {
        this.originalMachine = originalMachine;
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

    public int getPortYear() {
        return portYear;
    }

    public void setPortYear(int portYear) {
        this.portYear = portYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePort gamePort = (GamePort) o;
        return getPortYear() == gamePort.getPortYear() && Objects.equals(getOriginalGame(), gamePort.getOriginalGame()) && Objects.equals(getMachinePortedTo(), gamePort.getMachinePortedTo()) && Objects.equals(getOriginalMachine(), gamePort.getOriginalMachine()) && Objects.equals(getDevelopers(), gamePort.getDevelopers()) && Objects.equals(getCover(), gamePort.getCover());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginalGame(), getMachinePortedTo(), getOriginalMachine(), getDevelopers(), getCover(), getPortYear());
    }

    @Override
 public String toString() {
     return  "|ORIGINAL GAME: " + originalGame.getName().toUpperCase() + " | - " +
             "|MACHINE PORTED TO: " + machinePortedTo.getName().toUpperCase() + " | - " +
             "|ORIGINAL MACHINE: " + originalMachine.getName().toUpperCase() + "|\n" +
             "|DEVELOPERS: " + developers.toUpperCase() + " | - " +
             "|COVER: " + cover + " | - " +
             "|PORT YEAR: " + portYear + "|\n" +
             "-----------------------------------------------------------------------------------";
 }

}
