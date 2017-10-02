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

    private static int requestStartingFunds() {
        Scanner scanner = new Scanner(System.in);
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

        private DieGame(int startingFunds) {
            die = new Die();
            funds = startingFunds;
            rolls = 0;
        }

        public static void startNewGame(int startingFunds) {
            DieGame game = new DieGame(startingFunds);
            while (game.funds >= 0) {
                int firstRoll = game.getNextRoll();
                int secondRoll = game.getNextRoll();
                game.onRoll(firstRoll, secondRoll);
            }
            game.displayGameOver();
        }

        private void displayGameOver() {

        }

        public int getNextRoll() {
            return die.roll();
        }


        public void onRoll(int first, int second) {
            if (first + second == 7) {
                addFunds(FUND_INCREMENT);
            } else {
                subtractFunds(FUND_DECREMENT);
            }
        }

        public void addFunds(int amount) {
            funds += amount;
        }

        public void subtractFunds(int amount) {
            funds -= amount;
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
