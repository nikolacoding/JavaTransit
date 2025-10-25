package pathfinding;

import graph.MapGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import util.Constants;

import java.util.List;

public final class PathReconstructor {
    public static void undoAllPreviousReconstructions(MapGraph graph){
        List<Edge> allEdges = graph.edges().toList();

        allEdges.forEach(edge -> edge.setAttribute("ui.style", "fill-color: " + Constants.DEFAULT_EDGE_COLOR_CSS + "; size: 1px;"));
    }

    public static void reconstructPath(MapGraph graph, List<String> pathNodeIds, String edgeHighlightColorCss){
        new Thread(() -> {
            for (int i = 0; i < pathNodeIds.size() - 1; i++){
                Edge e = graph.getEdge(pathNodeIds.get(i) + " " + pathNodeIds.get(i + 1) + " 0");
                e.setAttribute("ui.style", "fill-color: " + edgeHighlightColorCss + "; size: 5px; ");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie){
                    System.out.println("Interrupted.");
                }
            }
        }).start();

    }
}
