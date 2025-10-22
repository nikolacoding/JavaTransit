package ui;

import javax.swing.*;
import java.awt.*;

public final class MainWindow extends JFrame {
    final JPanel mainPanel;
    final JPanel bottomPanel;

    public MainWindow(){
        super("JavaTransit");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        mainPanel = new MainPanel();
        bottomPanel = new BottomPanel();

        this.add(mainPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }
}