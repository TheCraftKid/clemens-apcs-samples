package com.thecraftkid.apcs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A program that finds the prime factors of a user defined integer.
 *
 * @author Willie Chalmers III
 * @since 12/7/17
 */
public class chalmersw18 {

    public static void main(String[] args) {
        int given = getNumber();
        System.out.printf("Computing prime factors for %s...\n", given);
        findPrimeFactors(given);

        System.out.printf("Computing Fibonacci number for %s...\n", given);
        System.out.printf("It's %s\n", findFibonacci(given));
    }

    public static int getNumber() {
        System.out.print("Input a number: ");
        int number;
        try {
            number = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Integers only.");
            number = getNumber();
        }
        System.out.println();
        return number;
    }

    public static void findPrimeFactors(int n) {
        int d = 0;
        for (int i = 2; i < n / 2; i++) { // Start at first prime integer, end halfway
            d++;
            if (n % d == 0) {
                System.out.println(n);
                i = n / d;
                findPrimeFactors(n);
            }
            System.out.println(n);
        }
    }

    /**
     * Computes the Fibonacci number for the given input.
     * <p>
     * More specifically, this computes a number such that the returned value
     * is the sum of the returned value of the number minus 1.
     * </p>
     *
     * @param n
     */
    public static int findFibonacci(int n) {
        if (n < 1) {
            return 1;
        }
        return findFibonacci(n - 1) + findFibonacci(n - 2);
    }
}