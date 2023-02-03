import java.awt.*;
/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing Circle shape
 * Implements Shape
 *
 * @author Cameron Hardy
 */
public class Circle implements Shape {
    /**
     * @Field cx - int containing Circle's center x coordinate
     * @Field cy - int containing Circle's center y coordinate
     * @Field r - int containing Circle's radius
     */
    int cx, cy, r;
    /**
     * @Field color - Color object containing the shapes color
     */
    Color color;
    /**
     * Creates shape object of type Circle, initializing x, y, r, and color
     * @param _cx - int x coordinate of the Circle
     * @param _cy - int y coordinate of the Circle
     * @param _r - int xrad of the Circle
     * @param _color - Color the shape is to be filled with
     */
    public Circle(int _cx, int _cy, int _r, Color _color) {
        cx = _cx;
        cy = _cy;
        r = _r;
        color = _color;
    }
    /**
     * Overridden draw function used for all shapes
     * Draws/Fills Circle given center position (cx, cy) and radius r
     * @param g - Graphics object to be drawn to
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(cx - r, cy - r, r * 2, r * 2);
    }
    /**
     * Same as draw() except uses Graphics2D to outline shape
     * Method used when the shape is still being changed
     * @param g - Graphics object to be drawn to
     */
    public void outline(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(cx - r, cy - r, r * 2, r * 2);
    }
    /**
     * Update the Circle's radius for the new mouse position
     * @param _x - new int x position
     * @param _y - new int y position
     */
    public void update(int _x, int _y) {
        r = (int) Math.sqrt(Math.pow(cx - _x, 2) + Math.pow(cy - _y, 2));
    }
    /**
     * Function to print information about the given shape
     * Used for testing functionality
     */
    public void printMe() {
        System.out.println("Circle with radius " + r + " and position x, y: " + cx + ", " + cy);
    }
}
