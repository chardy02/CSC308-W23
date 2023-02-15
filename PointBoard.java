import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing painting board functionality
 * Extends JPanel and implements Mouse[Motion]Listeners for user cursor input
 *
 * @author Cameron Hardy
 */

public class PointBoard extends JPanel, Obeserver implements MouseListener, MouseMotionListener {

    public String shapeMode = "Rectangle";

    /**
     * Creates an instance of PaintBoard
     * Add Mouse and MouseMotion listeners
     */
    public PointBoard() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * paintComponent method inherited by JPanel, called automatically
     * @param g - Graphics object associated with the JPanel/DrawingBoard
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Method called in Main class to update the mode
     * @param _mode - Mode to toggle
     */
    public void updateMode(String _mode) {
        mode = _mode;
    }

    /**
     * Creates point at given mouse coordinates, adding to list of points
     * @param e - MouseEvent to get mouse(x and y) position
     */
    public void placePoint(MouseEvent e) {

    }

    /**
     * Method overridden from MouseListener, detects when user presses the mouse
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mousePressed(MouseEvent e) {
        repaint();
    }
    /**
     * Method overridden from MouseListener, detects when user releases the mouse
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mouseReleased(MouseEvent e) {
        repaint();
    }

    @Override


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
     * @param e - MouseEvent processed
     */
    public void mouseDragged(MouseEvent e) {

    }
}