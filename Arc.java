import java.awt.*;
/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing Arc shape
 * Implements Shape
 *
 * @author Cameron Hardy
 */
public class Arc implements Shape {
    /**
     * @Field x - int containing Arc's center x coordinate
     * @Field y - int containing Arc's center y coordinate
     * @Field xrad - int containing Arc's x radius
     * @Field theta - int containing Arc's end angle
     */
    int x, y, xrad, theta;
    /**
     * @Field color - Color object containing the shapes color
     */
    Color color;

    /**
     * Creates shape object of type Arc, initializing x, y, xrad, theta, and color
     * @param _x - int x coordinate of the Arc
     * @param _y - int y coordinate of the Arc
     * @param _xrad - int xrad of the Arc
     * @param _theta - int theta of the Arc
     * @param _color - Color the shape is to be filled with
     */
    public Arc(int _x, int _y, int _xrad, int _theta, Color _color) {
        x = _x;
        y = _y;
        xrad = _xrad;
        theta = _theta;
        color = _color;
    }
    /**
     * Overridden draw function used for all shapes
     * Draws/Fills Arc given center (x, y), yrad = xrad / 2, start angle of 0
     * and end angle of theta
     * @param g - Graphics object to be drawn to
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillArc(x - xrad / 2, y- xrad / 4, xrad, xrad / 2, 0, theta);
    }
    /**
     * Same as draw() except uses Graphics2D to outline shape
     * Method used when the shape is still being changed
     * @param g - Graphics object to be drawn to
     */
    @Override
    public void outline(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));
        g2d.setColor(Color.WHITE);
        g2d.fillArc(x - xrad /2, y - xrad/4, xrad, xrad/2, 0, theta);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x - xrad /2, y - xrad/4, xrad, xrad/2, 0, theta);
    }
    /**
     * Update the Arc's parameters for the new mouse position
     * @param _x - new int x position
     * @param _y - new int y position
     */
    @Override
    public void update(int _x, int _y) {
        int dx, dy;
        dx = x - _x;
        dy = y - _y;
        theta = ((int) (Math.atan2(-dy, dx) * (360 / (2 * Math.PI)))) + 180;
        xrad = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
    /**
     * Function to print information about the given shape
     * Used for testing functionality
     */
    @Override
    public void printMe() {
        System.out.println("Arc with center x, y: " + x + ", " + y + ", xradius =" + xrad + " and an angle of " + theta);
    }
}
