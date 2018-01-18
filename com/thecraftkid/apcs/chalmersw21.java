package com.thecraftkid.apcs;

import com.thecraftkid.apcs.chalmersw14.Deck;

import javax.smartcardio.Card;
import java.util.Scanner;

public class chalmersw21 {

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame(new InputCallback() {
            @Override
            public int onBetRequest() {
                return getNumberInput("Your bet?");
            }

            @Override
            public Move onMove() {
//                String input;
////                while ((input = getStringInput("Your move?")) != Move.values())
                return null;
            }
        });
        game.start();
    }

    public static int getNumberInput(String prompt) {
        System.out.println(prompt);
        int result;
        try {
            result = new Scanner(System.in).nextInt();
        } catch (Exception e) {
            result = getNumberInput(prompt);
        }
        return result;
    }

    public static String getStringInput(String prompt) {
        System.out.println(prompt);
        String result;
        try {
            result = new Scanner(System.in).next();
        } catch (Exception e) {
            result = getStringInput(prompt);
        }
        return result;
    }

    public static class BlackjackGame {

        private static final int STARTING_AMOUNT = 100;

        private int funds = STARTING_AMOUNT;

        private InputCallback callback;

        private Deck deck = new Deck();

        public BlackjackGame(InputCallback callback) {
            this.callback = callback;
        }

        public void start() {
            int bet = callback.onBetRequest();
            while (bet > 0 && bet < funds) {
                bet = callback.onBetRequest();
                Card[] dealerCards = new Card[2];
                Card[] playerCards = new Card[2];
                displayCards(playerCards);
                Move nextMove = callback.onMove();

            }
        }

        private void displayCards(Card[] cards) {
            System.out.printf("You cards are %s, %s", cards[0], cards[1]);
        }
    }

    public interface InputCallback {

        int onBetRequest();

        Move onMove();
    }

    public enum Move {
        HIT,
        STAND;
    }
}
