package com.thecraftkid.apcs;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Willie Chalmers III
 * @since 11/9/17
 */
public class chalmersw14 {

    public static void main(String[] args) {

    }

    public static class CardPlayer {

        private Deck playerOneDeck = new Deck();
        private Deck playerTwoDeck = new Deck();

        public static void startWarGame() {

        }
    }

    public static class Deck {

        private List<ChalmersCard> cards = new ArrayList<>();

        public void fillDeck() {
//            cards.replaceAll(chalmersCard -> new ChalmersCard());
            while (cards.size() <= 52) {

                for (int value : ChalmersCard.POSSIBLE_VALUES) {

                }
            }
        }

        /**
         * Removes all cards from this deck.
         */
        public void clearDeck() {
            cards.removeIf(card -> true);
        }

        /**
         * Prints {@link ChalmersCard#toString()} to standard output for each
         * card in this deck.
         */
        public void displayCards() {
            System.out.println(this);
        }

        /**
         * Places each of the cards in this deck into a new random position.
         */
        public void shuffleDeck() {
            List<ChalmersCard> copy = new ArrayList<>();
            Collections.copy(copy, cards);
            List<Integer> randoms = generateRandomPositions();
        }

        private List<Integer> generateRandomPositions() {
            List<Integer> randoms = new ArrayList<>();
            while (randoms.size() <= 52) {
                int nextRandom = ThreadLocalRandom.current().nextInt(0, 52 + 1);
                if (randoms.get(nextRandom) == -1) {
                    randoms.add(nextRandom);
                }
            }
            return randoms;
        }

        /**
         * Returns a sorted copy of this deck.
         */
        public List<ChalmersCard> getSortedDeck() {
            return cards.stream()
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
        }

        /**
         * Returns {@link ChalmersCard#toString()} for each in this deck.
         */
        @Override
        public String toString() {
            return String.valueOf(cards);
        }
    }

    public static class ChalmersCard implements Comparable<ChalmersCard> {

        /**
         * Jack, queen, and king are worth 10.
         */
        private static final List<Integer> POSSIBLE_VALUES = Collections.unmodifiableList(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
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

        private final int value;
        private final String face;
        private final String suit;

        public ChalmersCard(int value, String face, String suit) {
            if (POSSIBLE_VALUES.stream().noneMatch(integer -> (value != integer))) {
                throw new IllegalArgumentException("Value " + value + " is not valid");
            }
            if (POSSIBLE_FACES.stream().noneMatch(string -> string.equals(face))) {
                throw new IllegalArgumentException("Value " + value + " is not valid");
            }
            if (POSSIBLE_SUITS.stream().noneMatch(string -> string.equals(suit))) {
                throw new IllegalArgumentException("Value " + value + " is not valid");
            }
            this.value = value;
            this.face = face;
            this.suit = suit;
        }

        /**
         * Returns the face and suit of the card. Example: A♦
         */
        public String getName() {
            return face + suit;
        }

        /**
         * Compares the face and suit of the given card to this one for sorting.
         *
         * @return -1 if the given card should be sorted before this card, 0 if
         * the given card is equal to this card, or 1 if the given card should
         * be sorted after this card.
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
            return getName() + "value: " + this.value;
        }
    }
}
