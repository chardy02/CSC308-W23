import java.awt.*;
/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Abstract class for implementing drawing of varying shapes
 *
 * @author Cameron Hardy
 */
public interface Shape {
    /**
     * @Field color - Color object containing current color
     */
    public Color color = null;

    /**
     * Generic draw function shape of any type
     * @param g - Graphics object to be drawn to
     */
    public void draw(Graphics g);

    /**
     * Generic update function to update parameters of the shape as
     * it is still being drawn, given new mouse position (x, y)
     * @param _x - New int x position
     * @param _y - New int y position
     */
    public void update(int _x, int _y);

    /**
     * Same as draw but used to outline the shape
     * Used when the shape is still being changed
     * @param g
     */
    public void outline(Graphics g);
    /**
     * Function to print information about the given shape
     * Used for testing functionality
     */
    public void printMe();
}
