package ui.primary;

import state.StateManager;
import state.UIManager;
import util.constants.TextConstants;
import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;

public final class MainWindow extends JFrame {
    final MainPanel mainPanel;
    final OptionsPanel optionsPanel;
    final SearchResultPanel searchResultPanel;

    public MainWindow(){
        super(TextConstants.MAIN_WINDOW_TITLE);

        final UIManager uimInstance = UIManager.getInstance();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        mainPanel = new MainPanel();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH_MAX, UIConstants.BOTTOM_PANEL_HEIGHT));
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

        uimInstance.setMainPanel(this.mainPanel);
        uimInstance.setOptionsPanel(this.optionsPanel);
        uimInstance.setSearchResultPanel(this.searchResultPanel);
    }
}