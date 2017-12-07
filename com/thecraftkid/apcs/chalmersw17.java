package com.thecraftkid.apcs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Willie Chalmers III
 * @since 12/6/17
 */
public class chalmersw17 {

    public static void main(String[] args) {
        Reader reader = new Reader();
        double radius = reader.getRadius();
        double circleArea = calculateArea(radius);
        System.out.printf("Area is %s\n\n", circleArea);
        int sideA = reader.getLength();
        int sideB = reader.getLength();
        double rectArea = calculateArea(sideA, sideB);
        System.out.printf("The area of the rectangle is %s\n\n", rectArea);

        Converter converter = new Converter();
        char character = reader.getChar();
        reader.printResults(character,
                converter.charToInt(character),
                converter.convertToDouble(character));
        int wholeNumber = reader.getWholeNumber();
        double decminalNumber = reader.getDecimalNumber();


    }

    public static double calculateArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static double calculateArea(double sideA, double sideB) {
        return sideA * sideB;
    }

    public static class Reader {

        private Scanner mScanner;

        public Reader() {
            mScanner = new Scanner(System.in);
        }

        public String readString(String prompt) {
            System.out.println(prompt);
            String result;
            try {
                result = mScanner.next();
            } catch (Exception e) {
                System.out.println("That's not a string. Try again.");
                return readString(prompt);
            }
            return result;
        }

        public int readInt(String prompt) {
            int result;
            try {
                result = Integer.parseInt(readString(prompt));
            } catch (InputMismatchException e) {
                System.out.println("That's not a number. Try again.");
                return readInt(prompt);
            }
            return result;
        }

        public double readDouble(String prompt) {
            double result;
            try {
                result = Double.parseDouble(readString(prompt));
            } catch (InputMismatchException e) {
                System.out.println("That's not a number. Try again.");
                return readInt(prompt);
            }
            return result;
        }

        public double getRadius() {
            return readDouble("Radius of the circle: ");
        }

        public int getLength(String prompt) {
            return readInt("What is the length of the rectangle? ");
        }

        public int getLength() {
            return readInt("What is the height of the rectangle?");
        }

        public char getChar() {
            System.out.println("Enter a character: ");
            return (char) mScanner.nextInt();
        }

        public int getWholeNumber() {
            return readInt("Enter a whole number: ");
        }

        public double getDecimalNumber() {
            return readDouble("Enter a decimal number: ");
        }

        public void printResults(Object... objects) {
            System.out.printf("%1$s equals %2$s and %3$s", objects[0], objects[1], objects[2]);
            System.out.println();
        }
    }

    public static class Converter {

        public int charToInt(char character) {
            return (int) character;
        }

        public char convertFromString(String string) {
            return string.charAt(0);
        }

        public double convertToDouble(char letter) {
            return (double) letter;
        }
    }
}
