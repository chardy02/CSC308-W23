import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Class containing painting board functionality
 * Extends JPanel and implements Mouse[Motion]Listeners for user cursor input
 *
 * @author Cameron Hardy
 */

public class PointBoard extends JPanel implements MouseListener, MouseMotionListener, Observer {
    /**
     * @Field Processor - PointProcessor reference
     */
    private PointProcessor processor = PointProcessor.getRef();
    /**
     * @Field clusters - List of Array lists containing the point
     * information for both clusters
     */
    private ArrayList<Point>[] clusters = new ArrayList[2];
    /**
     * @Field lines - Array list containing the point
     * information for the connecting lines to be drawn
     */
    private ArrayList<Point> lines;

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
        ArrayList<Point> pts = processor.getPts();
        for(Point p : pts) {
            g.fillOval(p.x, p.y, 4, 4);
        }
        drawClusters(g);
        drawLines(g);

        repaint();
    }
    /**
     * Method overridden from MouseListener, detects when user presses the mouse
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mousePressed(MouseEvent e) {
        processor.add(new Point(e.getX(), e.getY()));
    }
    /**
     * Method overridden from MouseListener, detects when user releases the mouse
     * @param e - MouseEvent to be passed through to subsequent functions
     */
    public void mouseReleased(MouseEvent e) { }
    /**
     * Empty method required to implement
     * MouseListener interface.
     * @param e - MouseEvent processed
     */
    public void mouseClicked(MouseEvent e) { }
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
    public void mouseDragged(MouseEvent e) {}

    /**
     * Method to draw the colored points when the clustering box is checked
     * @param g - Graphics object associated with the panel
     */
    public void drawClusters(Graphics g) {
        if(clusters[0] == null) {
            return;
        }
        g.setColor(Color.RED);
        for(Point p : clusters[0]) {
            g.fillOval(p.x, p.y, 5, 5);
        }
        g.setColor(Color.BLUE);
        for(Point p : clusters[1]) {
            g.fillOval(p.x, p.y, 5, 5);
        }
    }

    /**
     * Method to draw the connecting lines given by the nearest neighbor algorithm
     * @param g - Graphics object associated with the panel
     */
    public void drawLines(Graphics g) {
        if(lines == null) {
            return;
        }
        g.setColor(Color.BLACK);
        for(int i = 1; i < lines.size(); i++) {
            g.drawLine(lines.get(i).x, lines.get(i).y, lines.get(i - 1).x, lines.get(i - 1).y);
        }
        g.drawLine(lines.get(0).x, lines.get(0).y, lines.get(lines.size() - 1).x, lines.get(lines.size() - 1).y);
    }

    /**
     * Overridden update method to update the clusters or lines information
     * Called when notifyObservers is used in PointProcessor
     * @param o - Observable object which called update on this Observer
     * @param arg - Object passed through the update call
     */
    @Override
    public void update(Observable o, Object arg) {
        PointInfo ptData = (PointInfo) arg;
        if(ptData.type == "Cluster") {
            clusters = ptData.data;
        }
        if(ptData.type == "Lines") {
            lines = ptData.data[0];
        }
    }
}