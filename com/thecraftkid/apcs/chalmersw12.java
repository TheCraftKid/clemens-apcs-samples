package com.thecraftkid.apcs;

import com.thecraftkid.apcs.given.LabHelper;

import static java.lang.System.out;

/**
 * A program that sorts integers from least to greatest.
 *
 * @author Willie Chalmers III
 * @since 11/3/17
 */
public class chalmersw12 {

    public static void main(String[] args) {
        int[] items = LabHelper.lab12();
        int[] insertSortedItems = sortInsertion(items);
        int[] selectionSortedItems = sortSelection(items);
        printList(insertSortedItems);
        printList(selectionSortedItems);
    }

    public static int[] sortInsertion(int[] list) {
        int[] sorted = new int[list.length];
        for (int i = 0; i < list.length; i++) {

        }
        return sorted;
    }

    public static int[] sortSelection(int[] list) {
        int[] sorted = clone(list);
        for (int i = 1; i < list.length + 1; i++) {
            int first = sorted[i];
            int next = sorted[i + 1];
            if (next < first) {
                sorted[i] = next;
                sorted[i - 1] = first;
            }
        }
        return sorted;
    }

    public static int[] clone(int[] list) {
        int[] result = new int[list.length];
        for (int i = 0; i < result.length; i++) {
            result[0] = list[0];
        }
        return result;
    }

    public static void printList(int[] list) {
        for (int value : list) {
            out.println(value);
        }
    }
}
