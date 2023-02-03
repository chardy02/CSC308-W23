import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
/**
 * CSC 308 Assignment 1 - Paint App
 *
 * Main class - Displays UI including buttons, frames, and color selector
 * and DrawingBoard
 * Extends JFrame and implements ActionListener
 *
 * @author Cameron Hardy
 */
public class Main extends JFrame implements ActionListener {
    DrawingBoard drawBoard;

    /**
     * Main function - run when the program is run
     * Creates new instance of Main
     * @param args - user arguments input, normally through commandline
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(500, 500);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    /**
     * Constructor for Main object
     * Contains all UI mentioned in description
     * Mostly code copied from lecture slides, changed as little as possible
     */
    public Main() {
        super("Paint App");
        // South
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.CYAN);
        JButton b1 = new JButton("Undo");
        JButton b2 = new JButton("Erase");
        southPanel.add(b1);
        southPanel.add(b2);
        // West
        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.PINK);

        String[] colors = {"Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox list = new JComboBox(colors);
        JRadioButton rect = new JRadioButton("Rectangle");
        JRadioButton circle = new JRadioButton("Circle");
        JRadioButton arc = new JRadioButton("Arc");
        rect.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rect);
        group.add(circle);
        group.add(arc);
        GridLayout grid = new GridLayout(7, 1);
        westPanel.setLayout(grid);
        westPanel.add(list);
        westPanel.add(rect);
        westPanel.add(circle);
        westPanel.add(arc);
        // Center
        JPanel centerPanel = new DrawingBoard();
        centerPanel.setBackground(Color.CYAN);
        drawBoard = (DrawingBoard) centerPanel;
        // Frame
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        list.addActionListener(this);
        rect.addActionListener(this);
        circle.addActionListener(this);
        arc.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    /**
     * Overridden function from ActionListener
     * Is called when one of the UI buttons is interacted with
     * Undos, Erases, changes color, or changes shape depending on the button pressed
     * @param e - ActionEvent to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case("Undo"):
                drawBoard.undo();
                break;
            case("Erase"):
                drawBoard.erase();
                break;
            case("comboBoxChanged"):
                drawBoard.selectColor("" + ((JComboBox) e.getSource()).getSelectedItem());
                break;
            case("Rectangle"):
            case("Circle"):
            case("Arc"):
                drawBoard.selectShape(e.getActionCommand());
                break;

        }
    }
}