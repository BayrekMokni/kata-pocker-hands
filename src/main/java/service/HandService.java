package service;

import model.Card;
import model.HandCombination;
import service.calculator.*;

import java.util.List;

public class HandService {

    public HandCombination getBestCombination(List<Card> cardList) {
        HandCombination cardCalculationResult;
        ICardCombinationCalculator cardCalculator;

        cardCalculator = new StraightFlushCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 5){
            return cardCalculationResult;
        }

        cardCalculator = new FourOfaKindCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 4){
            return cardCalculationResult;
        }

        cardCalculator = new FullHouseCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 5){
            return cardCalculationResult;
        }

        cardCalculator = new FlushCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 5){
            return cardCalculationResult;
        }

        cardCalculator = new StraightCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 5){
            return cardCalculationResult;
        }

        cardCalculator = new ThreeOfAKindCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 3){
            return cardCalculationResult;
        }

        cardCalculator = new TwoPairCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 4){
            return cardCalculationResult;
        }

        cardCalculator = new PairCard();
        cardCalculationResult = cardCalculator.execute(cardList);
        if (cardCalculationResult.getBestCombination().size() == 2){
            return cardCalculationResult;
        }

        cardCalculator = new HighCard();
        return cardCalculator.execute(cardList);
    }
}

