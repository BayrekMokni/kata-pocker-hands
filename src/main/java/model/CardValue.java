package model;

import java.util.Arrays;

public enum CardValue {

    CARD_1(1,"1"),
    CARD_2(2,"2"),
    CARD_3(3,"3"),
    CARD_4(4,"4"),
    CARD_5(5,"5"),
    CARD_6(6,"6"),
    CARD_7(7,"7"),
    CARD_8(8,"8"),
    CARD_9(9,"9"),
    CARD_10(10,"T"),
    CARD_11(11,"J"),
    CARD_12(12,"Q"),
    CARD_13(13,"K"),
    CARD_14(14,"A");

    private final int value;
    private final String displayedValue;

    CardValue(int value, String displayedValue) {
        this.value = value;
        this.displayedValue = displayedValue;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayedValue() {
        return displayedValue;
    }

    public static CardValue getCardValue(String entry) {
        return Arrays.stream(CardValue.values())
                .filter(e -> e.getDisplayedValue().equals(entry))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error - Bad data entry. Can not find " + entry));
    }
}
