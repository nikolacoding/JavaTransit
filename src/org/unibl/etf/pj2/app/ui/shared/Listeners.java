package org.unibl.etf.pj2.app.ui.shared;

import org.unibl.etf.pj2.app.graph.MapGraph;
import org.unibl.etf.pj2.app.pathfinding.reconstruction.PathReconstructor;
import org.unibl.etf.pj2.app.pathfinding.yen.YenKShortestPaths;
import org.unibl.etf.pj2.app.pathfinding.yen.types.PathObject;
import org.unibl.etf.pj2.app.serialization.Receipt;
import org.unibl.etf.pj2.app.serialization.Serializer;
import org.unibl.etf.pj2.app.state.InputData;
import org.unibl.etf.pj2.app.state.StateManager;
import org.unibl.etf.pj2.app.state.UIManager;
import org.unibl.etf.pj2.app.ui.primary.GeneralComboBox;
import org.unibl.etf.pj2.app.ui.secondary.PathsWindow;
import org.unibl.etf.pj2.app.ui.tertiary.DetailedPathWindow;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;
import org.unibl.etf.pj2.app.util.constants.TextConstants;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja sadrzi gotovo svaki koristen Listener u aplikaciji kategorizovan u unutrasnjim statickim klasama.
 * Listeneri tipicno koriste vec definisane Singletone kao sto su UIManager, InputManager ili StateManager da
 * mijenjaju stanje objekata i izvrsavanja bez direktne reference na date objekte.
 *
 * @author Nikola Markovic
 */
public final class Listeners {

    private static final InputData idInstance = InputData.getInstance();
    private static final UIManager uimInstance = UIManager.getInstance();
    private static final StateManager smInstance = StateManager.getInstance();

    /**
     * Listeneri (tipicno ActionListeneri) za JButton elemente.
     *
     * @author Nikola Markovic
     */
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
            final UIManager uimInstance = UIManager.getInstance();
            final StateManager smInstance = StateManager.getInstance();

            final int price = smInstance.getCurrentReceiptPrice();
            final String from = smInstance.getCurrentReceiptFrom();
            final String to = smInstance.getCurrentReceiptTo();
            final String departureTime = smInstance.getCurrentReceiptDepartureTime();
            final String arrivalTime = smInstance.getCurrentReceiptArrivalTime();
            final int numVehicleChanges = smInstance.getCurrentReceiptNumVehicleChanges();
            final String path = smInstance.getCurrentReceiptPath();

            final Receipt receipt = new Receipt(price, from, to, departureTime, arrivalTime, numVehicleChanges, path);
            Serializer.serializeReceipt(receipt, uimInstance.getTopResultLabel());

            System.out.println("Karta kupljena.");

            uimInstance.getStartComboBox().setEnabled(true);
            uimInstance.getDestinationComboBox().setEnabled(true);
            uimInstance.getOptimizationCriteriaComboBox().setEnabled(true);
            uimInstance.getFindButton().setEnabled(true);
            uimInstance.getExtraButton().setEnabled(true);

            smInstance.closeAllButMainWindow();
        };
    }

    /**
     * Listeneri (tipicno ItemListeneri) za JComboBox elemente.
     *
     * @author Nikola Markovic
     */
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
