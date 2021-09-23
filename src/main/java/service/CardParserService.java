package service;

import model.Card;

import static model.CardValue.getCardValue;
import static model.Suit.getCardSuit;

public class CardParserService {

    public static Card parseToCard(String card) {
        if (card.length() != 2) {
            throw new RuntimeException("Error - Value is out of bounds");
        }
        return new Card()
                .setCardValue(getCardValue(card.trim().substring(0, 1)))
                .setSuit(getCardSuit(card.trim().substring(1)));
    }
}
