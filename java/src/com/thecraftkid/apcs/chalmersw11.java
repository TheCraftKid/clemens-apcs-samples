package com.thecraftkid.apcs;

import java.util.*;

import static com.thecraftkid.apcs.chalmersw07.Student;
import static java.lang.System.out;

/**
 * A program that manages data for an imaginary set of {@link Student}s
 *
 * @author Wiilie Chalmers III
 * @since 10/20/17
 */
public class chalmersw11 {

    public static void main(String[] args) {
        StudentManager manager = StudentManager.getInstance();
        while (true) {
            int choice = manager.getUserChoice();
            switch (choice) {
                case StudentManager.Choice.GENERATE_STUDENTS:
                    manager.launchStudentFillFlow();
                    break;
                case StudentManager.Choice.CHANGE_STUDENT_NAME:
                    manager.launchStudentChangeNameFlow();
                    break;
                case StudentManager.Choice.COPY_STUDENT:
                    manager.launchStudentCopyFlow();
                    break;
                case StudentManager.Choice.DUPLICATE_STUDENT:
                    manager.launchStudentDuplicateFlow();
                    break;
                case StudentManager.Choice.CHECK_DUPLICATE:
                    manager.launchCheckDuplicateFlow();
                    break;
                case StudentManager.Choice.DISPLAY_STUDENTS:
                    manager.displayStudents();
                    break;
                case StudentManager.Choice.EXIT:
                    // TODO: 10/25/2017 Find more OOP way to accomplish this
                    manager.exit();
                    return;
                default:
                    System.out.println("That isn't a valid input.");
                    break;
            }
        }
    }

    static class StudentManager {

        private static StudentManager instance;

        private StudentFlowManager flowManager = new StudentFlowManager();

        private ClemensStudent[] students = new ClemensStudent[5];

        /**
         * Returns the currently created instance of the StudentManager.
         * </br>
         */
        public static StudentManager getInstance() {
            if (instance == null) {
                instance = new StudentManager();
            }
            return instance;
        }

        public void launchStudentFillFlow() {
            System.out.println("Initializing students...");
            students = generateNamelessStudents(students.length).toArray(students);
        }

        public void launchStudentChangeNameFlow() {
            // TODO: 11/1/17 Lookup by ID as well (provide option)
            Map.Entry<Integer, ClemensStudent> info = flowManager.generateNameChangeInfo();
            ClemensStudent temp = info.getValue();
            changeName(info.getKey(), temp.getFirstName(), temp.getLastName());
        }

        public void launchStudentCopyFlow() {
            Map.Entry<Integer, Integer> info = flowManager.generateCloneInformation();
            linkStudents(info.getKey(), info.getValue());
        }

        public void launchStudentDuplicateFlow() {
            Map.Entry<Integer, Integer> info = flowManager.generateCloneInformation();
            deepCopy(info.getKey(), info.getValue());
        }

        public void launchCheckDuplicateFlow() {
            int[] choices = flowManager.getDuplicateChoices();
            boolean duplicate = checkDuplicate(students[choices[0]], students[choices[1]]);
            if (duplicate) {
                System.out.println("Those two students are duplicates.");
            }
        }

        public void displayStudents() {
            if (students[0] != null) {
                Arrays.asList(students).forEach(out::println); // Man, I love Java 8
            } else {
                System.out.println("Please initialize the students first.");
            }
        }

        private List<ClemensStudent> generateNamelessStudents(int amount) {
            List<ClemensStudent> students = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                students.add(i, new ClemensStudent());
            }
            return students;
        }

        public void changeName(int index, String firstName, String lastName) {
            students[index].setFirstName(firstName);
            students[index].setLastName(lastName);
        }

        /**
         * This is basically a shallow copy.
         */
        public void linkStudents(int start, int end) {
            students[start] = students[end];
        }

        public void deepCopy(int start, int end) {
            ClemensStudent first = students[start];
            try {
                students[end] = first.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("Couldn't copy student");
            }
        }

        public boolean checkDuplicate(ClemensStudent first, ClemensStudent second) {
            if (first == null || second == null) {
                return false;
            }
            return first.equals(second);
        }

