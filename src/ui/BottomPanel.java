package ui;

import util.Constants;

import javax.swing.*;
import java.awt.*;

public final class BottomPanel extends JPanel {
    public BottomPanel(){
        super(new BorderLayout());

        this.setLayout(new BorderLayout());

        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.BOTTOM_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel("Options", Color.white, SwingConstants.CENTER));
        labelPanel.setBackground(Color.darkGray);
        this.add(labelPanel, BorderLayout.NORTH);
    }
}
