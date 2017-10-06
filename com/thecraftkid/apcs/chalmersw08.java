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
     * Returns the user-inputted amount of money to start the game.
     */
    private static int requestStartingFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much money would you like to bring to Las Vegas?\t");
        return scanner.nextInt();
    }

    /**
     * A die game that follows these rules:
     * <ul>
     * <li>If the sum of two die rolls equals 7, win $4</li>
     * <li>If the sum of two die rolls does not equal 7, lose $1</li>
     * <li>Repeat until funds = 0</li>
     * </ul>
     */
    public static class DieGame {

        private static final int FUND_INCREMENT = 4;

        private static final int FUND_DECREMENT = 1;

        private Die die;

        private int funds;

        private int rolls;

        private int largestFundsValue;

        private DieGame(int startingFunds) {
            die = new StandardDie();
            funds = startingFunds;
            largestFundsValue = funds;
            rolls = 0;
        }

        /**
         * Starts a new instance of this game.
         *
         * @param startingFunds The amount of money to begin the game
         */
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
            System.out.println("Rolls: " + rolls + " Funds: " + funds);
            System.out.println(
                    String.format("You should have stopped at %s rolls when you had $%s.",
                    largestFundsValue, 0)); // FIXME: 10/5/17
        }

        /**
         * Returns this game's die
         *
         * @return Outputted value from {@link Die#roll()}
         */
        public int getNextRoll() {
            return die.roll();
        }

        public void onRoll(int first, int second) {
            System.out.println(first + " " + second);
            if (first + second == 7) {
                addFunds(FUND_INCREMENT);
            } else {
                subtractFunds(FUND_DECREMENT);
            }
            rolls++;
            System.out.println("Funds " + funds);
        }

        public void addFunds(int amount) {
            funds += amount;
        }

        public void subtractFunds(int amount) {
            funds -= amount;
        }
    }

    /**
     * A {@link Die} that only rolls numbers between 1 and 6, both inclusive.
     */
    public static class StandardDie extends Die {

        private static final int MAX_VALUE = 6;

        private static final int MIN_VALUE = 1;

        public StandardDie() {
            super(MAX_VALUE, MIN_VALUE);
        }
    }

    /**
     * An extensible die that generates random numbers.
     */
    public static class Die {

        private final int maxValue;

        private final int minValue;

        private int lastValue;

        /**
         * Creates a new die that rolls values from the given maximum to the the given minimum,
         * inclusive.
         *
         * @param maxValue The largest value this die can generate.
         * @param minValue The smallest value this die can generate.
         */
        public Die(int maxValue, int minValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

        /**
         * Returns a value between {@link #maxValue} and {@link #minValue}.
         * </p>
         * This also stores the returned value as the {@link #lastValue}.
         */
        public int roll() {
            lastValue = (int) (Math.random() * (maxValue - minValue + 1) + minValue);
            return lastValue;
        }

        @Override
        public String toString() {
            // Just for giggles
            return "Last roll: " + lastValue;
        }
    }
}
