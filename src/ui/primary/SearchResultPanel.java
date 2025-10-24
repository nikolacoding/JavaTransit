package ui.primary;

import util.Constants;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public final class SearchResultPanel extends TitledPanel {

    private static final Color bgColor = new Color(242, 240, 100);
    private JTable topResultTable;

    public SearchResultPanel(){
        super("Rezultat", bgColor, Color.black);

        final JPanel rightPanel = new JPanel();
        final JPanel leftPanel = new JPanel();

        rightPanel.setBackground(bgColor);
        leftPanel.setBackground(bgColor);

        rightPanel.setPreferredSize(new Dimension(300, Constants.TITLED_PANEL_HEIGHT));

        final JButton extraButton = new JButton("Detaljno");
        extraButton.setFocusable(false);
        rightPanel.add(extraButton);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
