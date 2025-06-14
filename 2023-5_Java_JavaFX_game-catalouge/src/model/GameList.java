package model;

public class GameList {

    private String game1;
    private String game2;
    private String game3;

    public String getGame1() {
        return game1;
    }

    public void setGame1(String game1) {
        this.game1 = game1;
    }

    public String getGame2() {
        return game2;
    }

    public void setGame2(String game2) {
        this.game2 = game2;
    }

    public String getGame3() {
        return game3;
    }

    public void setGame3(String game3) {
        this.game3 = game3;
    }

    public GameList(String game1, String game2, String game3) {
        this.game1 = game1;
        this.game2 = game2;
        this.game3 = game3;
    }
}
