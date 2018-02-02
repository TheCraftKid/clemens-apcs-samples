package com.thecraftkid.apcs;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * A program that contents of an {@link ArrayList} in their current order
 * using an enhanced for loop.
 *
 * @author Willie Chalmers III
 * @since 11/16/17
 */
public class chalmersw15 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // Regular for loop
        for (int a = 0; a < 20; a++) {
            list.add((int) (Math.random() * 100));
        }
        // Enhanced for loop
        for (int value : list) {
            out.println(value);
        }
        // Or using FP
        list.forEach(out::println);
    }
}
