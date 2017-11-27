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

        StringManipulator manipulator = new StringManipulator(TO_MANIPULATE);
        out.printf("Original string: %s", manipulator);

    }

    public static String getInput() {
        return new Scanner(System.in).next();
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

        private RiddleGenerator mRiddleGenerator = new RiddleGenerator();

        private int mCorrect;

        private int mTotal;

        public void presentNext() {

        }

        public void displayResults() {

        }
    }

    public static class RiddleGenerator extends ArrayList<String> {

        private List<Riddle> mRiddles = Collections.unmodifiableList(generateRiddles());

        public RiddleGenerator() {
            super();
        }

        private static List<Riddle> generateRiddles() {
            List<Riddle> riddles = new ArrayList<>();
            // TODO: 11/27/2017 Populate with riddles
            riddles.add(new Riddle("", ""));
            riddles.add(new Riddle("", ""));
            riddles.add(new Riddle("", ""));
            riddles.add(new Riddle("", ""));
            return riddles;
        }

        @Override
        public Iterator<String> iterator() {

            return super.iterator();
        }

        private class RiddleIterator implements Iterator<String> {

            private int mCursor;

            private int mLast;

            @Override
            public boolean hasNext() {
                return mCursor < size();
            }

            @Override
            public String next() {
                return null;
            }

            @Override
            public void remove() {
            }
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
    }
}
