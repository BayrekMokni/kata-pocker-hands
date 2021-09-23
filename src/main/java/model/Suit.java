package model;

import java.util.Arrays;

public enum Suit {

    CLUBS("C", "♣"),
    DIAMONDS("D", "◆"),
    HEART("H", "♥"),
    SPADES("S", "♠");

    private String value;
    private String displayedValue;

    Suit(String value, String displayedValue) {
        this.value = value;
        this.displayedValue = displayedValue;
    }

    public static Suit getCardSuit(String entry) {
        return Arrays.stream(Suit.values())
                .filter(suit -> suit.getValue().equals(entry))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error - Bad Suit data entry. Can not find " + entry));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayedValue() {
        return displayedValue;
    }

    public void setDisplayedValue(String displayedValue) {
        this.displayedValue = displayedValue;
    }
}
