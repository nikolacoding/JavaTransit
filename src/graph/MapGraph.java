package graph;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public final class MapGraph extends SingleGraph {
    public MapGraph(String name){
        super(name);
    }

    public void setNodes(String[] cityNames, Map<String, Node> map){
        Arrays.stream(cityNames).forEach(cityName -> {
            Node newNode = this.addNode(cityName);
            map.put(cityName, newNode);
        });

        final Random random = new Random();
        double minDistX = 0d;
        double minDistY = 0d;
        for (Node node : this){
            double x = random.nextDouble() * 100 + minDistX;
            minDistX += random.nextDouble() * 100;

            double y = random.nextDouble() * 100 + minDistY;
            minDistY += random.nextDouble() * 100 * (random.nextBoolean() ? 1 : -1);
            node.setAttribute("xyz", x, y, 0);
        }
    }

    @Override
    public Node addNode(String s){
        Node res = super.addNode(s);
        res.setAttribute("ui.style", "fill-color: black; size: 15px;");
        return res;
    }
}
