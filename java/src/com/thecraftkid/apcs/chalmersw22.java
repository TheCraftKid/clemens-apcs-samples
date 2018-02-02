package com.thecraftkid.apcs;

import java.util.Scanner;

public class chalmersw22 {

    public static void main(String args[]) {
        Scanner numbers = new Scanner(System.in);
//        String message = helper.lab22();
        String message = "Hello world. I'm doing just fine";
        System.out.println("Enter an integer");
        int n = numbers.nextInt();
        String[][] array = toArray(message, n);
        display(array);
        code(array);
        code(array);
    }

    public static String[][] toArray(String m, int n) {
        String[][] rows = new String[n][(int) (Integer.MAX_VALUE / Math.pow(2, 16))]; // How does one initialize an array with unknown height?
        char[] letters = m.toCharArray();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                rows[i][j] = String.valueOf(letters[i]);
            }
        }
        return rows;
    }

    public static void display(String[][] arr) {
        for (String[] line : arr) {
            for (String letter : line) {
                System.out.println(letter);
            }
            System.out.println();
        }
    }

    public static void code(String[][] arr) {
        System.out.println("\n--------------\nSwap\n\n");
        swap(arr);
        display(arr);
        swap(arr);
        display(arr);
    }

    private static void swap(Object[] array) {
        for (int i = 0; i < array.length - 1; i += 2) {
            Object temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

}
