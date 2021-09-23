package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static model.Hand.STRAIGHT;

public class StraightCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        HandCombination result = new HandCombination()
                .setHand(STRAIGHT);
        List<Card> sortedList = cards.stream()
                .sorted(Comparator.comparingInt(card -> card.getCardValue().getValue()))
                .collect(Collectors.toList());

        Card minCard = sortedList.get(0);
        Card maxCard = sortedList.get(sortedList.size() - 1);
        if ((maxCard.getCardValue().getValue() - minCard.getCardValue().getValue() + 1) == sortedList.size()) {
            result.setBestCombination(sortedList);
        } else {
            result.setBestCombination(Collections.emptyList());
        }
        return result;
    }
}
