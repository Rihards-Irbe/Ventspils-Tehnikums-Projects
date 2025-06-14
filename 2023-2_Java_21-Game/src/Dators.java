public class Dators {

    private int Computer_points;
    private int Computer_wins;
    private int Computer_loses;

    public int getComputer_points() {
        return Computer_points;
    }

    public int getComputer_wins() {
        return Computer_wins;
    }

    public int getComputer_loses() {
        return Computer_loses;
    }

    public void setComputer_points(int computer_points) {
        Computer_points = computer_points;
    }

    public void setComputer_wins(int computer_wins) {
        Computer_wins = computer_wins;
    }

    public void setComputer_loses(int computer_loses) {
        Computer_loses = computer_loses;
    }

    public Dators(int Computer_points, int Computer_wins, int Computer_loses) {
        this.Computer_points = Computer_points;
        this.Computer_wins = Computer_wins;
        this.Computer_loses = Computer_loses;
    }

    public String toString() {

        return "Datora punkti: " + Computer_points + "Datora uzvaras: " + Computer_wins + "Datora zaudes: " + Computer_loses;

    }
}
