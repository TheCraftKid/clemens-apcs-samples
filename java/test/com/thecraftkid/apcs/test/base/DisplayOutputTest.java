package com.thecraftkid.apcs.test.base;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A template class that initializes standard output and input to track
 * for testing.
 *
 * @version 1.0.0
 * @since 2/2/2018
 */
public class DisplayOutputTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setErr(new PrintStream(errorStream));
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }
}
