package org.unibl.etf.pj2.app.ui.primary;

import org.unibl.etf.pj2.app.graph.MapGraph;
import org.unibl.etf.pj2.app.state.InputData;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.unibl.etf.pj2.app.ui.shared.GeneralLabel;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;
import org.unibl.etf.pj2.app.util.constants.StyleConstants;
import org.unibl.etf.pj2.app.util.constants.TextConstants;
import org.unibl.etf.pj2.app.util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Glavni panel za prikaz vizuelizacije grafa koristeci elemente GraphStream biblioteke.
 * @author Nikola Markovic
 */
public final class MainPanel extends JPanel {
    private final MapGraph graph;
    private final Map<String, Node> nodeMap;
    private final Map<String, Edge> edgeMap;

    /**
     * Konstruktor, po pozivu, generise JLabel iznad glavnog panela kao i prvobitni prikaz grafa na sredini.
     * @author Nikola Markovic
     */
    public MainPanel(){
        super(new BorderLayout());

        this.setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH_MAX, UIConstants.MAIN_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel(TextConstants.GRAPH_GENERAL_LABEL_TEXT, StyleConstants.GRAPH_GENERAL_LABEL_COLOR, SwingConstants.CENTER));
        labelPanel.setBackground(StyleConstants.GRAPH_GENERAL_LABEL_BACKGROUND_COLOR);
        this.add(labelPanel, BorderLayout.NORTH);

        graph = InputData.getInstance().getMapGraph();
        nodeMap = new HashMap<>();
        edgeMap = new HashMap<>();

        graph.setNodes(InputData.getInstance().getCountryMap().getCityNames(), nodeMap);
        graph.connectAdjacent(GeneralConstants.CRITERIA_PRICE_ID);

        SwingViewer graphViewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        graphViewer.disableAutoLayout();

        View view = graphViewer.addDefaultView(false);
        ViewPanel viewPanel = (ViewPanel)view;

        this.add(viewPanel, BorderLayout.CENTER);
    }
}
