package com.company;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Main game class
 */
public class Arkanoid {
    // width and height
    private int width;
    private int height;

    // List of bricks
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    // The ball
    private Ball ball;
    // The stand
    private Stand stand;

    // end game boolean variable
    private boolean isGameOver = false;

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     * Draw borders and all objects
     */
    void draw(Canvas canvas) {
        drawBorders(canvas);

        // draw bricks
        for (Brick brick : bricks) {
            brick.draw(canvas);
        }

        // draw ball
        ball.draw(canvas);

        // draw stand
        stand.draw(canvas);

    }

    /**
     * Draw borders
     */
    private void drawBorders(Canvas canvas) {
        // draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    /**
     * main cycle of program
     */
    void run() throws Exception {
        // Draw canvas for sorting
        Canvas canvas = new Canvas(width, height);

        // Create Hanler for keyboard
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        // If NOT GameOver - infinity cycle
        while (!isGameOver) {
            // Handle keys
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                // LEFT BUTTON key
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                    // RIGHT BUTTON key
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                    // if Space - start move the ball
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            // move all objects
            move();

            // Chek bumps
            checkBricksBump();
            checkStandBump();

            // Check engGame
            checkEndGame();

            // Draw all objects
            canvas.clear();
            draw(canvas);
            canvas.print();

            // pause
            Thread.sleep(300);
        }

        // Get message
        System.out.println("Game Over!");
    }

    /**
     * Move the ball and the stand
     */
    public void move() {
        ball.move();
        stand.move();
    }

    /**
     * Check bumps with bricks
     */
    void checkBricksBump() {
        for (Brick brick : new ArrayList<Brick>(bricks)) {
            if (ball.isIntersec(brick)) {
                double angle = Math.random() * 360;
                ball.setDirection(angle);

                bricks.remove(brick);
            }
        }
    }

    void checkStandBump() {
        if (ball.isIntersec(stand)) {
            double angle = 90 + 20 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    /**
     * (isGameOver = true)
     */
    void checkEndGame() {
        if (ball.getY() > height && ball.getDy() > 0)
            isGameOver = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static Arkanoid game;

    public static void main(String[] args) throws Exception {
        game = new Arkanoid(20, 30);

        Ball ball = new Ball(10, 29, 2, 95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}



















