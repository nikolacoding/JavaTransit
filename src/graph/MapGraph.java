package graph;

import input.InputData;
import input.types.Departure;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import pathfinding.DepartureUtility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class MapGraph extends MultiGraph {

    public MapGraph(String name){
        super(name);
    }

    public void setNodes(String[] cityNames, Map<String, Node> map){
        Arrays.stream(cityNames).forEach(cityName -> {
            Node newNode = this.addNode(cityName);
            map.put(cityName, newNode);
        });

        final Random random = new Random();
        double minDistX = 5000d;
        double minDistY = 5000d;
        for (Node node : this){
            double x = random.nextDouble() * 5000 + minDistX;
            minDistX += random.nextDouble() * 75;

            double y = random.nextDouble() * 5000 + minDistY;
            minDistY += random.nextDouble() * 100 * (random.nextBoolean() ? 1 : -1);
            node.setAttribute("xyz", x, y, 0);
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

            switch (weightCriteria){
                case "duration" -> weight = departure.getDuration();
                case "price"    -> weight = departure.getPrice();
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
        res.setAttribute("ui.style", "fill-color: black; size: 10px;");
        return res;
    }

    public void setSelected(String node, boolean state, String type){
        final Node n = this.getNode(node);
        String style;

        if (n != null) {
            if (state) {
                switch (type) {
                    case "A" -> style = "fill-color: red; size: 10px;";     // selected (polazak)
                    case "B" -> style = "fill-color: blue; size: 10px;";    // selected (destinacija)
                    case "C" -> style = "fill-color: yellow; size: 10px;";  // selected (oba/debug)
                    default -> style = "fill-color: black; size: 10px;";   // deselected
                }
            }
            else style = "fill-color: black; size: 10px;";

            this.getNode(node).setAttribute("ui.style", style);
        }
    }
}
