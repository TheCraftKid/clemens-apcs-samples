package com.thecraftkid.apcs;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A program which displays a solid colored circle which bounces off of invisible walls.
 *
 * @author Willie Chalmers III
 * @since 9/28/17
 */
public class chalmersw07ec extends Panel {

    // TODO: 9/29/2017 Find way to set this dynamically
    public static final int SCREEN_X_SIZE = 1280;
    public static final int SCREEN_Y_SIZE = 1024;

    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;

    private Ball ball;


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
        chalmersw07ec window = new chalmersw07ec();
        window.setSize(1050, 700);
        f.add(window);
        f.pack();
        window.init();
        f.setSize(1050, 700);
        f.setVisible(true);
    }


    public void init() {
        ball = new Ball();
    }

    public void paint(Graphics g) {
//        TODO: 1) Draw circle on screen, 2) Move circle, 3) bounce off wall, 4) repeat

        try {
            Thread.sleep(20);
        } catch (Exception ignored) {
            // no-op
        }
        repaint();
    }

    public void bounce() {

    }

    public void move() {

    }

    class Ball {

        private Point point;

        public Ball() {
            this(0, 0);
        }

        public Ball(int x, int y) {
            point = new Point(x, y);
        }

        public void move() {
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (x + getXSize() < chalmersw07ec.SCREEN_X_SIZE) {
                x++;
            }
            if (y + getYSize() < chalmersw07ec.SCREEN_Y_SIZE) {
                y++;
            }
        }

        public void draw(Graphics g) {
            g.drawOval((int) point.getX(), (int) point.getY(), 10, 10);
        }

        public int getXSize() {
            return 5;
        }

        public int getYSize() {
            return 5;
        }

    }

}
