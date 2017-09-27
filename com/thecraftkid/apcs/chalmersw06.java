package com.thecraftkid.apcs;

import static com.thecraftkid.apcs.chalmersw06.Student.generateStudentId;
import static com.thecraftkid.apcs.chalmersw06.Student.getRandomGrade;

/**
 * A program that instantiates five {@link Student} objects with a random five digit student ID and
 * three random test grades.
 *
 * @author Willie Chalmers III
 * @since 9/18/17
 */
public class chalmersw06 {

    public static void main(String[] args) {
        System.out.println("Mr. Burton\nLab 6 - 10 point version");
        Student student1 = new Student(generateStudentId(), getRandomGrade(),
                getRandomGrade(), getRandomGrade());
        Student student2 = new Student(generateStudentId(), getRandomGrade(),
                getRandomGrade(), getRandomGrade());
        Student student3 = new Student(generateStudentId(), getRandomGrade(),
                getRandomGrade(), getRandomGrade());
        Student student4 = new Student(generateStudentId(), getRandomGrade(),
                getRandomGrade(), getRandomGrade());
        Student student5 = new Student(generateStudentId(), getRandomGrade(),
                getRandomGrade(), getRandomGrade());
        displayGrades(student1, student2, student3, student4, student5);
        recalculateGrades(student1, student2, student3, student4, student5);
        displayGrades(student1, student2, student3, student4, student5);
    }

    /**
     * Prints out the given students information using {@link Student#toString()}.
     *
     * @param students The students whose grades to display
     */
    private static void displayGrades(Student... students) {
        System.out.println();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Recalculates the grade for each student using {@link Student#addExtraCredit()}.
     *
     * @param students A set of students to add extra credit to
     */
    private static void recalculateGrades(Student... students) {
        for (Student student : students) {
            student.addExtraCredit();
        }
    }

    @SuppressWarnings("WeakerAccess")
    static class Student {

        // I mean, this could be an int, but that's not enough randomness
        private String id;
        // Use int[] as there are only three
        private int[] testGrades;
        // Still feels pretty redundant
        private double testAverage;

        /**
         * Creates a new student with the given student ID and test grades.
         *
         * @param id         A unique identifier
         * @param testGrades A set of initial test scores (out of 100)
         */
        public Student(String id, int... testGrades) {
            this.id = id;
            this.testGrades = testGrades;
            this.testAverage = calculateTestAverage(testGrades);
        }

        /**
         * Calculates the arithmetic average of the given values.
         *
         * @param grades A list of grades to average
         */
        public static double calculateTestAverage(int... grades) {
            double total = 0;
            for (double grade : grades) {
                total += grade;
            }
            return total / grades.length;
        }

        /**
         * @return Random value between 5 to 10
         */
        public static int getRandomExtraCredit() {
            return (int) (Math.random() * 6 + 5);
        }

        /**
         * Uses {@link Math#random()} to calculate an ID number between 0-100.
         *
         * @return A random number between 0 and 1000
         */
        public static String generateStudentId() {
            int id = (int) (Math.random() * 10000 + 10000);
            return String.valueOf(id);
        }

        /**
         * Uses {@link Math#random()} to calculate an test average between 60-100.
         *
         * @return A random number between 60 and 100.
         */
        public static int getRandomGrade() {
            return (int) (Math.random() * 40) + 60;
        }

        /**
         * Adds the given number of points to this student's second test grade.
         *
         * @param credit The amount of points to add
         */
        public void addExtraCredit(int credit) {
            testGrades[1] += credit;
            setTestAverage(calculateTestAverage(testGrades));
        }

        /**
         * Adds five extra points to this student's second test grade.
         */
        public void addExtraCredit() {
            testGrades[1] += getRandomExtraCredit();
            setTestAverage(calculateTestAverage(testGrades));
        }

        /**
         * Returns this student's test grades and its average in a human-readable format.
         */
        @Override
        public String toString() {
            return String.format("%s - Test 1: %s\tTest 2: %s\tTest 3: %s\t-- Average: %s",
                    getId(), getTestGrades()[0], getTestGrades()[1], getTestGrades()[2],
                    (int) getTestAverage());
        }

        public String getId() {
            return id;
        }

        public int[] getTestGrades() {
            return testGrades;
        }

        public double getTestAverage() {
            return testAverage;
        }

        public void setTestAverage(double testAverage) {
            this.testAverage = testAverage;
        }
    }

}
