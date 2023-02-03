import java.awt.*;
/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing Rectangle shape
 * Implements Shape
 *
 * @author Cameron Hardy
 */
public class Rectangle implements Shape {
    /**
     * @Field x - int containing Rectangle's starting x coordinate
     * @Field y - int containing Rectangle's starting y coordinate
     * @Field w - int containing Rectangle's width
     * @Field h - int containing Rectangle's height
     */
    public int x, y, w, h;
    /**
     * @Field color - Color object containing the shapes color
     */
    Color color;
    /**
     * Creates shape object of type Rectangle, initializing x, y, w, h, and color
     * @param _x - int x coordinate of the Rectangle
     * @param _y - int y coordinate of the Rectangle
     * @param _w - int width of the Rectangle
     * @param _h - int height of the Rectangle
     * @param _color - Color the shape is to be filled with
     */
    public Rectangle(int _x, int _y, int _w, int _h, Color _color) {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        color = _color;
    }
    /**
     * Overridden draw function used for all shapes
     * Draws/Fills Rectangle given center (x, y), width, and height
     * @param g - Graphics object to be drawn to
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillRect(x, y, w, h);
    }
    /**
     * Same as draw() except uses Graphics2D to outline shape
     * Method used when the shape is still being changed
     * @param g - Graphics object to be drawn to
     */
    public void outline(Graphics g) {
        // Uses dx, dy, dw, dh as temporary parameters to draw the rectangle correctly
        // If width is negative... mouse x is less than start x... change dx and dw
        // If height is negative... mouse y is less than stary y... change dy and dh
        int dx, dy, dw, dh;
        if(w < 0) {
            dw = -w;
            dx = x + w;
        }
        else {
            dx = x; dw = w;
        }
        if(h < 0) {
            dh = -h;
            dy = y + h;
        }
        else {
            dy = y; dh = h;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(dx, dy, dw, dh);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(dx, dy, dw, dh);
    }
    /**
     * Update the Rectangles parameters for the new mouse position
     * @param _x - new int x position
     * @param _y - new int y position
     */
    public void update(int _x, int _y) {
        w = _x - x;
        h = _y - y;
    }
    /**
     * Function to print information about the given shape
     * Used for testing functionality
     */
    public void printMe() {
        System.out.println("Rectangle with x, y, w, h: " + x + ", " + y + ", " + w + ", " + h);
    }
    /**
     * Method called when Rectangle is done being drawn
     * Rearranges coordinates if width or height are negative
     */
    public void rearrange() {
        // Same as in outline, but permanent change to parameters
        int dx, dy, dh, dw;
        if(w < 0) {
            dw = -w;
            dx = x + w;
        }
        else {
            dx = x; dw = w;
        }
        if(h < 0) {
            dh = -h;
            dy = y + h;
        }
        else {
            dy = y; dh = h;
        }
        x = dx; y = dy; w = dw; h = dh;
    }
}
