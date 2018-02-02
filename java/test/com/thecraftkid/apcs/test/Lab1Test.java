package com.thecraftkid.apcs.test;

import com.thecraftkid.apcs.test.base.DisplayOutputTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class Lab1Test {

    private static final String EXPECTED_OUTPUT = "\n\nIn computer programming \n" +
            "you have to type everything exactly.\n" +
            "\n The \\ key does some weird stuff.\n" +
            "\n" +
            "There is a big\n" +
            " difference \n" +
            "between: \n\"Let's eat Grandma\" \n" +
            "\tand\n" +
            "\"Let's eat, Grandma\"\n" +
            "\n" +
            " W        W    EEEEE      CCCCC   \n" +
            " W        W    E         C     C  \n" +
            " W        W    E         C     C  \n" +
            " W    W   W    EEEEEE   C         \n" +
            "   W W W W     E         C     C  \n" +
            "    W   W      EEEEEE     CCCCC   \n";

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outputStream));
    }

//    @After
//    public void restoreStreams() {
//        System.setOut(System.out);
//    }

    @Test
    public void shouldPrintProperly() {
        assertEquals(EXPECTED_OUTPUT, this.outputStream.toString());
    }
}
