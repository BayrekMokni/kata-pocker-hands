package model;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Game setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        return this;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Game setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
        return this;
    }
}
