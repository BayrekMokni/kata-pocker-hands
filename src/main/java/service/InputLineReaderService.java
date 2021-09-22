package service;

import model.Card;
import model.Game;
import model.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputLineReaderService {

    public Game parse(String entryLine) {
        if (entryLine == null || entryLine.isEmpty()) {
            throw new RuntimeException("Error - Entry line should not be Empty or Null");
        }
        String firstPlayerName;
        String secondPlayerName;
        List<String> firstPlayerCardsList;
        List<String> secondPlayerCardsList;
        try {
            String[] splitByColon = entryLine.split(":");
            firstPlayerName = splitByColon[0];
            String[] splitBySpaces = splitByColon[1].trim().split(" ");
            secondPlayerName = splitBySpaces[splitBySpaces.length - 1];
            String firstPlayerCards = splitByColon[1].trim()
                    .substring(0, splitByColon[1].indexOf(" " + secondPlayerName))
                    .trim();
            firstPlayerCardsList = Arrays.asList(firstPlayerCards.split(" "));
            String secondPlayerCards = splitByColon[2].trim();
            secondPlayerCardsList = Arrays.asList(secondPlayerCards.split(" "));
        } catch (Exception e) {
            throw new RuntimeException("Error - Bad data provided on the entry line");
        }
        Player firstPlayer = new Player()
                .setName(firstPlayerName)
                .setCards(buildCardsObjects(firstPlayerCardsList));
        Player secondPlayer = new Player()
                .setName(secondPlayerName)
                .setCards(buildCardsObjects(secondPlayerCardsList));
        return new Game().setFirstPlayer(firstPlayer)
                .setSecondPlayer(secondPlayer);
    }

    private List<Card> buildCardsObjects(List<String> playerCardsList) {
        if (playerCardsList.size() != 5) {
            throw new RuntimeException("Error - Out of bounds entry length");
        }
        return playerCardsList.stream()
                .map(CardParserService::parseToCard)
                .collect(Collectors.toList());
    }
}
