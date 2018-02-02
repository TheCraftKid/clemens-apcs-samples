package com.thecraftkid.apcs;

import java.awt.*;

/**
 * A utility class for making UI-based applications for AP Computer Science.
 *
 * @version 2.0.0
 * @since 10/4/17
 */
public class GameStart extends Panel {

    private static final boolean RESIZEABLE = false;

    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;
    private int width = 1280 - 10;
    private int height = 1024 - 10;

    public static void main(String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        // Make sure to update class name here
        GameStart window = new GameStart();
        window.setSize(1050, 700);
        f.add(window);
        f.pack();
        window.init();
        f.setSize(1050, 700);
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
    @Override
    public void update(Graphics g) {
        super.update(g);
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
     * This is where most front-end game code should be called.
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Insert code here

        repaint();
    }

    /**
     * Initializes game variables.
     */
    public void init() {

    }

    /**
     * Pauses the main thread for 20 milliseconds
     */
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
            // no-op
        }
    }

    private void sleep() {
        sleep(20);
    }
}