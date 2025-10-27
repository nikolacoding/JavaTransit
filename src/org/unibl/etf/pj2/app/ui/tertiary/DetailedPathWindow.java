package org.unibl.etf.pj2.app.ui.tertiary;

import org.unibl.etf.pj2.app.state.StateManager;
import org.unibl.etf.pj2.app.ui.shared.GeneralButton;
import org.unibl.etf.pj2.app.ui.shared.GeneralLabel;
import org.unibl.etf.pj2.app.ui.tertiary.table.DetailedPathTableScrollPane;
import org.unibl.etf.pj2.app.ui.shared.Listeners.ButtonListeners;
import org.unibl.etf.pj2.app.util.constants.TextConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Wrapper klasa koja prosiruje JFrame i postavlja izgled detaljnog prikaza neke putanje, sto se
 * korisniku prikazuje ako odabere da kupi kartu ili pogleda putanju detaljnije pritiskom na odgovarajuce dugme.
 *
 * @author Nikola Markovic
 */
public final class DetailedPathWindow extends JFrame {

    public static DetailedPathTableScrollPane scrollPane = null;
    public static JLabel priceLabel = new GeneralLabel("", Color.black);
    public static JLabel departureTimeLabel = new GeneralLabel("", Color.black);
    public static JLabel arrivalTimeLabel = new GeneralLabel("", Color.black);

    /**
     * Konstruktor, po pozivu, u potpunosti namjesta konacni stil prikaza datog prozora i dodaje Listenere koji
     * ce da rukuju funkcionalnoscu. Prikazana tabela se definise spolja.
     *
     * @author Nikola Markovic
     */
    public DetailedPathWindow(){
        super(TextConstants.DETAILED_PATHS_WINDOW_TITLE);

        final StateManager smInstance = StateManager.getInstance();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        final JPanel scrollPanePanel = new JPanel();
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        final JPanel labelsPanel = new JPanel();
        final JButton confirmPurchaseButton = new GeneralButton(TextConstants.CONFIRM_PURCHASE_BUTTON_TEXT);
        confirmPurchaseButton.addActionListener(ButtonListeners.confirmPurchaseListener);

        scrollPanePanel.add(scrollPane);
        this.add(scrollPanePanel, BorderLayout.CENTER);

        labelsPanel.add(priceLabel);
        labelsPanel.add(departureTimeLabel);
        labelsPanel.add(arrivalTimeLabel);
        bottomPanel.add(labelsPanel, BorderLayout.CENTER);
        bottomPanel.add(confirmPurchaseButton, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);

        smInstance.addActiveJFrame(this);
    }
}
