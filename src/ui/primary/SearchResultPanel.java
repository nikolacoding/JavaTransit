package ui.primary;

import input.StateManager;
import pathfinding.YenKShortestPaths;
import ui.secondary.PathsWindow;
import util.Constants;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class SearchResultPanel extends TitledPanel {

    private static final Color bgColor = new Color(242, 240, 100);
    private final JLabel topResultLabel = new JLabel();

    public SearchResultPanel(){
        super("Rezultat", bgColor, Color.black);
        topResultLabel.setFont(new Font("Roboto", Font.BOLD, 20));

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
    }

    public void setResult(){
        final StateManager smInstance = StateManager.getInstance();
        YenKShortestPaths.PathObject topResult = smInstance.getCurrentYenResult().getFirst();

        if (topResult != null){
            final String text = smInstance.getCriteriaTableName() + " najoptimalnijeg puta je " + topResult.getTotalCost() + ".";
            topResultLabel.setText(text);
            StateManager.getInstance().getExtraButton().setVisible(true);
        }
    }
}