        public int getUserChoice() {
            return flowManager.getNumberInput("1) Generate a new student list\n" +
                    "2) Change a student's name\n" +
                    "3) Copy a student\n" +
                    "4) Duplicate a student\n" +
                    "5) Check if two students have the same information\n" +
                    "6) Display all students\n" +
                    "7) Exit\n");
        }

        public void exit() {
            System.exit(0);
        }

        static class Choice {
            static final int GENERATE_STUDENTS = 1;
            static final int CHANGE_STUDENT_NAME = 2;
            static final int COPY_STUDENT = 3;
            static final int DUPLICATE_STUDENT = 4;
            static final int CHECK_DUPLICATE = 5;
            static final int DISPLAY_STUDENTS = 6;
            static final int EXIT = 7;
        }
    }

    static class StudentFlowManager {

        private Scanner scanner = new Scanner(System.in);

        /**
         * Returns a list of students filled in with information populated by the user.
         *
         * @param amount The number of students to generate
         */
        public List<ClemensStudent> generateStudents(int amount) {
            List<ClemensStudent> studentList = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                studentList.add(generateStudent());
            }
            return studentList;
        }

        public Map.Entry<Integer, ClemensStudent> generateNameChangeInfo() {
            int position = getNumberInput("What position is the student located?");
            return new AbstractMap.SimpleEntry<>(position, generateStudent());
        }

        /**
         * @return A pair representing the play and end position for a copy operation, respectively
         */
        public Map.Entry<Integer, Integer> generateCloneInformation() {
            int start = getNumberInput("Position of student to clone?");
            int end = getNumberInput("Destination position?");
            return new AbstractMap.SimpleEntry<>(start, end);
        }

        public int[] getDuplicateChoices() {
            int choiceOne = getNumberInput("Position of first student: ");
            int choiceTwo = getNumberInput("Position of second student: ");
            return new int[]{
                    choiceOne, choiceTwo
            };
        }

        /**
         * Returns the user's next integer input
         *
         * @param prompt The query text to display to the user
         */
        public int getNumberInput(String prompt) {
            System.out.println(prompt);
            return scanner.nextInt();
        }

        /**
         * Returns the user's next {@link String} input
         *
         * @param prompt The query text to display to the user
         */
        public String getStringInput(String prompt) {
            System.out.println(prompt);
            return scanner.next();
        }

        public ClemensStudent generateStudent() {
            // TODO: 10/23/2017 Fill in values
            String firstName = getStringInput("What is the student's first name?");
            String lastName = getStringInput("What is the student's last name?");
            ClemensStudent student = new ClemensStudent();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            return student;
        }
    }

    static class ClemensStudent extends Student implements Comparable<ClemensStudent> {

        private String firstName;

        private String lastName;

        public ClemensStudent() {
            // FIXME: 10/24/2017 Initialize values to reasonable defaults
            this(generateStudentId(), null, null, new int[3]);
        }

        public ClemensStudent(String id, String firstName, String lastName, int... testGrades) {
            super(id, testGrades);
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFullName() {
            return String.format("%s %s", getFirstName(), getLastName());
        }

        @Override
        public int compareTo(ClemensStudent student) {
            if (this == student) {
                return 0;
            }
            return 0;
        }

        /**
         * Returns true if the given object has the same first name, last name ID, and test grades
         * as this .
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ClemensStudent)) {
                return false;
            }
            ClemensStudent student = (ClemensStudent) o;
            return (getFirstName().equals(student.getFirstName())
                    && getLastName().equals(student.getLastName())
                    && getId().equals(student.getId())
                    && Arrays.equals(getTestGrades(), student.getTestGrades()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, getId(), getTestGrades());
        }

        @Override
        protected ClemensStudent clone() throws CloneNotSupportedException {
            ClemensStudent clone = (ClemensStudent) super.clone();
            clone.setFirstName(getFirstName());
            clone.setLastName(getLastName());
            clone.setTestAverage(getTestAverage());
            clone.setTestGrades(getTestGrades());
            clone.setId(getId());
            return clone;
        }

        @Override
        public String toString() {
            return getFullName() + " " + super.toString();
        }
    }
}