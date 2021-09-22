package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static service.CardParserService.parseToCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardParserServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenBadLengthCardValue() {
        parseToCard("9999H");
    }

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenBadCardValue() {
        parseToCard("0H");
    }

    @Test
    public void parseShouldReturnNotNullCard() {
        Card card = parseToCard("2H");
        assertNotNull(card);
    }

    @Test
    public void parseShouldReturnCardWithNotNullAttributes() {
        Card card = parseToCard("2H");
        assertNotNull(card.getCardValue());
        assertNotNull(card.getSuit());
    }

    @Test
    public void parseShouldReturnCardWithNotNullCardAttributes() {
        Card card = parseToCard("2H");
        assertNotNull(card.getCardValue().getDisplayedValue());
    }

    @Test
    public void parseShouldReturnCardWithCorrectValues() {
        Card card = parseToCard("2H");
        assertEquals("2", card.getCardValue().getDisplayedValue());
        assertEquals("H", card.getSuit().getValue());
    }

    @Test
    public void parseShouldReturnCardWithCorrectClubsSymbol() {
        Card card = parseToCard("2C");
        assertEquals("♣", card.getSuit().getDisplayedValue());
    }

    @Test
    public void parseShouldReturnCardWithCorrectDiamondsSymbol() {
        Card card = parseToCard("2D");
        assertEquals("◆", card.getSuit().getDisplayedValue());
    }

    @Test
    public void parseShouldReturnCardWithCorrectHeartSymbol() {
        Card card = parseToCard("2H");
        assertEquals("♥", card.getSuit().getDisplayedValue());
    }

    @Test
    public void parseShouldWriteOnConsoleSymbol() {
        Card card = parseToCard("2S");
        System.out.println(card.toString());
        assertEquals("2♠\r\n", outContent.toString());
    }
}
