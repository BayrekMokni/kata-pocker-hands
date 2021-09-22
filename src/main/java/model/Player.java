package model;

import java.util.List;

public class Player {

    private String name;
    private List<Card> cards;

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }
}
