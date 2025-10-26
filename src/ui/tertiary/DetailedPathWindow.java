package ui.tertiary;

import ui.tertiary.table.DetailedPathTable;
import ui.tertiary.table.DetailedPathTableScrollPane;

import javax.swing.*;
import java.awt.*;

public final class DetailedPathWindow extends JFrame {

    public static DetailedPathTableScrollPane scrollPane = null;
    public static JLabel priceLabel = new JLabel();
    public static JLabel startTimeLabel = new JLabel();
    public static JLabel endTimeLabel = new JLabel();

    public DetailedPathWindow(){
        super("Kupovina karte");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        final JPanel scrollPanePanel = new JPanel();
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        final JPanel labelsPanel = new JPanel();

        scrollPanePanel.add(scrollPane);
        this.add(scrollPanePanel, BorderLayout.CENTER);

        labelsPanel.add(priceLabel);
        labelsPanel.add(startTimeLabel);
        labelsPanel.add(endTimeLabel);
        bottomPanel.add(labelsPanel, BorderLayout.CENTER);
        bottomPanel.add(new JButton("Potvrdi kupovinu"), BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }
}
