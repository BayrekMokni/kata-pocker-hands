package service;

import model.Card;
import model.HandCombination;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.Hand.HIGH_CARD;
import static model.Hand.PAIR;
import static org.junit.Assert.*;
import static service.CardParserService.parseToCard;

public class HandServiceTest {

    private final HandService handService = new HandService();

    @Test
    public void getBestCombinationShouldRespond() {
        List<Card> cardList = buildCardListEntries(Arrays.asList("2H", "3D", "5S", "9C", "KD"));
        HandCombination handCombination = handService.getBestCombination(cardList);
        assertNotNull(handCombination);
    }

    @Test
    public void getBestCombinationShouldReturnHighCardResult() {
        final String KING_DIAMOND_CARD = "KD";
        Card expectedResult = parseToCard(KING_DIAMOND_CARD);
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList("2H", "3D", "5S", "9C", KING_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(expectedResult.getCardValue().getValue(), handCombination.getBestCombination().get(0).getCardValue().getValue());
        assertEquals(HIGH_CARD, handCombination.getHand());
    }

    @Test
    public void getBestCombinationShouldReturnPairResult() {
        final String KING_DIAMOND_CARD = "KD";
        final String KING_HEART_CARD = "KH";
        List<Card> expectResult = Arrays.asList(parseToCard(KING_DIAMOND_CARD), parseToCard(KING_HEART_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList("2H", "3D", "5S", KING_HEART_CARD, KING_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(2, handCombination.getBestCombination().size());
        assertEquals(PAIR, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    private List<Card> buildCardListEntries(List<String> cards) {
        return cards.stream()
                .map(CardParserService::parseToCard)
                .collect(Collectors.toList());
    }
}
