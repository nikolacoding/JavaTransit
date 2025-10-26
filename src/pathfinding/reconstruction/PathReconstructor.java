package pathfinding.reconstruction;

import graph.MapGraph;
import org.graphstream.graph.Edge;
import util.constants.GeneralConstants;
import util.constants.StyleConstants;

import java.util.List;

public final class PathReconstructor {
    public static void undoAllPreviousReconstructions(MapGraph graph){
        List<Edge> allEdges = graph.edges().toList();

        allEdges.forEach(edge -> edge.setAttribute("ui.style", StyleConstants.EDGE_STYLE_DEFAULT));
    }

    public static void reconstructPath(MapGraph graph, List<String> pathNodeIds){
        final int numNodes = pathNodeIds.size();
        new Thread(() -> {
            for (int i = 0; i < numNodes - 1; i++){
                Edge e = graph.getEdge(pathNodeIds.get(i) + " " + pathNodeIds.get(i + 1) + " 0");
                e.setAttribute("ui.style", StyleConstants.EDGE_STYLE_SELECTED);

                try {
                    Thread.sleep(GeneralConstants.PATH_RECONSTRUCTION_TIME_MS / numNodes); // 2 sekunde bez obzira na broj cvorova
                } catch (InterruptedException ie){
                    System.out.println("Interrupted.");
                }
            }
        }).start();

    }
}
