package ui.shared;

import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Wrapper klasa za JPanel koja sluzi za ustaljeno kreiranje panela sa naslovom na vrhu i
 * odgovarajucim bojama.
 * @author Nikola Markovic
 */
public class TitledPanel extends JPanel {
    /**
     * Konstruktor, po pozivu, postavlja layout panela na BorderLayout, kaci naslovni JLabel
     * na vrh, glavni panel (trenutno bez sadrzaja) na centar i ustaljuje boje izmedju njih.
     *
     * @param title Naslov
     * @param bgColor Boja pozadine
     * @param labelColor Boja naslovnog teksta
     * @author Nikola Markovic
     */
    public TitledPanel(String title, Color bgColor, Color labelColor){
        super(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.setBackground(bgColor);
        this.setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH_MAX, UIConstants.TITLED_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel(title, labelColor, SwingConstants.CENTER));
        labelPanel.setBackground(bgColor);
        this.add(labelPanel, BorderLayout.NORTH);
    }
}
