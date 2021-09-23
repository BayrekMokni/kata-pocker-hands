package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static model.Hand.FOUR_OF_A_KIND;

public class FourOfaKindCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        HandCombination result = new HandCombination()
                .setHand(FOUR_OF_A_KIND);
        Map<Integer, List<Card>> collect = cards.stream()
                .collect(groupingBy(e -> e.getCardValue().getValue()));

        return result.setBestCombination(calculateCards(collect));
    }

    private List<Card> calculateCards(Map<Integer, List<Card>> collect) {
        List<Card> fourCards = new ArrayList<>();
        collect.values().forEach(entryList -> {
            if (entryList.size() == 4) {
                fourCards.addAll(entryList);
            }
        });
        return fourCards;
    }
}
