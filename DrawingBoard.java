import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.ArrayList;

/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing painting board functionality
 * Extends JPanel and implements Mouse[Motion]Listeners for user cursor input
 *
 * @author Cameron Hardy
 */

public class DrawingBoard extends JPanel implements MouseListener, MouseMotionListener {
    /** @Field uniqColors - An array containing the available
     * colors to draw with
     */
    String[] uniqColors = {"Black", "Red", "Blue", "Green", "Yellow"};
    /** @Field colors - A hash table with key-value pairs of String, Color
     * Used to convert string given by JButton events to desired color
     * Initialized with all current colors
     */
    Hashtable<String , Color> colors = new Hashtable<String, Color>() {
        {
            put(uniqColors[0], Color.BLACK);
            put(uniqColors[1], Color.RED);
            put(uniqColors[2], Color.BLUE);
            put(uniqColors[3], Color.GREEN);
            put(uniqColors[4], Color.YELLOW);
        }
    };

    /** @Field colors - Current color being drawn
     */
    public Color color = Color.BLACK;
    /** @Field shapeMode - String to keep track of selected shape
     */
    public String shapeMode = "Rectangle";
    /** @Field newestShape - Used to store shapes between start of drawing and release of mouse-button
     */
    public Shape newestShape;
    /** @Field shapes - ArrayList containing all shapes drawn by the user
     */
    public ArrayList<Shape> shapes = new ArrayList<Shape>();

    /**
     * Creates an instance of DrawingBoard, extending and used in the place of
     * JPanel, allows user to draw shapes in varying colors
     */
    public DrawingBoard() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * paintComponent method inherited by JPanel, called automatically
     * draw each individual shape in "shapes" and outline the "newestShape"
     * @param g - Graphics object associated with the JPanel/DrawingBoard
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Shape s : shapes) {
            s.draw(g);
        }
        if (newestShape != null) {
            newestShape.outline(g);
        }
    }

    /**
     * Method called in Main class to change the current color being drawn
     * @param _color - String containing desired color
     */
    public void selectColor(String _color) {
        color = colors.get(_color);
    }

    /**
     * Method used to create "undo" button functionality
     * Removed last added shape from the shapes ArrayList
     */
    public void undo() {
        shapes.remove(shapes.size() - 1);
        repaint();
    }

    /**
     * Method used to create "erase" button functionality
     * Clears all shapes from shapes ArrayList
     */
    public void erase() {
        shapes.clear();
        repaint();
    }

    /**
     * Method called in Main class to change the shape being drawn
     * @param _shape - String containing desired shape
     */
    public void selectShape(String _shape) {
        shapeMode = _shape;
    }

    /**
     * Creates current shape at given mouse coordinates, storing the current color in the shape
     * @param e - MouseEvent to get mouse(x and y) position
     */
    public void startShape(MouseEvent e) {
        switch(shapeMode) {
            case("Rectangle"):
                newestShape = new Rectangle(e.getX(), e.getY(), 0, 0, color);
                break;
            case("Circle"):
                newestShape = new Circle(e.getX(), e.getY(), 0, color);
                break;
            case("Arc"):
                newestShape = new Arc(e.getX(), e.getY(), 5, 0, color);
                break;
        }
    }

    /**
     * Ends drawing process, adding shape to the ArrayList with all other drawn shapes, set newestShape to null
     * If drawing rectangles, rearrange...
     * @param e
     */
    public void endShape(MouseEvent e) {
        if(shapeMode == "Rectangle") {
            Rectangle rect = (Rectangle) newestShape;
            rect.rearrange();
        }
        shapes.add(newestShape);
        newestShape = null;
    }

    /**
     * Method overridden from MouseListener, detects when user presses the mouse
     * Calls startShape() and repaint()
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mousePressed(MouseEvent e) {
        startShape(e);
        repaint();
    }
    /**
     * Method overridden from MouseListener, detects when user releases the mouse
     * Calls endShape() and repaint()
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mouseReleased(MouseEvent e) {
        endShape(e);
        repaint();
    }

    /**
     * Empty method required to implement
     * MouseListener interface.
     * @param e - MouseEvent processed
     */
    public void mouseClicked(MouseEvent e) {}
    /**
     * Empty method required to implement
     * MouseListener interface.
     * @param e - MouseEvent processed
     */
    public void mouseEntered(MouseEvent e) {}
    /**
     * Empty method required to implement
     * MouseListener interface.
     * @param e - MouseEvent processed
     */
    public void mouseExited(MouseEvent e) {}
    /**
     * Empty method required to implement
     * MouseListener interface.
     * @param e - MouseEvent processed
     */
    public void mouseMoved(MouseEvent e) {}
    /**
     * Method overridden from MouseMotionListener, detects when mouse is dragged
     * If a shape is being drawn, update the shape and repaint()
     * @param e - MouseEvent processed
     */
    public void mouseDragged(MouseEvent e) {
        if (newestShape != null) {
            newestShape.update(e.getX(), e.getY());
            repaint();
        }
    }
}