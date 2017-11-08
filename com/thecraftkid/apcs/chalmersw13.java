package com.thecraftkid.apcs;

import com.thecraftkid.apcs.given.LabHelper;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A program the implements binary and sequential sort
 *
 * @author Willie Chalmers III
 * @since 11/8/17
 */
public class chalmersw13 {

    public static void main(String[] args) {
        int[] list = LabHelper.lab12();
        int[] sorted = Searcher.sort(list);
        int choice = getSearchChoice();
        int position = Searcher.doBinarySearch(list, choice);
//        int position = Arrays.binarySearch(list, getSearchChoice()); // Credit not received for this
        System.out.println("Original array:");
        System.out.println(Arrays.toString(list));
        if (position == -1) {
            System.out.println("Number wasn't found");
        } else {
            System.out.println("Your number was found at " + position);
        }
        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(sorted));
        int otherPosition = Searcher.doBinarySearch(sorted, choice);
        if (position == -1) {
            System.out.println("Number wasn't found");
        } else {
            System.out.println("Your number was found at " + otherPosition);
        }

    }

    private static int getSearchChoice() {
        System.out.println("What number would you like to search for?");
        return new Scanner(System.in).nextInt();
    }

    public static class Searcher {

        /**
         * Performs a binary search on the given list.
         * </br>
         * TODO: Document this
         *
         * @param list     The list to search through
         * @param toSearch The value to find the in the list
         * @return The index of the given number to search, -1 if it doesn't exist
         */
        public static int doBinarySearch(int[] list, int toSearch) {
            int lowestIndex = 0;
            int highestIndex = list.length;
            while (lowestIndex + 1 < highestIndex) {
                int testIndex = (lowestIndex + highestIndex) / 2;
                if (list[testIndex] > toSearch) {
                    highestIndex = testIndex;
                } else {
                    lowestIndex = testIndex;
                }
            }
            if (list[lowestIndex] < toSearch) {
                return lowestIndex;
            } else {
                return -1;
            }
        }

        /**
         * Performs a sequential search on the given list.
         * </br>
         * This loops through the list and returns the index of the current item
         * if it equals the given number.
         *
         * @param list     The list to search through
         * @param toSearch The value to find the in the list
         * @return The index of the given number to search, -1 if it doesn't exist
         */
        public static int doSequentialSearch(int[] list, int toSearch) {
            for (int i = 0; i < list.length; i++) {
                if (list[i] == toSearch) {
                    return i;
                }
            }
            return -1;
        }

        private static int[] sort(int[] list) {
            return new chalmersw12.Sorter(list).sortInsertion();
        }
    }
}
