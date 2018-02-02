package com.thecraftkid.apcs;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * A program that prints out the prime numbers
 *
 * @author Willie Chalmers III
 * @since 10/16/17
 */
public class chalmersw10 {

    private static final int START = 0;

    private static final int END = 200;

    public static void main(String[] args) {
        out.println("These numbers are prime:");
        // Use fancy Java 8 features even though Java 9 is out
        fetchPrimesTo(START, END).forEach(out::println);
    }

    public static List<Integer> fetchPrimesTo(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) { /*That <= nearly screwed me. It turns out I did this right the first time*/
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        return primes;
    }


    public static List<Integer> fetchCachedPrimes(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        return primes;
    }

}
