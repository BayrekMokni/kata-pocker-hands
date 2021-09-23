package service;

import javafx.util.Pair;
import model.Card;
import model.HandCombination;
import service.calculator.HighCard;
import service.calculator.ICardCombinationCalculator;
import service.calculator.PairCard;

import java.util.List;

public class HandService {

    public HandCombination getBestCombination(List<Card> cardList) {
        HandCombination pairCardResult;
        ICardCombinationCalculator pairCardCalculator;

        pairCardCalculator = new PairCard();
        pairCardResult = pairCardCalculator.execute(cardList);
        if (pairCardResult.getBestCombination().size() == 2){
            return pairCardResult;
        }

        pairCardCalculator = new HighCard();
        return pairCardCalculator.execute(cardList);
    }
}
