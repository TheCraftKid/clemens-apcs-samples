package com.thecraftkid.apcs;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.thecraftkid.apcs.chalmersw07.Student;

/**
 * An application that displays {@link Student} objects ID's and average
 * grades on the screen.
 */
public class chalmersw19 extends Panel {

    private static final int START_X = 16;
    private static final int START_Y = 16;

    private static final int RECT_WIDTH = 240;
    private static final int RECT_HEIGHT = 128;

    private static final int TEXT_PADDING = 8;
    private static final int MARGIN = 16;

    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;

    public void update(Graphics g) {
        Dimension d = getSize();
        if ((offScreenImage == null) || (d.width != offScreenSize.width) || (d.height != offScreenSize.height)) {
            offScreenImage = createImage(d.width, d.height);
            offScreenSize = d;
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.clearRect(0, 0, d.width, d.height);
        paint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        chalmersw19 window = new chalmersw19();  //change chalmersw19 to your file name   (twice in this line)
        window.setSize(1050, 700);
        f.add(window);
        f.pack();
        window.init();
        f.setSize(1050, 700);/*size of frame*/
        f.setVisible(true);
    }

    private Student[][] desks;

    private boolean initPaint = true;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (initPaint) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getSize().height, getSize().width);
            initPaint = false;
        }
        draw(g, desks);
        try {
            Thread.sleep(20);
        } catch (Exception ignored) {
        }
        repaint();
    }

    private static int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 5);
    }

    /**
     * Initializes some instance variables for this program.
     */
    public void init() {
        requestFocus();
        desks = getFilledArray();
    }

    /**
     * Generates a 5 x 5 array of {@link Student}s.
     */
    public static Student[][] getFilledArray() {
        Student[][] students = new Student[generateRandomNumber()][generateRandomNumber()];
        for (Student[] student : students) {
            Arrays.fill(student, new Student());
        }
        return students;
    }

    /**
     * Draws the given students' information to the screen.
     *
     * @param g        A surface to paint
     * @param students An array
     */
    public static void draw(Graphics g, Student[][] students) {
        for (int i = 0; i < students.length; i++) {
            Student[] row = students[i];
            for (int j = 0; j < row.length; j++) {
                Student student = row[j];
                int currentX = START_X + i * (RECT_WIDTH + MARGIN);
                int currentY = START_Y + j * (RECT_HEIGHT + MARGIN);
                g.setColor(Color.getColor("#E0E0E0"));
                g.drawRect(currentX, currentY, RECT_WIDTH, RECT_HEIGHT);
//                g.setColor(Color.BLACK);
                g.setFont(Font.getFont(Font.SANS_SERIF));
                String displayId = String.format("ID: %s", student.getId());
                g.drawString(displayId, currentX + TEXT_PADDING, currentY + TEXT_PADDING + 10);
                String displayAverage = String.format("%.2f%n", student.getTestAverage());
                g.drawString(displayAverage,
                        (currentX + RECT_WIDTH) - 100,
                        (currentY + RECT_HEIGHT) + TEXT_PADDING);
            }
        }
    }
}
