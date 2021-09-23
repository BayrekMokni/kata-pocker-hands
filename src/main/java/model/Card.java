package model;

import java.util.Objects;

public class Card {

    private CardValue cardValue;
    private Suit suit;

    public CardValue getCardValue() {
        return cardValue;
    }

    public Card setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
        return this;
    }

    public Suit getSuit() {
        return suit;
    }

    public Card setSuit(Suit suit) {
        this.suit = suit;
        return this;
    }

    @Override
    public String toString() {
        return cardValue.getDisplayedValue() + suit.getDisplayedValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, suit);
    }
}
