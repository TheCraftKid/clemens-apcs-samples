package com.thecraftkid.apcs;

import com.thecraftkid.apcs.given.LabHelper;

import java.util.Arrays;

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
        Sorter sorter = new Sorter(items);
        System.out.println("Original: \n" + sorter);
        int[] insertSortedItems = sorter.sortInsertion();
//        int[] selectionSortedItems = sortSelection(original);
        System.out.println("Sorted with insertion sort: ");
        printList(insertSortedItems);
//        printList(selectionSortedItems);
    }

    static void printList(int[] list) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Arrays.stream(list).forEach(i -> builder.append(i).append(", ")); // Gotta love Java 8
        builder.append("]");
        System.out.println(builder);
    }

    public static class Sorter {

        private int[] original;

        private SortCallback callback;

        public Sorter(int[] items) {
            this.original = clone(items);
        }

        public Sorter(int[] items, SortCallback callback) {
            this.original = items;
            this.callback = callback;
        }

        /**
         * Performs an insertion sort with this sorter's item.
         *
         * @return A sorted copy of the array
         */
        public int[] sortInsertion() {
            int[] sorted = clone(original);
            for (int i = 0; i < sorted.length; i++) {
                int j = i;
                while (j > 0 && sorted[j] < sorted[j - 1]) {
                    swap(sorted, j, j - 1);
                    j -= 1;
                }

            }
            return sorted;
        }

        /**
         * Performs a selection sort with this sorter's items.
         *
         * @return A sorted copy of the array
         */
        public int[] sortSelection() {
            int[] sorted = clone(original);
            for (int i = 1; i < sorted.length + 1; i++) {
            }
            return sorted;
        }

        /**
         * Sorts the given list in reverse order.
         *
         * @return The sorted array
         */
        public int[] sortBackwards() {
            int[] sorted = clone(original);
            return sorted;
        }

        public static void swap(int[] list, int start, int end) {
            int toSwap = list[start];
            list[start] = list[end];
            list[end] = toSwap;
        }

        public static int[] clone(int[] list) {
            int[] result = new int[list.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = list[i];
            }
            return result;
        }

        @Override
        public String toString() {
            return Arrays.toString(original);
        }

        public interface SortCallback {
            void onUpdate(int[] list, int position, int oldValue, int newValue);
        }
    }
}
