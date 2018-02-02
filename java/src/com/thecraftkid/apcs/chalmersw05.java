package com.thecraftkid.apcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class that finds total imagined score of the previous four labs from user input.
 *
 * @author Willie Chalmers III
 * @since 9/18/17
 */
public class chalmersw05 {

    private static final Scanner sScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Mr. Burton\nLab 5 - 10 point version\n");
        GradeTracker tracker = new GradeTracker();
        HelloWorldLab.main(args);
        tracker.requestNextScore(sScanner);
        chalmersw02.main(args);
        tracker.requestNextScore(sScanner);
        chalmersw03.main(args);
        tracker.requestNextScore(sScanner);
        chalmersw04.main(args);
        tracker.requestNextScore(sScanner);
        tracker.printPoints();
        tracker.printAverage();
    }

    /**
     * A utility object that keeps track of the collected grades.
     */
    static class GradeTracker {

        private List<Integer> mGrades = new ArrayList<>();

        private static final int TOTAL_POINTS = 40;

        /**
         * Uses the given scanner to fetch the next score (an int).
         */
        public void requestNextScore(Scanner scanner) {
            System.out.print("\nWhat grade did you get on this lab?    ");
            mGrades.add(scanner.nextInt());
        }

        /**
         * Returns the sum of all the grades collected by this tracker.
         * </p>
         * In reality, this should be private.
         *
         * @return The total number of points contained in this tracker
         */
        public int calculateTotalPoints() {
            int total = 0;
            for (int grade : mGrades) {
                total += grade;
            }
            return total;
        }

        /**
         * Finds and returns the average of the grades this tracker's collected.
         */
        public double calculateAverage() {
            return calculateTotalPoints() / mGrades.size();
        }

        /**
         * Prints this tracker's total points to standard output.
         */
        public void printPoints() {
            System.out.println(String.format(
                    "\nYour lab points are %s out of %s", calculateTotalPoints(), TOTAL_POINTS));
        }

        /**
         * Prints the average out of 100 of the grades collected.
         */
        public void printAverage() {
            System.out.println("Your lab average is " + (int) calculateAverage() * 10);
        }
    }
}
