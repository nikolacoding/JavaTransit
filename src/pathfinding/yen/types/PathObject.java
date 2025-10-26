package pathfinding.yen.types;

import java.util.ArrayList;
import java.util.List;

public final class PathObject implements Comparable<PathObject> {
    private final List<String> nodes;
    private final double totalCost;

    public PathObject(List<String> nodes, double totalCost) {
        this.nodes = new ArrayList<>(nodes);
        this.totalCost = totalCost;
    }

    public List<String> getNodes(){
        return nodes;
    }

    public double getTotalCost(){
        return this.totalCost;
    }

    @Override
    public int compareTo(PathObject other) {
        return Double.compare(this.totalCost, other.totalCost);
    }
}
