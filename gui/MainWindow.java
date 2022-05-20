import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MainWindow class
public class MainWindow extends JFrame {
    // constructor
    public MainWindow() {
        // MainWindow class
        super("QUARTO");
        // MainWindow class
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // MainWindow class
        setSize(800, 600);
        // MainWindow class
        setVisible(true);

        JButton startButton = new JButton("Start");
        JButton rulesButton = new JButton("Rules");

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(rulesButton);

        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);

    }

}