package com.thecraftkid.apcs;

import java.util.Scanner;

/**
 * A program that calculates the hypotenuse of a right triangle given two legs from user input
 * and calculates the circumference, area, and volume of a circle/sphere given a radius.
 * </p>
 * For extra credit, this also finds the largest value inputted by the user.
 *
 * @author Willie Chalmers III
 * @since 9/2/17
 */
public class chalmersw03 {

    private static Scanner sScanner = new Scanner(System.in);

    public static void main(String... args) {
        System.out.println("Mr. Burton\nLab 3 - 11 point version\n");
        double firstSide = getInput("Enter the first side of the triangle ");
        double secondSide = getInput("Enter the second side of the triangle ");
        double hypotenuse = calculateHypotenuse(firstSide, secondSide);
        System.out.println("\nThe hypotenuse of the triangle is " + hypotenuse + "\n\n");
        double radius = getInput("Enter the radius of the circle ");
        System.out.println("\nThe circumference of the circle is " + calculateCircumference(radius));
        System.out.println("The area of the circle is " + calculateArea(radius));
        System.out.println("The volume of the sphere is " + calculateVolume(radius));
        System.out.println("\n-------------------\nExtra Credit\n");
        double largestNumber = getLargestValue(firstSide, secondSide, radius);
        System.out.println("The largest number entered was " + largestNumber);
    }

    private static double calculateHypotenuse(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private static double calculateCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    private static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }

    private static double calculateVolume(double radius) {
        return (4 / 3) * Math.PI * Math.pow(radius, 3.0);
    }

    private static double getInput(String prompt) {
        System.out.print(prompt);
        return sScanner.nextDouble();
    }

    private static double getLargestValue(double... values) {
        double largestValue = 0.0;
        for (double value : values) {
            largestValue = Math.max(largestValue, value);
        }
        return largestValue;
    }
}
