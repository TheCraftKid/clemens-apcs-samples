package com.thecraftkid.apcs;

import java.awt.*;

/**
 * A program that displays each roll of the dice and the current amount of money in an Applet
 * for {@link chalmersw08}
 *
 * @author Willie Chalmers III
 * @since 10/6/17
 */
public class chalmersw08ec extends Panel implements chalmersw08.DieGame.DieRollCallback {

    private static final boolean RESIZEABLE = false;

    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;
    private int width = 1280 - 10;
    private int height = 1024 - 10;

    private chalmersw08.DieGame dieGame;
    private DieGameDrawer renderer;

    public static void main(String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        chalmersw08ec window = new chalmersw08ec();
        window.setSize(1050, 700);
        f.add(window);
        f.pack();
        window.init();
        f.setSize(1050, 700); // Size of frame
        f.setVisible(true);
    }

    public void resizeWindow() {
        if (RESIZEABLE) {
            if (getHeight() != height + 10 || getWidth() != width + 10) {
                height = getHeight() - 10;
                width = getWidth() - 10;
                Image virtualMem = createImage(width + 20, height + 20);
                Graphics2D gBuffer = (Graphics2D) virtualMem.getGraphics();
                gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        } else {
            setSize(width + 10, height + 10);
        }
    }

    /**
     * Constantly redraws the displayed image
     *
     * @param g
     */
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

    /**
     * Initializes game variables.
     */
    public void init() {
        renderer = new DieGameDrawer(getGraphics());
        dieGame = chalmersw08.DieGame.startNewGame(100, this);
    }

//    /**
//     * This is where most front-end game code should be called.
//     *
//     * @param g
//     */
//    @Override
//    public void paint(Graphics g) {
//        // Insert code here
//
//        repaint();
//    }

    /**
     * Pauses the main thread for 20 milliseconds
     */
    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (Exception ignored) {
            // no-op
        }
    }

    @Override
    public void onRoll(int rollOne, int rollTwo) {
        renderer.displayDieRoll(rollOne, rollTwo);
        sleep();
    }

    private static class DieGameDrawer {

        private Graphics graphics;

        public DieGameDrawer(Graphics graphics) {
            this.graphics = graphics;
        }

        public void displayDieRoll(int first, int second) {
            graphics.drawString("You rolled " + first + " and " + second, 100, 100);
        }
    }
}