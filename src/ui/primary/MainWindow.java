package ui.primary;

import input.StateManager;
import util.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.State;
import java.awt.*;

public final class MainWindow extends JFrame {
    final MainPanel mainPanel;
    final OptionsPanel optionsPanel;
    final SearchResultPanel searchResultPanel;

    public MainWindow(){
        super("JavaTransit");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        mainPanel = new MainPanel();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.BOTTOM_PANEL_HEIGHT));
        optionsPanel = new OptionsPanel();
        searchResultPanel = new SearchResultPanel();
        bottomPanel.add(optionsPanel, BorderLayout.SOUTH);
        bottomPanel.add(searchResultPanel, BorderLayout.NORTH);

        this.add(mainPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);

        StateManager.getInstance().setMainPanel(this.mainPanel);
        StateManager.getInstance().setOptionsPanel(this.optionsPanel);
        StateManager.getInstance().setSearchResultPanel(this.searchResultPanel);
    }
}