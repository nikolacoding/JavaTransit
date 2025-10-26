package graph;

import state.InputData;
import input.types.Departure;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import util.DepartureUtility;
import util.constants.GeneralConstants;
import util.constants.StyleConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class MapGraph extends MultiGraph {

    public MapGraph(String name){
        super(name);
        this.setAttribute("ui.stylesheet", StyleConstants.GRAPH_STYLESHEET);
    }

    public void setNodes(String[] cityNames, Map<String, Node> map){
        Arrays.stream(cityNames).forEach(cityName -> {
            Node newNode = this.addNode(cityName);
            newNode.setAttribute("ui.style", StyleConstants.NODE_STYLE_DEFAULT);
            map.put(cityName, newNode);
        });

// (Stari algoritam za raspodjelu cvorova na vizuelnom prikazu)
//
//        final Random random = new Random();
//        double minDistX = 5000d;
//        double minDistY = 5000d;
//        for (Node node : this){
//            double x = random.nextDouble() * 5000 + minDistX;
//            minDistX += random.nextDouble() * 75;
//
//            double y = random.nextDouble() * 5000 + minDistY;
//            minDistY += random.nextDouble() * 100 * (random.nextBoolean() ? 1 : -1);
//            node.setAttribute("xyz", x, y, 0);
//        }

        final double[] x = { 10d };
        final double[] y = { 10d };

        List<Node> nodeList = this.nodes().toList();
        for (int i = 0, j = 0; i < nodeList.size(); i++, j++){
            nodeList.get(i).setAttribute("xyz", x[0], y[0], 0);

            if (j == (int)Math.sqrt(nodeList.size())) {
                y[0] += GeneralConstants.NODE_DISTANCE_Y;
                j = 0;
            }

            x[0] += GeneralConstants.NODE_DISTANCE_X;
        }
    }

    public void connectAdjacent(String weightCriteria){
        List<Departure> departures = InputData.getInstance().getDepartureList();

        departures.forEach(departure -> {
            final String from = DepartureUtility.stationToCity(departure.getFrom());
            final String to = DepartureUtility.stationToCity(departure.getTo());
            final int weight;

            int subId = -1;
            String id;

            do {
                subId++;
                id = from + " " + to + " " + subId;
            } while (this.getEdge(id) != null);

            Edge e = this.addEdge(id, from, to, true);
            e.setAttribute("ui.style", StyleConstants.EDGE_STYLE_DEFAULT);

            switch (weightCriteria){
                case "duration" -> weight = departure.getDuration();
                case "price"    -> weight = departure.getPrice();
                case "vehicle"  -> weight = 1;
                default         -> weight = departure.getDuration();
            }
            e.setAttribute("weight", weight);
        });
    }

    public void clearEdges(){
        var edges = this.edges().toList();
        edges.forEach(this::removeEdge);
    }

    @Override
    public Node addNode(String s){
        Node res = super.addNode(s);
        res.setAttribute("ui.style", StyleConstants.NODE_STYLE_DEFAULT);
        return res;
    }

    public void setSelected(String node, boolean state, String type){
        final Node n = this.getNode(node);
        String style;

        if (n != null) {
            if (state) {
                switch (type) {
                    case "A" -> style = StyleConstants.NODE_STYLE_SELECTED_A;    // selected (polazak)
                    case "B" -> style = StyleConstants.NODE_STYLE_SELECTED_B;    // selected (destinacija)
                    case "C" -> style = StyleConstants.NODE_STYLE_SELECTED_C;    // selected (debug)
                    default -> style = StyleConstants.NODE_STYLE_DEFAULT;        // deselected
                }
            }
            else style = StyleConstants.NODE_STYLE_DEFAULT;

            this.getNode(node).setAttribute("ui.style", style);
        }
    }
}
