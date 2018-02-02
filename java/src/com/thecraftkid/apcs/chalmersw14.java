package com.thecraftkid.apcs;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * A program that allows the user to simulate having and playing a deck of cards.
 *
 * @author Willie Chalmers III
 * @since 11/9/17
 */
public class chalmersw14 {

    public static void main(String[] args) {
        CardFlowManager.getInstance(new Deck()).start();
    }

    /**
     * A class that simulates two players playing a card game.
     */
    public static class CardPlayer {

        private CardPlayListener listener;

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        private Deck playerOneDeck = new Deck();
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        private Deck playerTwoDeck = new Deck();

        private CardPlayer() {
            this(null);
        }

        private CardPlayer(CardPlayListener listener) {
            this.listener = listener;
        }

        /**
         * Begins playing the <a href="http://en.wikipedia.org/wiki/War_(card_game)">War</a> card
         * game.
         */
        public static void startWarGame() {
            CardPlayer player = new CardPlayer();
            player.playWar();
        }

        private void playWar() {
            while (playerOneDeck.size() > 0 && playerTwoDeck.size() > 0) {
                ChalmersCard playerOneCard = playerOneDeck.pullCard();
                ChalmersCard playerTwoCard = playerTwoDeck.pullCard();
                if (playerOneCard.compareTo(playerTwoCard) > 0) {

                }

            }
        }

        private void playCard(String player, ChalmersCard card) {
            if (listener != null) {
                listener.onPlay(player, card);
            }
            System.out.printf("%s has played %s", player, card);
        }

        public interface CardPlayListener {
            void onPlay(String player, ChalmersCard card);
        }
    }

    /**
     * A program component that allows the user to manipulate a given deck.
     * <p>
     * <p>Options include:</p>
     * <p>
     * <ul> <li> Fill a deck of cards </li>
     * <p>
     * <li> Delete all cards from the deck </li>
     * <p>
     * <li> Display all cards in the deck </li>
     * <p>
     * <li> Pull card and remove it from the deck </li>
     * <p>
     * <li> Shuffle the deck </li>
     * <p>
     * <li> Sort the deck by value </li>
     * <p>
     * <li> Play a game of war using the cards in the deck. </li>
     * <p>
     * </ul>
     *
     * @see CardPlayer
     * @see ChalmersCard
     */
    public static class CardFlowManager {

        private static CardFlowManager INSTANCE;

        private static final int EXIT = 0;
        private static final int FILL_DECK = 1;
        private static final int DELETE_CARDS = 2;
        private static final int DISPLAY_CARDS = 3;
        private static final int PULL_CARD = 4;
        private static final int SHUFFLE_DECK = 5;
        private static final int SORT_DECK = 6;
        private static final int PLAY_WAR = 10;

        private final Deck deck;

        public CardFlowManager(Deck deck) {
            this.deck = deck;
        }

        /**
         * Returns the current instance of the CardFlowManager if it exists; otherwise, it returns a
         * new one with the {@link Deck} provided.
         */
        public static CardFlowManager getInstance(Deck deck) {
            if (INSTANCE == null) {
                INSTANCE = new CardFlowManager(deck);
            }
            return INSTANCE;
        }

        /**
         * Begins a loop that doesn't end until the user chooses the exit option.
         */
        public void start() {
            int input;
            displayOptions();
            while ((input = getInput("Choose an option: ")) != EXIT) {
                displayOptions();
                switch (input) {
                    case FILL_DECK:
                        launchFillDeckFlow();
                        break;
                    case DELETE_CARDS:
                        launchDeleteCardsFlow();
                        break;
                    case DISPLAY_CARDS:
                        launchDisplayCardsFlow();
                        break;
                    case PULL_CARD:
                        launchPullCardFlow();
                        break;
                    case SHUFFLE_DECK:
                        launchShuffleDeckFlow();
                        break;
                    case SORT_DECK:
                        launchSortDeckFlow();
                        break;
                    case PLAY_WAR:
                        launchPlayWarFlow();
                        break;
                }
            }
        }

        /**
         * @param prompt The text to display to the user.
         * @return The selected option
         */
        public int getInput(String prompt) {
            out.println(prompt);
            int input;
            try {
                input = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please choose a valid selection.");
                return getInput(prompt);
            }
            return input;
        }

        public void launchFillDeckFlow() {
            out.println("Filling deck with valid card combinations...");
            deck.fill();
            System.out.println(deck);
            out.println("Done.");
        }

        public void launchDeleteCardsFlow() {
            out.println("Deleting all cards in deck...");
            while (deck.size() > 0) {
                deck.remove(0);
            }
        }

        public void launchDisplayCardsFlow() {
            out.println("These are all the cards in the deck: ");
            out.println(deck);
        }

        public void launchPullCardFlow() {
            ChalmersCard card = deck.pullCard();
            out.printf("Your card is %s", card);
            out.println();
        }

