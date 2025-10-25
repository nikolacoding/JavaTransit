package ui.primary;

import graph.MapGraph;
import input.InputData;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class MainPanel extends JPanel {
    private final MapGraph graph;
    private final Map<String, Node> nodeMap;
    private final Map<String, Edge> edgeMap;

    public MainPanel(){
        super(new BorderLayout());

        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.MAIN_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel("Grad", Color.white, SwingConstants.CENTER));
        labelPanel.setBackground(new Color(33, 32, 28));
        this.add(labelPanel, BorderLayout.NORTH);

        graph = InputData.getInstance().getMapGraph();
        nodeMap = new HashMap<>();
        edgeMap = new HashMap<>();

        graph.setNodes(InputData.getInstance().getCountryMap().getCityNames(), nodeMap);
        graph.connectAdjacent("price");

        SwingViewer graphViewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        graphViewer.disableAutoLayout();

        View view = graphViewer.addDefaultView(false);
        ViewPanel viewPanel = (ViewPanel)view;

        this.add(viewPanel, BorderLayout.CENTER);
    }

    public Graph getGraph(){
        return this.graph;
    }
}
