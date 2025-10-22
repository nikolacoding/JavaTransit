package ui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import util.Constants;

import javax.swing.*;
import java.awt.*;

public final class MainPanel extends JPanel {
    private final Graph graph;

    public MainPanel(){
        super(new BorderLayout());

        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.MAIN_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel("Grad", Color.black, SwingConstants.CENTER));
        labelPanel.setBackground(Color.white);
        this.add(labelPanel, BorderLayout.NORTH);

        graph = new SingleGraph("MainGraph");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CD", "C", "D");

        SwingViewer graphViewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        graphViewer.enableAutoLayout();

        View view = graphViewer.addDefaultView(false);
        ViewPanel viewPanel = (ViewPanel)view;

        this.add(viewPanel, BorderLayout.CENTER);
    }

    public Graph getGraph(){
        return this.graph;
    }
}
