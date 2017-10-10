package com.thecraftkid.apcs;

import java.util.Scanner;

/**
 * A program that reads in an amount of pennies (as an integer) and converts the pennies to the
 * lowest form of change.
 *
 * @author Willie Chalmers III
 * @since 10/10/17
 */
public class chalmersw09 {

    public static void main(String[] args) {
        int startingPennies = getPennyCount();
        CoinCounter counter = new CoinCounter(startingPennies);
        counter.calculateChange();
    }

    public static int getPennyCount() {
        System.out.println("How many pennies do you have?\t");
        return new Scanner(System.in).nextInt();
    }

    public static class CoinCounter {

        private int funds;

        public CoinCounter(int startingPennies) {
            funds = startingPennies;
        }

        public void calculateChange() {
            // FIXME: 10/10/2017 Subtract from running total
            int dollars = calculateDollars(funds);
            int quarters = calculateQuarters(funds);
            int dimes = calculateDimes(funds);
            int nickels = calculateNickels(funds);
            int pennies = 0;
            System.out.println("Dollars:\t" + dollars);
            System.out.println("Quarters:\t" + quarters);
            System.out.println("Dimes:\t" + dimes);
            System.out.println("Nickels:\t" + nickels);
            System.out.println("Pennies:\t" + pennies);
        }

        public int calculateDollars(int pennies) {
            return pennies % 100;
        }

        public int calculateNickels(int pennies) {
            return pennies % Coin.NICKEL.getValue();
        }

        public int calculateDimes(int pennies) {
            return pennies % Coin.DIME.getValue();
        }

        public int calculateQuarters(int pennies) {
            return pennies % Coin.QUARTER.getValue();
        }

        // TODO: 10/10/2017 Find better use for this
        public enum Coin {
            PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

            private int value;

            Coin(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return this.name().toLowerCase() + " worth $0." + value;
            }

            public int getValue() {
                return value;
            }
        }
    }
}
