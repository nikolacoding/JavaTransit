package ui.primary;

import input.StateManager;
import pathfinding.YenKShortestPaths;
import ui.secondary.PathsWindow;
import util.Constants;
import util.Time;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class SearchResultPanel extends TitledPanel {

    private static final Color bgColor = new Color(242, 240, 100);
    private final JLabel topResultLabel = new JLabel();

    public SearchResultPanel(){
        super("Rezultat", bgColor, Color.black);
        topResultLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        StateManager.getInstance().setTopResultLabel(this.topResultLabel);

        final JPanel rightPanel = new JPanel();
        final JPanel leftPanel = new JPanel();

        rightPanel.setBackground(bgColor);
        leftPanel.setBackground(bgColor);

        rightPanel.setPreferredSize(new Dimension(300, Constants.TITLED_PANEL_HEIGHT));
        leftPanel.add(topResultLabel);

        final JButton extraButton = new JButton("Detaljno");
        extraButton.addActionListener(ae -> {
            final List<YenKShortestPaths.PathObject> rawData = StateManager.getInstance().getCurrentYenResult();
            final ArrayList<YenKShortestPaths.PathObject> data = (ArrayList<YenKShortestPaths.PathObject>)rawData;
            new PathsWindow(data);
        });
        extraButton.setFocusable(false);
        rightPanel.add(extraButton);
        extraButton.setVisible(false);
        StateManager.getInstance().setExtraButton(extraButton);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        StateManager.getInstance().addPrimaryInteractiveComponent(extraButton);
    }

    public void setResult(){
        final StateManager smInstance = StateManager.getInstance();
        YenKShortestPaths.PathObject topResult = null;
        boolean buttonState;
        String text = "";

        if (!smInstance.getCurrentYenResult().isEmpty()) {
            if (smInstance.getCurrentYenResult().size() == 1 && (int)smInstance.getCurrentYenResult().getFirst().getTotalCost() == 0){
                text = "Izabrana su dva ista grada.";
                buttonState = false;
            }
            else {
                topResult = smInstance.getCurrentYenResult().getFirst();
                final String tableName = smInstance.getCriteriaTableName();
                int rawValue = (int) topResult.getTotalCost();
                String displayedValue = "";

                if (tableName.equals("Broj presjedanja")) {
                    rawValue--;
                    if (rawValue <= 0)
                        rawValue = 0;

                    displayedValue = String.valueOf(rawValue);
                } else if (tableName.equals("Trajanje")) {
                    displayedValue = Time.minutesToStringTime(rawValue);
                } else if (tableName.equals("Cijena")) {
                    displayedValue = rawValue + "KM";
                }

                text = tableName + " najoptimalnijeg puta je " + displayedValue + ".";
                buttonState = true;
            }
        }
        else {
            text = "Ne postoji put izmedju dva zadata cvora.";
            buttonState = false;
        }

        topResultLabel.setText(text);
        StateManager.getInstance().getExtraButton().setVisible(buttonState);
    }
}
