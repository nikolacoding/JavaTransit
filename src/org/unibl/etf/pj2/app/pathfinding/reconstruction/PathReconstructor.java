package org.unibl.etf.pj2.app.pathfinding.reconstruction;

import org.unibl.etf.pj2.app.graph.MapGraph;
import org.graphstream.graph.Edge;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;
import org.unibl.etf.pj2.app.util.constants.StyleConstants;

import java.util.List;

/**
 * Klasa metoda za vizuelnu rekonstrukciju (optimalne) putanje na vizuelnom prikazu grafa.
 * @author Nikola Markovic
 */
public final class PathReconstructor {
    /**
     * Metoda koja uklanja sve prethodne rekonstrukcije, odnosno zadaje default stil
     * svakoj grani grafa.
     * @param graph Graf
     * @author Nikola Markovic
     */
    public static void undoAllPreviousReconstructions(MapGraph graph){
        List<Edge> allEdges = graph.edges().toList();

        allEdges.forEach(edge -> edge.setAttribute("ui.style", StyleConstants.EDGE_STYLE_DEFAULT));
    }

    /**
     * Metoda za rekonstrukciju putanje grafa. Nad svakom granom u zadatoj putanji grafa se visenitno primjenjuje promjena
     * stila svakih x milisekundi gdje je x = <code>GeneralConstants.PATH_RECONSTRUCTION_TIME_MS</code> / (broj cvorova).
     * Potpuna rekonstrukcija putanje traje ukupnih <code>GeneralConstants.PATH_RECONSTRUCTION_TIME_MS</code> milisekundi.
     * @param graph
     * @param pathNodeIds
     * @author Nikola Markovic
     */
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
