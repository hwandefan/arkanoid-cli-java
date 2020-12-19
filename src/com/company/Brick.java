package com.company;

/**
 * Class for Brick object
 */
public class Brick extends BaseObject {
    //Canvas for drawing
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y) {
        super(x, y, 3);
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    /**
     * Do nothing because bricks doesn't move (but this funtion needs to be @Override)
     */
    @Override
    void move() {
        //Do nothing because bricks doesn't move
    }
}
