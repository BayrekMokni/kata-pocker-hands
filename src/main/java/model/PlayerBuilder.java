package model;

import java.util.List;

public class PlayerBuilder {

    String playerName;
    List<Card> cards;

    public PlayerBuilder withName(String name) {
        this.playerName = name;
        return this;
    }

    public PlayerBuilder withCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public Player build() {
        return new Player()
                .setName(this.playerName)
                .setCards(this.cards);
    }
}
