package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static model.Hand.HIGH_CARD;

public class HighCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        Card card = cards
                .stream()
                .max(Comparator.comparing(Card::getCardValue))
                .get();
        return new HandCombination()
                .setHand(HIGH_CARD)
                .setBestCombination(Collections.singletonList(card));
    }
}
