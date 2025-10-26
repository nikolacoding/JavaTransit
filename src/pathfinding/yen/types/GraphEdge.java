package pathfinding.yen.types;

public final class GraphEdge {
    private String to;
    private double cost;
    private String edgeId;

    public GraphEdge(String to, double cost, String edgeId) {
        this.to = to;
        this.cost = cost;
        this.edgeId = edgeId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId;
    }
}
