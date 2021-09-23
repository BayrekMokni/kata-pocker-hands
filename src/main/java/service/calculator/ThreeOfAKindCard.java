package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static model.Hand.THREE_OF_A_KIND;

public class ThreeOfAKindCard implements ICardCombinationCalculator {

    @Override
    public HandCombination execute(List<Card> cards) {
        List<Card> duplicatedCards = cards.stream().flatMap(card -> {
            final AtomicInteger count = new AtomicInteger();
            final List<Card> duplicatedCard = new ArrayList<>();
            cards.forEach(cardToCompare -> {
                if (cardToCompare.getCardValue().getValue() == card.getCardValue().getValue()) {
                    count.getAndIncrement();
                }
                if (count.get() == 2 && !duplicatedCard.contains(card)) {
                    duplicatedCard.add(card);
                }
            });
            return duplicatedCard.stream();
        }).collect(Collectors.toList());
        return new HandCombination()
                .setHand(THREE_OF_A_KIND)
                .setBestCombination(duplicatedCards);
    }
}
