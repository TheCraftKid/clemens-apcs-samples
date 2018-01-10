package com.thecraftkid.apcs;

import java.awt.*;
import java.util.Arrays;

import static com.thecraftkid.apcs.chalmersw07.Student;

public class chalmersw19 extends Panel {

    private static final int START_X = 16;
    private static final int START_Y = 16;

    private static final int RECT_WIDTH = 128;
    private static final int RECT_HEIGHT = 32;

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
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }

            ;
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
        Student[][] students = new Student[5][5];
        for (Student[] student : students) {
            Arrays.fill(student, new Student());
        }
        return students;
    }

    @Override
    public void paint(Graphics g) {
        draw(g, desks);
        try {
            Thread.sleep(20);
        } catch (Exception ignored) {
        }
        repaint();
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
                g.drawString(student.getId(), currentX + TEXT_PADDING, currentY + TEXT_PADDING);
                g.drawString(String.valueOf(student.getTestAverage()),
                        (currentX + RECT_WIDTH) - TEXT_PADDING,
                        (currentY + RECT_HEIGHT) - 10);
            }

        }
    }

}
