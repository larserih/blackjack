package no.teknisk.test.service;

import no.teknisk.test.model.Hand;
import no.teknisk.test.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ResultService {

    @SuppressWarnings("java:S106") /*Use logger instead of System.out*/
    public void announceWinner(List<Hand> hands) {
        String winnerName = hands.stream()
                .filter(Hand::isWinner)
                .map(Hand::getPlayer)
                .map(Player::getName)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There must be a winner name to announce"));

        Hand samsHand = getPlayersHand(hands, Player.SAM);
        Hand dealersHand = getPlayersHand(hands, Player.DEALER);

        String samsHandAsString = createHandAsString(samsHand);
        String dealersHandAsString = createHandAsString(dealersHand);

        System.out.println(winnerName);
        System.out.println(Player.SAM.getName().concat(": ").concat(samsHandAsString));
        System.out.println(Player.DEALER.getName().concat(": ").concat(dealersHandAsString));
    }

    private Hand getPlayersHand(List<Hand> hands, Player player) {
        return hands.stream()
                .filter(hand -> player.equals(hand.getPlayer()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("%s must have a hand to show", player.getName())));
    }

    private String createHandAsString(Hand hand) {
        List<String> cardsAsStrings = new ArrayList<>();
        hand.getCards()
                .forEach(card -> cardsAsStrings.add(card.getSuit() + card.getSymbol()));

        return String.join(",", cardsAsStrings);
    }
}
