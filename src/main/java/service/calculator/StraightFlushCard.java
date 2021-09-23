package service.calculator;

import model.Card;
import model.HandCombination;
import model.Suit;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static model.Hand.STRAIGHT;
import static model.Hand.STRAIGHT_FLUSH;

public class StraightFlushCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        HandCombination result = new HandCombination()
                .setHand(STRAIGHT_FLUSH);
        List<Card> sortedList = cards.stream()
                .sorted(Comparator.comparingInt(card -> card.getCardValue().getValue()))
                .collect(Collectors.toList());

        Card minCard = sortedList.get(0);
        Card maxCard = sortedList.get(sortedList.size() - 1);
        if ((maxCard.getCardValue().getValue() - minCard.getCardValue().getValue() + 1) == sortedList.size()) {
            Map<Suit, List<Card>> collect = cards.stream()
                    .collect(groupingBy(Card::getSuit));
            if (collect.entrySet().size() == 1) {
                result.setBestCombination(sortedList);
            }
            return result;
        }
        return result.setBestCombination(Collections.emptyList());
    }
}
