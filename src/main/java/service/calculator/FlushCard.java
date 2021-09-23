package service.calculator;

import model.Card;
import model.HandCombination;
import model.Suit;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static model.Hand.FLUSH;

public class FlushCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        HandCombination result = new HandCombination()
                .setHand(FLUSH);
        Map<Suit, List<Card>> collect = cards.stream()
                .collect(groupingBy(Card::getSuit));
        if (collect.entrySet().size() == 1) {
            result.setBestCombination(cards);
        }
        return result;
    }
}
