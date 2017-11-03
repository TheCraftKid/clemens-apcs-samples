package com.thecraftkid.apcs;

import java.util.HashMap;
import java.util.Map;
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
        Map<CoinCounter.Coin, Integer> coins = counter.calculateChange();
        printChange(coins);
    }

    public static void printChange(Map<CoinCounter.Coin, Integer> countAmounts) {
        for (Map.Entry<CoinCounter.Coin, Integer> entry : countAmounts.entrySet()) {
            System.out.println(entry.getKey() + "s: " + entry.getValue());
        }
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

        public Map<Coin, Integer> calculateChange() {
            Map<Coin, Integer> coins = new HashMap<>();
            int pennies = funds;
            while (pennies > 0) {
                // TODO: 10/13/2017 Finish me
            }
            // FIXME: 10/10/2017 Subtract from running total
            int dollars = getExtraPenniesFromDollar(funds);
            int quarters = getExtraPenniesFromQuarter(funds);
            int dimes = getExtraPenniesFromDime(funds);
            int nickels = getExtraPenniesFromNickel(funds);
            System.out.println("Dollars:\t" + dollars);
            System.out.println("Quarters:\t" + quarters);
            System.out.println("Dimes:\t" + dimes);
            System.out.println("Nickels:\t" + nickels);
            System.out.println("Pennies:\t" + pennies);
            return coins;
        }

        public int getExtraPenniesFromDollar(int pennies) {
            return pennies % 100;
        }

        public int getExtraPenniesFromNickel(int pennies) {
            return pennies % Coin.NICKEL.getValue();
        }

        public int getExtraPenniesFromDime(int pennies) {
            return pennies % Coin.DIME.getValue();
        }

        public int getExtraPenniesFromQuarter(int pennies) {
            return pennies % Coin.QUARTER.getValue();
        }

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