        public void launchShuffleDeckFlow() {
            out.println("Shuffling cards...");
            deck.shuffle();
            out.println("Deck is now shuffled.");
        }

        public void launchSortDeckFlow() {
            out.println("Sorting deck:");
            deck.getSortedDeck().forEach(out::println);
            out.println("Deck is now sorted.");
        }

        /**
         * Starts a game of war.
         *
         * @see CardPlayer#playWar()
         */
        public void launchPlayWarFlow() {
            out.println("Starting game of war...");
            CardPlayer.startWarGame();
        }

        private void displayOptions() {
            System.out.println(
                    "1) Fill a deck of cards\n" +
                            "2) Delete all cards in deck\n" +
                            "3) Display all cards in deck\n" +
                            "4) Pull a random card in the deck\n" +
                            "5) Shuffle the deck\n" +
                            "6) Sort the deck by card value\n" +
                            "10) Watch a game of War between two decks");
        }
    }

    public static class Deck extends ArrayList<ChalmersCard> {

        /**
         * Fills this deck with all valid card combinations.
         */
        public void fill() {
            ChalmersCard.POSSIBLE_SUITS.forEach(suit -> ChalmersCard.POSSIBLE_FACES.stream()
                    .map(face -> new ChalmersCard(face, suit)).forEach(this::add));
        }

        /**
         * Places each of the cards in this deck into a new random position.
         */
        public void shuffle() {
            Collections.shuffle(this); // Can't I just do this?
            for (int i = 0; i < this.size(); i++) {
                int index = (int) (Math.random() * 52 + 1);
                add(0, remove(index));
            }
        }

        public ChalmersCard pullCard() {
            int cardIndex = ThreadLocalRandom.current().nextInt(0, size() - 1);
            return remove(cardIndex);
        }

        /**
         * Returns a sorted copy of this deck.
         */
        public List<ChalmersCard> getSortedDeck() {
            return stream()
                    .sorted(Comparator.naturalOrder()) // Use the ChalmersCard#compareTo(ChalmersCard)
                    .collect(Collectors.toList());
        }

        /**
         * Returns {@link ChalmersCard#toString()} for each in this deck.
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * A simple playing card, like a ace of spaces, or a 10 of diamonds.
     */
    public static class ChalmersCard implements Comparable<ChalmersCard> {

        /**
         * Jack, queen, and king are worth 10.
         */
        private static final List<Integer> POSSIBLE_VALUES = Collections.unmodifiableList(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)); // TODO: Find more efficient way

        /**
         * Consist of A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
         */
        private static final List<String> POSSIBLE_FACES = Collections.unmodifiableList(
                Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));

        /**
         * Consist of ace, club, heart, diamond, respectively
         */
        private static final List<String> POSSIBLE_SUITS = Collections.unmodifiableList(
                Arrays.asList("\3", "\4", "\5", "\6"));

        private int value;
        private final String face;
        private final String suit;

        public ChalmersCard(String face, String suit) {
            if (POSSIBLE_FACES.stream().noneMatch(string -> string.equals(face))) {
                throw new IllegalArgumentException("Face " + face + " is not valid");
            }
            if (POSSIBLE_SUITS.stream().noneMatch(string -> string.equals(suit))) {
                throw new IllegalArgumentException("Suit " + suit + " is not valid");
            }
            this.face = face;
            this.suit = suit;
            this.value = calculateValue(face);
        }

        private static int calculateValue(String face) {
            int newValue;
            try {
                newValue = Integer.parseInt(face);
            } catch (Exception e) {
                // If it's NaN, it could be an ace, king, queen, or jack.
                switch (face) {
                    case "A":
                        newValue = 1;
                        break;
                    case "K":
                    case "Q":
                    case "J":
                        newValue = 10;
                        break;
                    default:
                        throw new IllegalArgumentException("Given suit isn't valid.");
                }
            }
            return newValue;
        }

        /**
         * Returns the face and suit of the card. Example: A♦
         */
        public String getName() {
            return face + suit;
        }

        public int getValue() {
            return value;
        }

        public String getFace() {
            return face;
        }

        public String getSuit() {
            return suit;
        }

        /**
         * Compares the face and suit of the given card to this one for sorting.
         *
         * @return -1 if the given card should be sorted before this card, 0 if the given card is
         * equal to this card, or 1 if the given card should be sorted after this card.
         */
        @Override
        public int compareTo(ChalmersCard card) {
            final int GREATER = 1;
            final int LESSER = -1;
            final int EQUAL = 0;

            // Check for suits
            if (card.suit.compareTo(suit) > 0) {
                return GREATER;
            } else if (card.suit.compareTo(suit) < 0) {
                return LESSER;
            }

            // Check for values
            if (card.value > value) {
                return GREATER;
            } else if (card.value < value) {
                return LESSER;
            }
            return EQUAL;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
