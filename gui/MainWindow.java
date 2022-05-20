package qualto.gui;

import qualto.main;
import javax.swing.*;

// Mainwindow class
public class MainWindow extends JFrame {
    // Mainwindow constructor
    public MainWindow() {
        // Set window title
        setTitle("QUALTO");
        // Set window size
        setSize(800, 600);
        // Set window location
        setLocation(100, 100);
        // Set window to close when closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window to visible
        setVisible(true);
    }

    // show window
    public static void main(String[] args) {
        new MainWindow();
    }
}
