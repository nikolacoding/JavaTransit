package ui.primary;

import util.Constants;

import javax.swing.*;
import java.awt.*;

public class TitledPanel extends JPanel {
    public TitledPanel(String title, Color bgColor, Color labelColor){
        super(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.setBackground(bgColor);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.TITLED_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel(title, labelColor, SwingConstants.CENTER));
        labelPanel.setBackground(bgColor);
        this.add(labelPanel, BorderLayout.NORTH);
    }
}
