package ui.primary;

import pathfinding.yen.types.PathObject;
import state.UIManager;
import state.StateManager;
import ui.shared.GeneralButton;
import ui.shared.GeneralLabel;
import ui.shared.TitledPanel;
import util.Time;
import util.constants.TextConstants;
import util.constants.UIConstants;
import ui.shared.Listeners.ButtonListeners;

import javax.swing.*;
import java.awt.*;

public final class SearchResultPanel extends TitledPanel {

    private static final Color bgColor = new Color(242, 240, 100);
    private final JLabel topResultLabel = new GeneralLabel("", Color.black);

    public SearchResultPanel(){
        super(TextConstants.RESULT_GENERAL_LABEL_TEXT, bgColor, Color.black);

        final StateManager smInstance = StateManager.getInstance();
        final UIManager uimInstance = UIManager.getInstance();

        topResultLabel.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.BOLD, 14));
        uimInstance.setTopResultLabel(this.topResultLabel);

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

    public void setResult(){
        final UIManager uimInstance = UIManager.getInstance();
        final StateManager smInstance = StateManager.getInstance();

        PathObject topResult = null;
        boolean buttonState;
        String text = "";

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
}
