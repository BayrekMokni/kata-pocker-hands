package model;

import java.util.List;
import java.util.Map;

public class HandCombination {

    private Hand hand;
    private List<Card> bestCombination;
    private Map<Integer, List<Card>> bestCombinationMap;

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

    public Map<Integer, List<Card>> getBestCombinationMap() {
        return bestCombinationMap;
    }

    public HandCombination setBestCombinationMap(Map<Integer, List<Card>> bestCombinationMap) {
        this.bestCombinationMap = bestCombinationMap;
        return this;
    }
}
