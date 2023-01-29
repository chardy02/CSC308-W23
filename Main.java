import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Main extends JFrame implements ActionListener {
    DrawingBoard drawBoard;

    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(500, 500);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

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
        centerPanel.setBackground(Color.GRAY);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case("Undo"):
                System.out.println("Undoing last drawing");
                break;
            case("Erase"):
                System.out.println("Erasing DrawingBoard");
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