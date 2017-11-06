package com.thecraftkid.apcs;

import com.thecraftkid.apcs.given.LabHelper;

import static java.lang.System.out;

/**
 * A program that sorts integers from least to greatest using different
 * algorithms including insertion sort and selection sort.
 *
 * @author Willie Chalmers III
 * @since 11/3/17
 */
public class chalmersw12 {

    public static void main(String[] args) {
        int[] items = LabHelper.lab12();
        int[] insertSortedItems = sortInsertion(items);
//        int[] selectionSortedItems = sortSelection(items);
        printList(insertSortedItems);
//        printList(selectionSortedItems);
    }

    /**
     * Performs an insertion sort on the given array.
     *
     * @param list An unsorted list
     * @return A sorted copy of the array
     */
    public static int[] sortInsertion(int[] list) {
        int[] sorted = clone(list);
        for (int i = 0; i < sorted.length; i++) {
            int j = i;
            while (j > 0 && sorted[j] < sorted[j - 1]) {
                swap(sorted, j, j - 1);
                j -= 1;
            }
        }
        return sorted;
    }

    public static void swap(int[] list, int start, int end) {
        int toSwap = list[start];
        list[start] = list[end];
        list[end] = toSwap;
    }

//    public static int[] sortSelection(int[] list) {
//        int[] sorted = clone(list);
//        for (int i = 1; i < list.length + 1; i++) {
//            int first = sorted[i];
//            int next = sorted[i - 1];
//            if (next < first) {
//                sorted[i] = next;
//                sorted[i - 1] = first;
//            }
//        }
//        return sorted;
//    }

    /**
     * Sorts the given list in reverse order
     *
     * @return
     */
    public static int[] sortBackwards(int[] list) {
        int[] sorted = clone(list);
        return sorted;
    }

    public static int[] clone(int[] list) {
        int[] result = new int[list.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = list[i];
        }
        return result;
    }

    public static void printList(int[] list) {
        for (int value : list) {
            out.println(value);
        }
    }
}
