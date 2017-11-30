package com.thecraftkid.apcs;

import java.util.*;

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
        out.printf("Original string: %s", splitToConsole(TO_MANIPULATE));
        out.println();
        int startPos = Integer.parseInt(getInput("Where would to like to split this string from?"));
        int endPos = Integer.parseInt(getInput("Where would to like to split this string to?"));
        out.println();
        out.printf("From given start: %s", manipulator.getPart(startPos));
        out.println();
        out.printf("To given end: %s", manipulator.displayPartTo(endPos));
    }

    public static String getInput(String prompt) {
        out.println(prompt);
        return new Scanner(System.in).next();
    }

    public static String splitToConsole(String toSplit) {
        List<String> split = new ArrayList<>();
        // TODO: 11/30/2017 Split string into 80 character sections
        StringBuilder builder = new StringBuilder();
        split.forEach(builder::append);
        return builder.toString();
    }

    public static int getNumberInput() {
        return new Scanner(System.in).nextInt();
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

        public void start() {
            mRiddleGenerator.forEach(riddle -> {
                System.out.println(riddle);
                String response = getInput();
                if (riddle.getValue().equals(response)) {
                    mCorrect++;
                    System.out.println("Yep.");
                } else {
                    System.out.println("Nope. It's " + riddle.getValue());
                }
                mTotal++;
            });
            displayResults();
        }

        public void displayResults() {
            System.out.printf("You got %s out of %s riddles correct.", mCorrect, mTotal);
            if (mTotal == mCorrect) {
                out.println("You are a freaking legend.");
            }
        }

        private String getInput() {
            return new Scanner(System.in).next();
        }
    }

    public static class RiddleGenerator extends ArrayList<Riddle> {

        public RiddleGenerator() {
            super();
            addAll(fetchRiddles());
        }

        private static List<Riddle> fetchRiddles() {
            List<Riddle> riddles = new ArrayList<>();
            // TODO: 11/27/2017 Populate with riddles
            riddles.add(new Riddle("What is black and white and red all over?",
                    "a barber's rag."));
            riddles.add(new Riddle("What is red and yellow and blue all over?",
                    "a rainbow."));
            riddles.add(new Riddle("", ""));
            riddles.add(new Riddle("", ""));
            return riddles;
        }
    }

    public static class Riddle implements Map.Entry<String, String> {

        private String mQuestion;

        private String mAnswer;

        public Riddle(String question, String answer) {
            mQuestion = question;
            mAnswer = answer;
        }

        @Override
        public String getKey() {
            return mQuestion;
        }

        @Override
        public String getValue() {
            return mAnswer;
        }

        @Override
        public String setValue(String s) {
            throw new UnsupportedOperationException("Riddles are immutable.");
        }

        @Override
        public String toString() {
            return getKey();
        }
    }
}
