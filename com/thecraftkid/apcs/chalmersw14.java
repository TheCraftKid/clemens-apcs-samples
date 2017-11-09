package com.thecraftkid.apcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Willie Chalmers III
 * @since 11/9/17
 */
public class chalmersw14 {

    public static void main(String[] args) {

    }

    public static class CardPlayer {
        private List<ChalmersCard> cards = new ArrayList<>(52);


        public void fillDeck() {
            cards.replaceAll(chalmersCard -> new ChalmersCard());
        }

        public void deleteCards() {
            cards.removeIf(card -> true);
        }

        @Override
        public String toString() {
            cards.stream()
                    .
        }

        public void displayCards() {
            System.out.println(this);
        }

        public void shuffleDeck() {

        }

        public void sortByValue() {

        }
    }

    public static class ChalmersCard implements Comparable {
        private static final List<Integer> POSSIBLE_VALUES = Collections.unmodifiableList(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        private static final List<String> POSSIBLE_FACES = Collections.unmodifiableList(
                Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q"));
        private static final List<String> POSSIBLE_SUITS = Collections.unmodifiableList(
                Arrays.asList("\3", "\4", "\5", "\6")); // Escapes for
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

        public String getName() {
            return face + suit;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        @Override
        public String toString() {
            return getName() + "value: " + this.value;
        }
    }
}
