package ui.shared;

import graph.MapGraph;
import pathfinding.reconstruction.PathReconstructor;
import pathfinding.yen.YenKShortestPaths;
import pathfinding.yen.types.PathObject;
import state.InputData;
import state.StateManager;
import state.UIManager;
import ui.primary.GeneralComboBox;
import ui.secondary.PathsWindow;
import ui.tertiary.DetailedPathWindow;
import util.constants.GeneralConstants;
import util.constants.TextConstants;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public final class Listeners {

    private static final InputData idInstance = InputData.getInstance();
    private static final UIManager uimInstance = UIManager.getInstance();
    private static final StateManager smInstance = StateManager.getInstance();

    public static final class ButtonListeners {
        public static ActionListener findButtonListener = (ae) -> {
            final MapGraph graph = idInstance.getMapGraph();

            final GeneralComboBox startComboBox = uimInstance.getStartComboBox();
            final GeneralComboBox destComboBox = uimInstance.getDestinationComboBox();
            var yenGenerator = new YenKShortestPaths(graph, (String) startComboBox.getSelectedItem(), (String) destComboBox.getSelectedItem());

            smInstance.setCurrentYenResult(yenGenerator.yen(5));
            uimInstance.getSearchResultPanel().setResult();

            var res = smInstance.getCurrentYenResult();
            if (!res.isEmpty()) {
                var pathNodeIds = res.getFirst().getNodes();
                PathReconstructor.reconstructPath(graph, pathNodeIds);
            }
        };

        public static ActionListener extraButtonListener = (ae) -> {
            final List<PathObject> rawData = smInstance.getCurrentYenResult();
            final ArrayList<PathObject> data = (ArrayList<PathObject>)rawData;
            new PathsWindow(data);
        };

        public static ActionListener buyButtonListener = (ae) -> {
            uimInstance.getExtraButton().setVisible(false);
            new DetailedPathWindow();
        };

        public static ActionListener confirmPurchaseListener = (ae) -> {
            System.out.println("Karta je kupljena i racun je sacuvan u <RACUN_PATH>.");
            StateManager.getInstance().getActiveJFrames().forEach(jF -> {
                jF.setVisible(false);
                jF.dispose();
            });

            final UIManager uimInstance = UIManager.getInstance();

            uimInstance.getStartComboBox().setEnabled(true);
            uimInstance.getDestinationComboBox().setEnabled(true);
            uimInstance.getOptimizationCriteriaComboBox().setEnabled(true);
            uimInstance.getFindButton().setEnabled(true);
            uimInstance.getExtraButton().setEnabled(true);
        };
    }

    public static final class ComboBoxListeners {
        public static ItemListener optimizationCriteriaComboBoxListener = (ie) -> {
            final MapGraph graph = idInstance.getMapGraph();

            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String)ie.getItem();

                switch (selectedItem) {
                    case TextConstants.OPTIMIZATION_CRITERIA_COMBO_BOX_BY_PRICE -> {
                        graph.clearEdges();
                        graph.connectAdjacent(GeneralConstants.CRITERIA_PRICE_ID);
                        smInstance.setCriteriaTableName(TextConstants.CRITERIA_PRICE_DISPLAY_NAME);
                    }
                    case TextConstants.OPTIMIZATION_CRITERIA_COMBO_BOX_BY_DURATION -> {
                        graph.clearEdges();
                        graph.connectAdjacent(GeneralConstants.CRITERIA_DURATION_ID);
                        smInstance.setCriteriaTableName(TextConstants.CRITERIA_DURATION_DISPLAY_NAME);
                    }
                    case TextConstants.OPTIMIZATION_CRITERIA_COMBO_BOX_BY_NODE_COUNT -> {
                        graph.clearEdges();
                        graph.connectAdjacent(GeneralConstants.CRITERIA_NODES_ID);
                        smInstance.setCriteriaTableName(TextConstants.CRITERIA_NODES_DISPLAY_NAME);
                    }
                }
            }
        };
    }
}
