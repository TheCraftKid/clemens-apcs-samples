package com.thecraftkid.apcs;

import java.util.Scanner;

/**
 * A program that finds and display the perimeter of 5 triangles and 5 squares
 * from user input.
 *
 * @author Willie Chalmers III
 * @since 9/7/17
 */
public class chalmersw04 {

    /**
     * The number of times to ask for a shape's perimeter
     */
    private static final int ASK_AMOUNT = 5;

    private static final Scanner sScanner = new Scanner(System.in);

    public static void main(String[] args) {
        print("Lab 4 - 10 point version");
        determineAndPrintPerimeter();
    }

    /**
     * Calculates the perimeters for {@link #ASK_AMOUNT} triangles and
     * {@link #ASK_AMOUNT} squares.
     */
    private static void determineAndPrintPerimeter() {
        for (int i = 0; i < ASK_AMOUNT; i++) {
            askTriangles();
        }
        for (int i = 0; i < ASK_AMOUNT; i++) {
            askSquares();
        }
    }

    private static void askSquares() {
        print("\n\nSquare\n");
        double sideLength = askSideLength();
        double perimeter = findPerimeter(sideLength, sideLength, Shape.SQUARE);
        displayPerimeter(perimeter, Shape.SQUARE);
    }

    private static void askTriangles() {
        print("\n\nTriangle\n");
        double base = askSideLength();
        double height = askSideLength();
        double perimeter = findPerimeter(base, height, Shape.TRIANGLE);
        displayPerimeter(perimeter, Shape.TRIANGLE);
    }

    private static double askSideLength() {
        print("What is the length of this side?   ");
        return sScanner.nextDouble();
    }

    private static void displayPerimeter(double perimeter, Shape shape) {
        String shapeString;
        if (shape == Shape.SQUARE) {
            shapeString = "square";
        } else {
            // We know there are only two possibilities
            shapeString = "triangle";
        }
        print(String.format("The perimeter of the %s is %s", shapeString, perimeter));
    }

    /**
     * Determines the perimeter of a square or a triangle
     *
     * @param height Ignored if {@code shape} is a {@link Shape#SQUARE}
     * @param shape  A shape used to determine perimeter calculation method
     * @return The perimeter of the shape with the given values
     */
    private static double findPerimeter(double base, double height, Shape shape) {
        if (shape == Shape.TRIANGLE) {
            // Assumes right triangles
            double hypotenuse = Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));
            return base + height + hypotenuse;
        }
        return 4 * base;
    }

    /**
     * Because I was too lazy to write {@link System#out#print()} a billion times
     *
     * @param text A {@link String} to display to standard output
     */
    private static void print(String text) {
        System.out.print(text);
    }

    private enum Shape {
        SQUARE, TRIANGLE
    }
}
