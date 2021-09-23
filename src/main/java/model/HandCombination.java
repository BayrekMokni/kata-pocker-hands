package model;

import java.util.List;

public class HandCombination {

    private Hand hand;
    private List<Card> bestCombination;

    public Hand getHand() {
        return hand;
    }

    public HandCombination setHand(Hand hand) {
        this.hand = hand;
        return this;
    }

    public List<Card> getBestCombination() {
        return bestCombination;
    }

    public HandCombination setBestCombination(List<Card> bestCombination) {
        this.bestCombination = bestCombination;
        return this;
    }
}
