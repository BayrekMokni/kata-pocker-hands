package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static model.Hand.FLUSH;
import static model.Hand.FULL_HOUSE;

public class FullHouseCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        HandCombination result = new HandCombination()
                .setHand(FULL_HOUSE);
        Map<Integer, List<Card>> collect = cards.stream()
                .collect(groupingBy(e -> e.getCardValue().getValue()));
        if (collect.entrySet().size() == 2 && mustPairAndThreeCards(collect)) {
            result.setBestCombination(cards);
        }
        return result;
    }

    private boolean mustPairAndThreeCards(Map<Integer, List<Card>> collect) {
        int sum = collect.values().stream()
                .map(List::size)
                .collect(Collectors.toList())
                .stream()
                .mapToInt(integer -> integer)
                .sum();
        return sum == 5;
    }
}
