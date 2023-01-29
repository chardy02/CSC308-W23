import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.ArrayList;


public class DrawingBoard extends JPanel implements MouseListener, MouseMotionListener {
    String[] uniqColors = {"Black", "Red", "Blue", "Green", "Yellow"};
    Hashtable<String , Color> colors = new Hashtable<String, Color>(){
        {
            put(uniqColors[0], Color.BLACK);
            put(uniqColors[1], Color.RED);
            put(uniqColors[2], Color.BLUE);
            put(uniqColors[3], Color.GREEN);
            put(uniqColors[4], Color.YELLOW);
        }
    };
    public Color color = Color.BLACK;
    public int shapeMode = 0;
    // 0 - 1 - 2
    // Rect - Circle - Arc
    public ArrayList<Shape> shapes = new ArrayList<Shape>();

    public DrawingBoard() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g) {
        for(Shape s : shapes) {
            s.draw(g);
        }
//        g.setColor(Color.RED);
//        g.fillRect(10, 10, 100, 100);
    }
    public void selectColor(String _color) {
        color = colors.get(_color);
    }
    public void selectShape(String _shape) {
        switch(_shape) {
            case("Rectangle"):
                shapeMode = 0;
                break;
            case("Circle"):
                shapeMode = 1;
                break;
            case("Arc"):
                shapeMode = 2;
                break;
        }
    }

    public void startShape() {
        System.out.println("Starting shape...");
        System.out.println("Shape mode is " + shapeMode);
        System.out.println("Color is " + color);
    }
    public void endShape() {
        System.out.println("Ending shape...");
    }
    public void mousePressed(MouseEvent e) {
        startShape();
    }
    public void mouseReleased(MouseEvent e) {
        endShape();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}