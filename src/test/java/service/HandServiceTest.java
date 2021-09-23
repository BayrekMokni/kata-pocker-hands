package service;

import model.Card;
import model.HandCombination;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.Hand.*;
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

    @Test
    public void getBestCombinationShouldReturnTwoPairsResult() {
        final String KING_DIAMOND_CARD = "KD";
        final String KING_HEART_CARD = "KH";
        final String QUEEN_HEART_CARD = "QH";
        final String QUEEN_DIAMOND_CARD = "QD";
        List<Card> expectResult = Arrays.asList(parseToCard(KING_DIAMOND_CARD), parseToCard(KING_HEART_CARD), parseToCard(QUEEN_HEART_CARD), parseToCard(QUEEN_DIAMOND_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList("2H", QUEEN_HEART_CARD, QUEEN_DIAMOND_CARD, KING_HEART_CARD, KING_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(4, handCombination.getBestCombination().size());
        assertEquals(TWO_PAIRS, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnThreeOfAKindResult() {
        final String KING_DIAMOND_CARD = "KD";
        final String KING_HEART_CARD = "KH";
        final String KING_CLUB_CARD = "KC";
        final String QUEEN_DIAMOND_CARD = "QD";
        List<Card> expectResult = Arrays.asList(parseToCard(KING_DIAMOND_CARD), parseToCard(KING_HEART_CARD), parseToCard(KING_CLUB_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList("2H", KING_CLUB_CARD, QUEEN_DIAMOND_CARD, KING_HEART_CARD, KING_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(3, handCombination.getBestCombination().size());
        assertEquals(THREE_OF_A_KIND, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnStraightResult() {
        final String ACE_DIAMOND_CARD = "AD";
        final String KING_HEART_CARD = "KH";
        final String QUEEN_CLUB_CARD = "QC";
        final String JACK_DIAMOND_CARD = "JD";
        final String TEN_DIAMOND_CARD = "TD";
        List<Card> expectResult = Arrays.asList(parseToCard(ACE_DIAMOND_CARD), parseToCard(KING_HEART_CARD), parseToCard(QUEEN_CLUB_CARD), parseToCard(JACK_DIAMOND_CARD), parseToCard(TEN_DIAMOND_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList(ACE_DIAMOND_CARD, KING_HEART_CARD, QUEEN_CLUB_CARD, JACK_DIAMOND_CARD, TEN_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(5, handCombination.getBestCombination().size());
        assertEquals(STRAIGHT, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnFlushResult() {
        final String ACE_DIAMOND_CARD = "AD";
        final String KING_DIAMOND_CARD = "KD";
        final String QUEEN_DIAMOND_CARD = "QD";
        final String JACK_DIAMOND_CARD = "JD";
        final String TEN_DIAMOND_CARD = "TD";
        List<Card> expectResult = Arrays.asList(parseToCard(ACE_DIAMOND_CARD), parseToCard(KING_DIAMOND_CARD), parseToCard(QUEEN_DIAMOND_CARD), parseToCard(JACK_DIAMOND_CARD), parseToCard(TEN_DIAMOND_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList(ACE_DIAMOND_CARD, KING_DIAMOND_CARD, QUEEN_DIAMOND_CARD, JACK_DIAMOND_CARD, TEN_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(5, handCombination.getBestCombination().size());
        assertEquals(FLUSH, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnFullHouseResult() {
        final String ACE_DIAMOND_CARD = "AD";
        final String KING_HEART_CARD = "AH";
        final String QUEEN_DIAMOND_CARD = "QD";
        final String QUEEN_CLUB_CARD = "QC";
        final String QUEEN_HEART_CARD = "QH";
        List<Card> expectResult = Arrays.asList(parseToCard(ACE_DIAMOND_CARD), parseToCard(KING_HEART_CARD), parseToCard(QUEEN_DIAMOND_CARD), parseToCard(QUEEN_CLUB_CARD), parseToCard(QUEEN_HEART_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList(ACE_DIAMOND_CARD, KING_HEART_CARD, QUEEN_DIAMOND_CARD, QUEEN_CLUB_CARD, QUEEN_HEART_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(5, handCombination.getBestCombination().size());
        assertEquals(FULL_HOUSE, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnFourOfAKindResult() {
        final String ACE_DIAMOND_CARD = "AD";
        final String QUEEN_SPADE_CARD = "QS";
        final String QUEEN_DIAMOND_CARD = "QD";
        final String QUEEN_CLUB_CARD = "QC";
        final String QUEEN_HEART_CARD = "QH";
        List<Card> expectResult = Arrays.asList(parseToCard(QUEEN_SPADE_CARD), parseToCard(QUEEN_DIAMOND_CARD), parseToCard(QUEEN_CLUB_CARD), parseToCard(QUEEN_HEART_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList(ACE_DIAMOND_CARD, QUEEN_SPADE_CARD, QUEEN_DIAMOND_CARD, QUEEN_CLUB_CARD, QUEEN_HEART_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(4, handCombination.getBestCombination().size());
        assertEquals(FOUR_OF_A_KIND, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    @Test
    public void getBestCombinationShouldReturnStraightFlushResult() {
        final String ACE_DIAMOND_CARD = "AD";
        final String KING_DIAMOND_CARD = "KD";
        final String QUEEN_DIAMOND_CARD = "QD";
        final String JACK_DIAMOND_CARD = "JD";
        final String TEN_DIAMOND_CARD = "TD";
        List<Card> expectResult = Arrays.asList(parseToCard(ACE_DIAMOND_CARD), parseToCard(KING_DIAMOND_CARD), parseToCard(QUEEN_DIAMOND_CARD), parseToCard(JACK_DIAMOND_CARD), parseToCard(TEN_DIAMOND_CARD));
        List<Card> cardListEntries = buildCardListEntries(Arrays.asList(ACE_DIAMOND_CARD, KING_DIAMOND_CARD, QUEEN_DIAMOND_CARD, JACK_DIAMOND_CARD, TEN_DIAMOND_CARD));

        HandCombination handCombination = handService.getBestCombination(cardListEntries);

        assertEquals(5, handCombination.getBestCombination().size());
        assertEquals(STRAIGHT_FLUSH, handCombination.getHand());
        assertTrue(handCombination.getBestCombination().containsAll(expectResult));
    }

    private List<Card> buildCardListEntries(List<String> cards) {
        return cards.stream()
                .map(CardParserService::parseToCard)
                .collect(Collectors.toList());
    }
}
