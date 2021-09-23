package service;

import model.Card;
import model.HandCombination;
import service.calculator.HighCard;
import service.calculator.ICardCombinationCalculator;

import java.util.List;

public class HandService {

    public HandCombination getBestCombination(List<Card> cardList) {
        ICardCombinationCalculator calculator = new HighCard();
        return calculator.execute(cardList);
    }
}
