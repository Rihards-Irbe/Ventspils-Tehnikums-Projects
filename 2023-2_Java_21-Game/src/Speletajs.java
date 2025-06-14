public class Speletajs{

    private String Player_Name;
    private int Player_points;
    private int Player_wins;
    private int Player_loses;

    public String getPlayer_Name() {
        return Player_Name;
    }

    public int getPlayer_points() {
        return Player_points;
    }

    public int getPlayer_wins() {
        return Player_wins;
    }

    public int getPlayer_loses() {
        return Player_loses;
    }

    public void setPlayer_Name(String player_Name) {
        Player_Name = player_Name;
    }

    public void setPlayer_points(int player_points) {
        Player_points = player_points;
    }

    public void setPlayer_wins(int player_wins) {
        Player_wins = player_wins;
    }

    public void setPlayer_loses(int player_loses) {
        Player_loses = player_loses;
    }

    public Speletajs(String Player_Name, int Player_points, int Player_wins, int Player_loses) {
        this.Player_Name = Player_Name;
        this.Player_points = Player_points;
        this.Player_wins = Player_wins;
        this.Player_loses = Player_loses;
    }

    public String toString() {

        return "Spēlētāja vārds: " + Player_Name + "Spēlētāja punkti: " + Player_points + "Spēlētāja uzvaras: " + Player_wins + "Spēlētāja zaudes: " + Player_loses;

    }

}
