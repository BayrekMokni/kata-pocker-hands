package service;

import model.Card;
import model.Game;
import model.Player;
import model.PlayerBuilder;

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
        List<String> firstPlayerCardList;
        List<String> secondPlayerCardList;
        try {
            String[] splitByColon = entryLine.split(":");
            firstPlayerName = splitByColon[0];
            String[] splitBySpaces = splitByColon[1].trim().split(" ");
            secondPlayerName = splitBySpaces[splitBySpaces.length - 1];
            String firstPlayerCards = splitByColon[1].trim()
                    .substring(0, splitByColon[1].indexOf(" " + secondPlayerName))
                    .trim();
            firstPlayerCardList = Arrays.asList(firstPlayerCards.split(" "));
            String secondPlayerCards = splitByColon[2].trim();
            secondPlayerCardList = Arrays.asList(secondPlayerCards.split(" "));
            validate(firstPlayerCardList);
            validate(secondPlayerCardList);
        } catch (Exception e) {
            throw new RuntimeException("Error - Bad data provided on the entry line");
        }
        return new Game()
                .setFirstPlayer(buildPlayer(firstPlayerName, buildCardsObjects(firstPlayerCardList)))
                .setSecondPlayer(buildPlayer(secondPlayerName, buildCardsObjects(secondPlayerCardList)));
    }

    private Player buildPlayer(String playerName, List<Card> playerCardsList) {
        return new PlayerBuilder()
                .withName(playerName)
                .withCards(playerCardsList)
                .build();
    }

    private void validate(List<String> playerCardsList) {
        if (playerCardsList.size() != 5) {
            throw new RuntimeException();
        }
        playerCardsList.forEach(card -> {
            if (card.length() != 2) {
                throw new RuntimeException();
            }
        });
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
