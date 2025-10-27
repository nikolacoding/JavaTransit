package pathfinding.yen.types;

/**
 * Data-holder klasa koja opisuje jednu usmjerenu granu grafa. Predstavlja laksi nacin za rad sa ovim podacima jer
 * standardne metode unutar GraphStream biblioteke nemaju jednostavan nacin za pristup svim ovim podacima u svakom trenutku.
 * @author Nikola Markovic
 */
public final class GraphEdge {
    private String to;
    private double cost;
    private String edgeId;

    /**
     * @param to ID cvora dolaska
     * @param cost tezina
     * @param edgeId ID grane
     * @author Nikola Markovic
     */
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
