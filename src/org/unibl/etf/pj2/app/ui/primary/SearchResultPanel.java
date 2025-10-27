package org.unibl.etf.pj2.app.ui.primary;

import org.unibl.etf.pj2.app.pathfinding.yen.types.PathObject;
import org.unibl.etf.pj2.app.serialization.Serializer;
import org.unibl.etf.pj2.app.state.UIManager;
import org.unibl.etf.pj2.app.state.StateManager;
import org.unibl.etf.pj2.app.ui.shared.GeneralButton;
import org.unibl.etf.pj2.app.ui.shared.GeneralLabel;
import org.unibl.etf.pj2.app.ui.shared.TitledPanel;
import org.unibl.etf.pj2.app.util.Time;
import org.unibl.etf.pj2.app.util.constants.TextConstants;
import org.unibl.etf.pj2.app.util.constants.UIConstants;
import org.unibl.etf.pj2.app.ui.shared.Listeners.ButtonListeners;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa koja definise panel koji opisuje rezultat aktuelne, relevantne radnje na nivou citave aplikacije.
 * @author Nikola Markovic
 */
public final class SearchResultPanel extends TitledPanel {

    private static final Color bgColor = new Color(242, 240, 100);
    private final JLabel topResultLabel = new GeneralLabel("", Color.black);

    /**
     * Konstruktor, po pozivu, generise standardan TitledPanel kojem se postavlja BorderLayout na kom se distribuisu
     * UI elementi kojima se takodje dodaju odgovarajuci Listeneri.
     * @author Nikola Markovic
     */
    public SearchResultPanel(){
        super(TextConstants.RESULT_GENERAL_LABEL_TEXT, bgColor, Color.black);

        final StateManager smInstance = StateManager.getInstance();
        final UIManager uimInstance = UIManager.getInstance();

        topResultLabel.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.BOLD, 14));
        uimInstance.setTopResultLabel(this.topResultLabel);
        setTopResultLabelToDefaultState();

        final JPanel rightPanel = new JPanel();
        final JPanel leftPanel = new JPanel();

        rightPanel.setBackground(bgColor);
        leftPanel.setBackground(bgColor);

        rightPanel.setPreferredSize(new Dimension(UIConstants.SEARCH_RESULT_PANEL_RIGHT_SUBPANEL_SIZE, UIConstants.TITLED_PANEL_HEIGHT));
        leftPanel.add(topResultLabel);

        final GeneralButton extraButton = new GeneralButton(TextConstants.EXTRA_BUTTON_TEXT);
        extraButton.addActionListener(ButtonListeners.extraButtonListener);

        rightPanel.add(extraButton);
        extraButton.setVisible(false);
        uimInstance.setExtraButton(extraButton);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        smInstance.addPrimaryInteractiveComponent(extraButton);
    }

    /**
     * Metoda koja postavlja rezultantnu tekstualnu vrijednost na glavni JLabel panela,
     * sto se prikazuje korisniku poslije pritiska odgovarajuceg dugmeta.
     * @author Nikola Markovic
     */
    public void setResult(){
        final UIManager uimInstance = UIManager.getInstance();
        final StateManager smInstance = StateManager.getInstance();

        PathObject topResult;
        boolean buttonState;
        String text;

        if (!smInstance.getCurrentYenResult().isEmpty()) {
            if (smInstance.getCurrentYenResult().size() == 1 && (int)smInstance.getCurrentYenResult().getFirst().getTotalCost() == 0){
                text = TextConstants.RESULT_LABEL_SAME_NODES;
                buttonState = false;
            }
            else {
                topResult = smInstance.getCurrentYenResult().getFirst();
                final String tableName = smInstance.getCriteriaTableName();
                int rawValue = (int) topResult.getTotalCost();
                String displayedValue = "";

                if (tableName.equals(TextConstants.CRITERIA_NODES_DISPLAY_NAME)) {
                    rawValue--;
                    if (rawValue <= 0)
                        rawValue = 0;

                    displayedValue = String.valueOf(rawValue);
                } else if (tableName.equals(TextConstants.CRITERIA_DURATION_DISPLAY_NAME)) {
                    displayedValue = Time.minutesToStringTime(rawValue);
                } else if (tableName.equals(TextConstants.CRITERIA_PRICE_DISPLAY_NAME)) {
                    displayedValue = rawValue + TextConstants.CURRENCY;
                }

                text = tableName + " najoptimalnijeg puta je " + displayedValue + ".";
                buttonState = true;
            }
        }
        else {
            text = TextConstants.RESULT_LABEL_NO_PATHS;
            buttonState = false;
        }

        topResultLabel.setText(text);
        uimInstance.getExtraButton().setVisible(buttonState);
    }

    /**
     * Metoda koja postavlja defaultnu tekstualnu vrijednost na glavni JLabel panel,
     * sto se prikazuje korisniku svaki put po pokretanju aplikacije.
     * @author Nikola Markovic
     */
    private void setTopResultLabelToDefaultState(){
        int[] res = Serializer.getSalesInfo();

        final String text = "Do sada prodano " + res[0] + " karata i zaradjeno ukupno " + res[1] + TextConstants.CURRENCY + ".";

        this.topResultLabel.setText(text);
    }
}
