package com.thecraftkid.apcs;

import java.util.Scanner;

/**
 * A program that simulates rolling two die until funds are depleted.
 *
 * @author Willie Chalmers III
 * @since 10/2/17
 */
public class chalmersw08 {

    public static void main(String[] args) {
        int startingFunds = requestStartingFunds();
        // Pass off responsibility to DieGame
        DieGame.startNewGame(startingFunds);
    }

    /**
     * Returns the user-inputted
     */
    private static int requestStartingFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much money would you like to bring to Las Vegas?\t");
        return scanner.nextInt();
    }

    /**
     * The implementation of a die game that follows these rules:
     * <ul>
     * <li>If the sum of two die rolls equals 7, win $4</li>
     * <li>If the sum of two die rolls does not equal 7, lose $1</li>
     * <li>Repeat until funds = 0</li>
     * </ul>
     */
    public static class DieGame {

        private static final int FUND_INCREMENT = 7;

        private static final int FUND_DECREMENT = 1;

        private Die die;

        private int funds;

        private int rolls;

        /**
         * Used
         */
        private DieRollCallback defaultCallback = (first, second) -> {
            System.out.println(first + " " + second);
            if (first + second == 7) {
                addFunds(FUND_INCREMENT);
            } else {
                subtractFunds(FUND_DECREMENT);
            }
            rolls++;
        };

        private DieGame(int startingFunds) {
            die = new Die();
            funds = startingFunds;
            rolls = 0;
        }

        /**
         * Starts a new DieGame instance
         */
        public static DieGame startNewGame(int startingFunds) {
            return startNewGame(startingFunds, null);
        }

        /**
         * Starts a new DieGame instance with a {@link DieRollCallback}
         */
        public static DieGame startNewGame(int startingFunds, DieRollCallback callback) {
            DieGame game = new DieGame(startingFunds);
            while (game.funds >= 0) {
                int firstRoll = game.getNextRoll();
                int secondRoll = game.getNextRoll();
                if (callback != null) {
                    callback.onRoll(firstRoll, secondRoll);
                } else {
                    game.defaultCallback.onRoll(firstRoll, secondRoll);
                }
            }
            game.displayGameOver();
            return game;
        }

        private void displayGameOver() {
            System.out.println("Rolls: " + rolls + " Funds: " + funds);
        }

        public int getNextRoll() {
            return die.roll();
        }

        public void addFunds(int amount) {
            funds += amount;
        }

        public void subtractFunds(int amount) {
            funds -= amount;
        }

        public interface DieRollCallback {

            /**
             * Called when the game rolls two dice.
             */
            void onRoll(int rollOne, int rollTwo);
        }
    }

    public static class Die {

        private static final int MAX_VALUE = 6;

        private static final int MIN_VALUE = 1;

        private int lastValue;

        public int roll() {
            lastValue = (int) (Math.random() * (MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
            return lastValue;
        }

        @Override
        public String toString() {
            // Just for giggles
            return "Last roll: " + lastValue;
        }
    }
}
