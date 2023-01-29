import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel {

    public Color color;
    public int shapeMode;

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 10, 100, 100);
    }
    public void selectColor(String _color) {


    }
}