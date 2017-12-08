package com.thecraftkid.apcs;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A program that finds the prime factors of a user defined integer.
 *
 * @author Willie Chalmers III
 * @since 12/7/17
 */
public class chalmersw18 {

    public static void main(String[] args) {
        int start = getNumber();
        System.out.printf("Computing Fibonacci number for %s...\n", start);
        System.out.printf("It's %s\n", findFibonacci(start));
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

    public static List<Integer> findPrimeFactors(int n) {

        return new ArrayList<>();
    }

    /**
     * Computes the Fibonacci number for the given input.
     * <p>
     * More specifically, this computes a number such that the returned value
     * is the sum of the returned value of the number minus 1
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
