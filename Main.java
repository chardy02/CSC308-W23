import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Hashtable;
/**
 * CSC 308 Assignment 2 - 
 *
 * Main class - Displays UI including buttons, frames, and PointBoard
 * Extends JFrame and implements ActionListener
 *
 * @author Cameron Hardy
 */
public class Main extends JFrame implements ActionListener {
    /**
     * @Field pointBoard - Reference to the PointBoard instance used
     */
    public PointBoard pointBoard;

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
        // West
        JPanel westPanel = new JPanel();
//        westPanel.setBackground(Color.PINK);

        GridLayout grid = new GridLayout(7, 1);

        JCheckBox cluster = new JCheckBox("Cluster - K-means");
        JCheckBox lines = new JCheckBox("Lines - Nearest Neighbor");
        JButton run = new JButton("Run");

        westPanel.setLayout(grid);
        westPanel.add(cluster);
        westPanel.add(lines);
        westPanel.add(run);

        // Center
        JPanel centerPanel = new PointBoard();
        centerPanel.setBackground(Color.WHITE);
        pointBoard = (PointBoard) centerPanel;


        PointProcessor.getRef().addObserver(pointBoard);

        // Frame
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        cluster.addActionListener(this);
        lines.addActionListener(this);
        run.addActionListener(this);

    }

    /**
     * Overridden function from ActionListener
     * Is called when one of the UI buttons is interacted with
     * Updates the state of the PointProcessor
     * @param e - ActionEvent to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PointProcessor p = PointProcessor.getRef();
        p.updateState(e.getActionCommand());
    }
}