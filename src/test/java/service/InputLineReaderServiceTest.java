package service;

import model.Card;
import model.Game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.H;
import static org.junit.Assert.*;
import static service.CardParserService.parseToCard;

public class InputLineReaderServiceTest {

    private final InputLineReaderService inputLineReader = new InputLineReaderService();

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenEmptyEntryLine() {
        inputLineReader.parse("");
    }

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenNullEntryLine() {
        inputLineReader.parse(null);
    }

    @Test
    public void parseShouldReturnNotNullGame() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        assertNotNull(game);
    }

    @Test
    public void parseShouldReturnGameContainingTwoNotNullPlayers() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        assertNotNull(game.getFirstPlayer());
        assertNotNull(game.getSecondPlayer());
    }

    @Test
    public void parseShouldReturnNotNullPlayersNames() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        assertNotNull(game.getFirstPlayer().getName());
        assertNotNull(game.getSecondPlayer().getName());
    }

    @Test
    public void parseShouldReturnNotNullPlayersCards() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        assertNotNull(game.getFirstPlayer().getCards());
        assertNotNull(game.getSecondPlayer().getCards());
    }


    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenBadLengthEntryLine() {
        String entry = "Bad entry line";
        inputLineReader.parse(entry);
    }

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenBadLengthCardsNumber() {
        String entry = "Black: 2H 3D 5S 9C KD 2C 3H 4S White: 8C AH";
        inputLineReader.parse(entry);
    }

    @Test
    public void parseShouldReturnCorrectPlayersNames() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        assertEquals("Black", game.getFirstPlayer().getName());
        assertNotNull("White", game.getSecondPlayer().getName());
    }

    @Test(expected = RuntimeException.class)
    public void parseShouldThrowExceptionWhenBadCardValue() {
        String entry = "Black: 2HXXXX 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        inputLineReader.parse(entry);
    }

    @Test
    public void parseShouldReturnCorrectPlayersCards() {
        String entry = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
        Game game = inputLineReader.parse(entry);
        List<Card> firstPlayerExpectedCards = new ArrayList<>(Arrays.asList(parseToCard("2H"),parseToCard("3D"),parseToCard("5S"),parseToCard("9C"),parseToCard("KD")));
        List<Card> secondPlayerExpectedCards = new ArrayList<>(Arrays.asList(parseToCard("2C"),parseToCard("3H"),parseToCard("4S"),parseToCard("8C"),parseToCard("AH")));
        assertEquals(firstPlayerExpectedCards, game.getFirstPlayer().getCards());
        assertEquals(secondPlayerExpectedCards, game.getSecondPlayer().getCards());
    }
}
