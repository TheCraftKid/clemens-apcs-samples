package com.thecraftkid.apcs;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * A class that prints the averages of multiple test scores and their average.
 * </p>
 * In addition to fulfilling the requirements, this allows the option to scrub inputs and allow for
 * multiple iterations of asking for grades.
 *
 * @author Willie Chalmers III
 * @since 9/7/17
 */
public class chalmersw02 {

    private static Scanner sScanner;

    /**
     * Change this to change the amount of tests to process
     */
    private static final int TEST_COUNT = 3;

    // Experimental flags
    private static final boolean ASK_FOR_CONTINUED_CALCULATIONS = true;
    private static final boolean HANDLE_ERRORS = true;

    /**
     * Uses user input from {@link System#in} to calculate test averages.
     */
    public static void main(String... args) {
        out.println("Mr. Burton\nLab 2 - 10 point version \n");
        loop();
    }

    private static void loop() {
        sScanner = new Scanner(System.in);
        double[] averages = new double[TEST_COUNT];
        // Based on the number of tests to grade, ask correct question # out of total question #
        for (int i = 0; i < TEST_COUNT; i++) {
            double average = calculateTestAverage(
                    getNumberInput(sScanner, "How many did you get right on the test?"),
                    getNumberInput(sScanner, "How many questions were on the test?"));
            averages[i] = average;
        }
        printResults(averages);
//        handleAdditionalCalculations();
    }

    private static void handleAdditionalCalculations() {
        if (ASK_FOR_CONTINUED_CALCULATIONS) {
            System.out.println("Would you like to calculate another batch? y/(n)");
            String result = sScanner.next();
            // Confirm choice
            for (String option : new String[]{"y", "ye", "yes",}) {
                if (result.equalsIgnoreCase(option)) {
                    loop();
                }
            }
        }
    }

    /**
     * Loops through the available tests and prints out their averages.
     *
     * @param testAverages A list of test scores
     */
    private static void printResults(double... testAverages) {
        out.println("\n-------------------");
        for (int i = 0; i < testAverages.length; i++) {
            out.println("Test " + i + ": " + getPercentFromDecimal(testAverages[i]));
        }
        out.println("-------------------");
        out.println("Test Average: " + getPercentFromDecimal(calculateAverage(testAverages)));
    }

    /**
     * Truncates and converts a decimal to a percentage to one decimal place.
     *
     * @return The percentage (e.g.: 33.3%)
     */
    private static String getPercentFromDecimal(double decimal) {
        String percent = String.valueOf(decimal * 100);
        // Truncate string to tenths
        int periodPosition = percent.indexOf(".");
        percent = percent.substring(0, periodPosition + 2);
        return String.format("%s%%", percent);
    }

    /**
     * Retrieves the next integer from the provided {@link Scanner}.
     * </p>
     * This handles user input errors (e.g.: Asks for more input when it is "pie" when 2 is expected)
     *
     * @param scanner The data source of test averages
     * @param prompt  The prompt to display to the user (e.g.: How many X were on Y?)
     * @return The inputted number
     */
    private static int getNumberInput(Scanner scanner, String prompt) {
        out.println(prompt);
        if (HANDLE_ERRORS) {
            String next = scanner.next();
            int input;
            try {
                input = Integer.parseInt(next);
            } catch (IllegalArgumentException e) {
                out.println(String.format("Input \"%s\" is not a number.", next));
                input = getNumberInput(scanner, prompt);
            }
            return input;
        } else {
            return scanner.nextInt();
        }
    }

    /**
     * Calculates the average percentage of questions correct.
     *
     * @param questionsRight The amount of questions answered correctly
     * @param questionCount  The amount of questions on the given test
     * @return The decimal percentage of correctly answered questions
     */
    private static double calculateTestAverage(int questionsRight, int questionCount) {
        return (double) questionsRight / questionCount;
    }

    /**
     * Calculates the mathematical average of the given values.
     * </p>
     * This does the equivalent of {@code (values[0] + values[1]) / values.length}
     */
    private static double calculateAverage(double... values) {
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total / values.length;
    }
}