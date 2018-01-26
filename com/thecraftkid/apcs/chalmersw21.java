package com.thecraftkid.apcs;

import com.thecraftkid.apcs.chalmersw14.ChalmersCard;
import com.thecraftkid.apcs.chalmersw14.Deck;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A program that allows the user to play a game of blackjack on the console.
 */
public class chalmersw21 {

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame(new InputCallback() {

            @Override
            public int onBetRequest(int max) {
                System.out.printf("Your wallet: $%s\n", max);
                int bet = getNumberInput("Your bet?");
                while (bet > max) {
                    bet = getNumberInput(String.format("You don't have $%s", bet));
                }
                return bet;
            }

            @Override
            public Move onMove() {
                return requestMove();
            }

            @Override
            public boolean onGameEnd() {
                System.out.println("You");
                requestPlayAgain();
                return false;
            }

            @Override
            public int onTooLargeBetRequest(int max) {
                System.out.printf("You only have %s to bet. Bet again.\n", max);
                return InputCallback.super.onTooLargeBetRequest(max);
            }

            /**
             * Keeps requesting input until a valid input is inputted.
             *
             * @return A valid, non-null {@link Move}
             */
            private Move requestMove() {
                String input = getStringInput("Your move? (Hit or stand)");
                Move move;
                try {
                    switch (input.toLowerCase()) {
                        case "hit":
                            move = Move.HIT;
                            break;
                        case "stand":
                            move = Move.STAND;
                            break;
                        default:
                            throw new Exception(); // Pass through
                    }
                } catch (Exception e) {
                    System.out.println(input + " is not a valid move");
                    move = requestMove();
                }
                return move;
            }

            private boolean requestPlayAgain() {
                String output = getStringInput("Would you like to play again? (y/N)");
                boolean result = false;
                try {
                    result = Boolean.valueOf(output);
                } catch (Exception e) {
                    result = requestPlayAgain();
                }
                return result;
            }
        });
        game.setDisplayCallback(new DisplayCallback() {

            @Override
            public void onDisplayPlayerCards(ChalmersCard... cards) {
                System.out.printf("Your cards are %s and %s\n", cards[0], cards[1]);
            }

            @Override
            public void onDisplayDealerCards(ChalmersCard... cards) {
                System.out.printf("Dealer has %s and %s\n", cards[0], cards[1]);
            }

            @Override
            public void onPlayerPullCard(ChalmersCard card) {
                System.out.printf("You pulled %s\n", card);
            }

            @Override
            public void onDealerPullCard(ChalmersCard card) {
                System.out.printf("Dealer pulled %s\n", card);
            }

            @Override
            public void onBust(int lastValue, int bustValue) {
                System.out.printf("You busted at %s\n", bustValue);
                System.out.printf("You should have stopped at %s\n", lastValue);
            }

            @Override
            public void onSuccessfulDealerRound(int lastValue) {
                System.out.printf("Dealer stopped at %s.\n", lastValue);
            }

            @Override
            public void onSuccessfulPlayerRound(int lastValue) {
                System.out.printf("You didn't bust! Last value: %s\n", lastValue);
            }
        });
        game.play();
    }

    /**
     * Requests the user to enter a number.
     *
     * @param prompt Text to display to the user before requesting input
     */
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

    /**
     * Requests the user to enter a String.
     *
     * @param prompt Text to display to the user before requesting input
     * @return A non-null String
     */
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

        private DisplayCallback displayCallback;

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        private final Deck deck;

        public BlackjackGame(InputCallback callback) {
            this.callback = callback;
            this.deck = new Deck();
            this.deck.fill();
        }

        public void setDisplayCallback(DisplayCallback callback) {
            this.displayCallback = callback;
        }

        /**
         * Begins playing this game.
         * <p>
         * Starts the game loop by requesting for a starting funds amount and
         * then showing the user his/her cards.
         */
        public void play() {
            int playerBet = callback.onBetRequest(funds);
            while (playerBet > 0) {
                if (playerBet > funds) {
                    playerBet = callback.onTooLargeBetRequest(funds);
                }
                // Start player round
                ChalmersCard[] playerCards = generateStartingPair();
                displayPlayerCards(playerCards);
                RoundResult result = startBetting(playerCards);
                displayPlayerRoundResults(result);
                // Start dealer round
                ChalmersCard[] dealerCards = generateStartingPair();
                displayDealerCards(dealerCards);
                RoundResult dealerResult = startDealerRound(dealerCards);
                displayDealerRoundResults(dealerResult);
            }
            callback.onGameEnd();
        }

        private ChalmersCard[] generateStartingPair() {
            ChalmersCard[] dealerCards = new ChalmersCard[2]; // Don't show these
            dealerCards[0] = deck.pullCard();
            dealerCards[1] = deck.pullCard();
            return dealerCards;
        }

        /**
         * Begins the standard blackjack loop of asking the user to hit or stand.
         *
         * @param playerCards Length <= 2
         * @return True if the player busted
         */
        private RoundResult startBetting(ChalmersCard... playerCards) {
            List<ChalmersCard> cards = Arrays.stream(playerCards).collect(Collectors.toList());
            Move nextMove = callback.onMove();
            int lastValue = cards.get(0).getValue() + cards.get(1).getValue();
            int bustedValue = RoundResult.NOT_BUSTED;
            while (lastValue < 21) {
                if (nextMove == Move.HIT) {
                    ChalmersCard newCard = deck.pullCard();
                    displayPulledCard(newCard);
                    bustedValue = (lastValue += newCard.getValue());
                } else {
                    return new RoundResult(false, lastValue, bustedValue);
                }
            }
            return new RoundResult(true, lastValue, bustedValue);
        }

        private void displayPulledCard(ChalmersCard newCard) {
            if (displayCallback != null) {
                displayCallback.onPlayerPullCard(newCard);
            }
        }

        private RoundResult startDealerRound(ChalmersCard... dealerCards) {
            List<ChalmersCard> cards = Arrays.stream(dealerCards).collect(Collectors.toList());
            int lastValue = cards.get(0).getValue() + cards.get(1).getValue();
            int bustedValue = RoundResult.NOT_BUSTED;
            while (lastValue <= 17) {
                ChalmersCard card = deck.pullCard();

                lastValue += card.getValue();
                if (lastValue > 21) {
                    bustedValue = lastValue;
                }
            }
            boolean isBusted = bustedValue != RoundResult.NOT_BUSTED;
            return new RoundResult(isBusted, lastValue, bustedValue);
        }

        /**
         * Only runs if {@link #displayCallback} is non-null.
         */
        private void displayPlayerRoundResults(RoundResult result) {
            if (displayCallback != null) {
                if (result.isBusted()) {
                    displayCallback.onBust(result.getLastGoodValue(), result.getBustedValue());
                } else {
                    displayCallback.onSuccessfulDealerRound(result.getLastGoodValue());
                }
            }
        }

        /**
         * Only runs if {@link #displayCallback} is non-null.
         */
        private void displayDealerRoundResults(RoundResult result) {
            if (displayCallback != null) {
                if (result.isBusted()) {
                    displayCallback.onBust(result.getLastGoodValue(), result.getBustedValue());
                } else {
                    displayCallback.onSuccessfulDealerRound(result.getLastGoodValue());
                }
            }
        }

        /**
         * Only runs if {@link #displayCallback} is non-null.
         */
        private void displayPlayerCards(ChalmersCard... cards) {
            if (displayCallback != null) {
                displayCallback.onDisplayPlayerCards(cards);
            }
        }

        /**
         * Only runs if {@link #displayCallback} is non-null.
         */
        private void displayDealerCards(ChalmersCard... cards) {
            if (displayCallback != null) {
                displayCallback.onDisplayDealerCards(cards);
            }
        }

        /**
         * A container for betting results.
         *
         * @see DisplayCallback#onBust(int, int)
         */
        private static class RoundResult {

            public static final int NOT_BUSTED = -1;

            private final boolean busted;

            private final int lastGoodValue;

            private final int bustedValue;

            private RoundResult(boolean busted, int lastGoodValue, int bustedValue) {
                this.busted = busted;
                this.lastGoodValue = lastGoodValue;
                this.bustedValue = bustedValue;
            }

            public boolean isBusted() {
                return busted;
            }

            public int getLastGoodValue() {
                return lastGoodValue;
            }

            /**
             * @return -1 if the betting round did not bust
             */
            public int getBustedValue() {
                return bustedValue;
            }
        }
    }

    /**
     * A required callback used to request values important to the game.
     */
    public interface InputCallback {

        /**
         * Requests a user's input for the amount of dollars to bet.
         *
         * @return The amount of dollars the player has bet for the next turn
         */
        int onBetRequest(int max);

        /**
         * Requests a user's selection for the next turn in blackjack.
         *
         * @return An always non-null {@link Move}
         */
        Move onMove();

        /**
         * Called when the game has ended.
         *
         * @return True if the game should restart
         */
        boolean onGameEnd();

        /**
         * Notifies this callback that the provided bet in {@link #onBetRequest(int)} was too large.
         * <p>
         * By default, the limit is
         */
        default int onTooLargeBetRequest(int max) {
            return onBetRequest(max);
        }
    }

    /**
     * An optional callback notified on user-relevant game state changes.
     */
    public interface DisplayCallback {

        /**
         * Prints the player's cards to standard output.
         *
         * @param cards The player's current cards.
         */
        void onDisplayPlayerCards(ChalmersCard... cards);

        /**
         * Prints the dealer's cards to standard output.
         *
         * @param cards The dealer's current cards
         */
        void onDisplayDealerCards(ChalmersCard... cards);

        /**
         * Prints the given card to standard output.
         *
         * @param card A card most recently pulled by the user
         */
        void onPlayerPullCard(ChalmersCard card);

        /**
         * Prints the given card to standard output.
         *
         * @param card A card most recently pulled by the user
         */
        void onDealerPullCard(ChalmersCard card);

        /**
         * Notifies this callback that the playing agent has passed total card
         * value of 21.
         *
         * @param lastValue The amount of dollars the agent had before
         *                  he/she busted
         * @param bustValue The amount of dollars the agent had after
         *                  he/she busted
         */
        void onBust(int lastValue, int bustValue);

        /**
         * Notifies this callback that the playing agent has not busted.
         *
         * @param lastValue The sum of card values the agent had before
         *                  he/she busted.
         */
        void onSuccessfulDealerRound(int lastValue);

        void onSuccessfulPlayerRound(int lastValue);
    }

    /**
     * A set of turn types that a player can make during the game of blackjack.
     */
    public enum Move {

        /**
         * The player wants to receive another {@link ChalmersCard}.
         */
        HIT,

        /**
         * The player is satisfied with his/her cards.
         */
        STAND;
    }
}
