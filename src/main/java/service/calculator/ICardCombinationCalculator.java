package service.calculator;

import model.Card;
import model.HandCombination;

import java.util.List;

public interface ICardCombinationCalculator {

    HandCombination execute(List<Card> cards);

}
