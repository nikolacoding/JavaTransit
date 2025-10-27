package org.unibl.etf.pj2.app.pathfinding.yen.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Data-holder klasa koja reprezentuje jednu putanju. Sadrzi njene kljucne elemente.
 * @author Nikola Markovic
 */
public final class PathObject implements Comparable<PathObject> {
    private final List<String> nodes;
    private final double totalCost;

    /**
     * @param nodes Lista ID-jeva cvorova putanje
     * @param totalCost ukupna tezina putanje
     * @author Nikola Markovic
     */
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
