package com.thecraftkid.apcs;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * @author Willie Chalmers III
 * @since 11/27/17
 */
public class chalmersw16 {

    public static final String TO_MANIPULATE = "Samuel Clemens said “The secret of getting ahead is getting started. " +
            "The secret of getting started is breaking your complex overwhelming tasks into small manageable tasks, " +
            "and then starting on the first one.”";

    public static void main(String[] args) {
        RiddlePresenter presenter = new RiddlePresenter();
        presenter.start();

        StringManipulator manipulator = new StringManipulator(TO_MANIPULATE);
        out.println();
        out.printf("Original string: \n%s", splitToConsole(TO_MANIPULATE));
        out.println();

        int startPos = getNumberInput("Where would to like to split this string from?");
        int endPos = getNumberInput("Where would to like to split this string to?");
        out.println();
        out.printf("From given start: %s", manipulator.getPart(startPos));
        out.println();
        out.printf("To given end: %s", manipulator.displayPartTo(endPos));
    }

    public static String getInput(String prompt) {
        out.println(prompt);
        return new Scanner(System.in).next();
    }

    /**
     * Prints the given prompt to the user and requests a numerical input from
     * the console (standard input).
     *
     * @param prompt The prompt to display to the user.
     * @return The number inputted from standard input.
     */
    public static int getNumberInput(String prompt) {
        out.println(prompt);
        int number;
        try {
            number = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            out.println("That's not a number.");
            return getNumberInput(prompt);
        }
        return number;
    }

    /**
     * Splits the given String into 80 character segments separated by newlines.
     */
    public static String splitToConsole(String toSplit) {
        StringBuilder builder = new StringBuilder();
        StringBuilder currentBuilder = new StringBuilder();
        Arrays.stream(toSplit.split("\\s+")) // Split by word
                .collect(Collectors.toList()) // Turn it into a list
                .listIterator() // Start iterating
                .forEachRemaining(word -> {
                    // If adding the letter makes the current line longer than 80, break it
                    if (currentBuilder.length() + word.length() <= 80) {
                        currentBuilder.append(word).append(" ");
                    } else {
                        currentBuilder.append("\n").append(word);
                        builder.append(currentBuilder);
                        currentBuilder.setLength(0);
                    }
                });
        return builder.toString();
    }

    public static class StringManipulator {

        private String mString;

        public StringManipulator(String toManipulate) {
            mString = toManipulate;
        }

        public String getPart(int start) {
            return getPart(start, mString.length());
        }

        public String displayPartTo(int end) {
            return getPart(0, end);
        }

        public String getPart(int start, int end) {
            return mString.substring(start, end);
        }

        /**
         * Returns a list of indexes of the given letters in this StringManipulator's
         * string.
         * <p>
         * The list will be empty if none of the letters are in this StringManipulator's
         * string.
         * </p>
         */
        public List<Integer> findCharacters(char[] letters) {
            List<Integer> indexes = new ArrayList<>();
            List<Character> characters = new ArrayList<>();
            for (char letter : letters) {
                characters.add(letter);
            }
            characters.forEach(letter -> {
                int index = characters.indexOf(letter);
                if (index > -1) {
                    indexes.add(index);
                }
            });
            return indexes;
        }

        @Override
        public String toString() {
            return mString;
        }
    }

    public static class RiddlePresenter {

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        private RiddleGenerator mRiddleGenerator = new RiddleGenerator();

        private int mCorrect;

        private int mTotal;

        /**
         * Begins looping through riddles, asking them until there are no more left.
         */
        public void start() {
            mRiddleGenerator.forEach(riddle -> {
                String response = getInput(riddle.toString());
                if (riddle.getValue().equals(response.toLowerCase())) {
                    mCorrect++;
                    System.out.println("Yep.");
                } else {
                    System.out.printf("Nope, it's %s.", riddle.getValue());
                    out.println();
                }
                mTotal++;
            });
            displayResults();
        }

        /**
         * Displays the number of correct riddles and the total guessed.
         */
        public void displayResults() {
            System.out.printf("You got %s out of %s riddles correct.", mCorrect, mTotal);
            if (mTotal == mCorrect) {
                out.println("You are a freaking legend.");
            }
        }
    }

    /**
     * A source for riddles with questions and answers.
     */
    public static class RiddleGenerator extends ArrayList<Riddle> {

        /**
         * Creates a new RiddleGenerator with a hardcoded set of riddles.
         */
        public RiddleGenerator() {
            super();
            addAll(fetchRiddles());
        }

        private static List<Riddle> fetchRiddles() {
            List<Riddle> riddles = new ArrayList<>();
            // TODO: 11/27/2017 Populate with riddles
            riddles.add(new Riddle("What is black and white and red all over?",
                    "a barber's rag"));
            riddles.add(new Riddle("What is red and yellow and blue all over?",
                    "a rainbow"));
            riddles.add(new Riddle("", ""));
            riddles.add(new Riddle("", ""));
            return riddles;
        }
    }

    /**
     * An immutable riddle that contains a question and a singular answer.
     */
    public static class Riddle implements Map.Entry<String, String> {

        private final String mQuestion;

        private final String mAnswer;

        /**
         * Creates a new riddle with a given question and only answer.
         */
        public Riddle(String question, String answer) {
            mQuestion = question;
            mAnswer = answer;
        }

        /**
         * Returns this riddle's question.
         */
        @Override
        public String getKey() {
            return mQuestion;
        }

        /**
         * Returns the lowercase version of this riddle's answer for comparison.
         */
        @Override
        public String getValue() {
            return mAnswer.toLowerCase();
        }

        /**
         * Throws an exception when called.
         *
         * @param s An ignored value
         * @throws UnsupportedOperationException Always - riddles are immutable.
         */
        @Override
        public String setValue(String s) {
            throw new UnsupportedOperationException("Riddles are immutable.");
        }

        /**
         * Returns the user-readable representation of this riddle - the question.
         */
        @Override
        public String toString() {
            return getKey();
        }
    }
}
