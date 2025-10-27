package org.unibl.etf.pj2.app.pathfinding.yen;

import org.graphstream.graph.Graph;
import org.unibl.etf.pj2.app.pathfinding.yen.types.GraphEdge;
import org.unibl.etf.pj2.app.pathfinding.yen.types.PathObject;

import java.util.*;

/**
 * Klasa rukovatelj Jenovim algoritmom za K najkracih putanja izmedju dva zadata cvora usmjerenog grafa.
 * Jenov algoritam koristi Dajkstrin algoritam za najkracu putanju izmedju dva cvora kao podalgoritam
 * kojim otkriva K najkracih putanja.
 * @author Nikola Markovic
 */
public class YenKShortestPaths {
    private final Map<String, List<GraphEdge>> adjacency;
    private final String source;
    private final String target;

    /**
     * @param graph Graf objekat (interfejs koji polimorfno reprezentuje npr. SingleGraph ili MultiGraph tipove)
     * @param source ID cvora polaska
     * @param target ID cvora destinacije
     * @author Nikola Markovic
     */
    public YenKShortestPaths(Graph graph, String source, String target) {
        this.adjacency = buildAdjacencyMap(graph);
        this.source = source;
        this.target = target;
    }

    /**
     * Metoda koja kreira listu susjednosti (u obliku mape) cvorova polaznog GraphStream grafa.
     * @param graph Graf objekat
     * @return <code>HashMap</code> objekat sa Stringovima kao kljucevima i listom <code>GraphEdge</code> objekata kao vrijednostima
     * @author Nikola Markovic
     */
    private Map<String, List<GraphEdge>> buildAdjacencyMap(Graph graph) {
        Map<String, List<GraphEdge>> graphMap = new HashMap<>();

        graph.nodes().forEach(node -> graphMap.put(node.getId(), new ArrayList<>()));

        graph.edges().forEach(edge -> {
            String from = edge.getSourceNode().getId();
            String to = edge.getTargetNode().getId();

            double cost = 1d;
            if (edge.hasAttribute("weight")) {
                Object currentWeight = edge.getAttribute("weight");
                if (currentWeight instanceof Number) {
                    cost = ((Number) currentWeight).doubleValue();
                }
            }

            graphMap.get(from).add(new GraphEdge(to, cost, edge.getId()));
        });

        return graphMap;
    }


    /**
     * Implementacija Dajkstrinog podalgoritma kao driver algoritma za Jenov. Implementacija koristi prioritetni red
     * umjesto nizova kako bi se postigla veca efikasnost za ogromne grafove.
     * @param start Pocetni cvor
     * @param bannedNodes Skup ID-jeva zabranjenih (ranije navedenih kao nevalidnih) cvorova
     * @param bannedEdges Skup ID-jeva zabranjenih (ranije navedenih kao nevalidnih) grana
     * @return <code>PathObject</code> objekat koji reprezentuje datu optimalnu putanju
     * @author Nikola Markovic
     */
    private PathObject dijkstra(String start, Set<String> bannedNodes, Set<String> bannedEdges) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));

        for (String node : adjacency.keySet()) dist.put(node, Double.POSITIVE_INFINITY);
        dist.put(start, 0.0);
        priorityQueue.offer(new AbstractMap.SimpleEntry<>(start, 0.0));

        while (!priorityQueue.isEmpty()) {
            Map.Entry<String, Double> currEntry = priorityQueue.poll();
            String currEntryId = currEntry.getKey();
            double currDist = currEntry.getValue();

            if (currDist > dist.get(currEntryId)) continue;

            if (currEntryId.equals(target)) break;

            if (bannedNodes != null && bannedNodes.contains(currEntryId)) continue;

            for (GraphEdge ge : adjacency.getOrDefault(currEntryId, Collections.emptyList())) {
                if (bannedEdges != null && bannedEdges.contains(ge.getEdgeId())) continue;
                if (bannedNodes != null && bannedNodes.contains(ge.getTo())) continue;

                double newDist = dist.get(currEntryId) + ge.getCost();
                if (newDist < dist.get(ge.getTo())) {
                    dist.put(ge.getTo(), newDist);
                    prev.put(ge.getTo(), currEntryId);
                    priorityQueue.offer(new AbstractMap.SimpleEntry<>(ge.getTo(), newDist));
                }
            }
        }

        if (!dist.containsKey(target) || dist.get(target) == Double.POSITIVE_INFINITY)
            return null;

        LinkedList<String> path = new LinkedList<>();
        for (String at = target; at != null; at = prev.get(at))
            path.addFirst(at);

        return new PathObject(path, dist.get(target));
    }

    /**
     * Glavna metoda Jenovog algoritma ciji rezultat se dalje primjenjuje.
     * @param K Broj najoptimalnijih putanji koje zelimo kao rezultat izvrsavanja algoritma
     * @return Lista od tacno <code>K</code> <code>PathObject</code> objekata koji reprezentuju <code>K</code>
     * najoptimalnijih putanji izmedju dva zadana cvora.
     * @author Nikola Markovic
     */
    public List<PathObject> yen(int K) {
        List<PathObject> kPaths = new ArrayList<>();
        PriorityQueue<PathObject> candidates = new PriorityQueue<>();
        PathObject shortest = dijkstra(source, null, null);

        if (shortest == null) return Collections.emptyList();
        kPaths.add(shortest);

        for (int k = 1; k < K; k++) {
            PathObject lastPath = kPaths.get(k - 1);
            int pathSize = lastPath.getNodes().size();

            for (int i = 0; i < pathSize - 1; i++) {
                String nodeId = lastPath.getNodes().get(i);
                List<String> rootPath = lastPath.getNodes().subList(0, i + 1);

                Set<String> bannedNodes = new HashSet<>();
                Set<String> bannedEdges = new HashSet<>();

                for (PathObject p : kPaths) {
                    if (p.getNodes().size() > i && p.getNodes().subList(0, i + 1).equals(rootPath)) {
                        String from = p.getNodes().get(i);
                        String to = p.getNodes().get(i + 1);
                        for (GraphEdge e : adjacency.getOrDefault(from, Collections.emptyList())) {
                            if (e.getTo().equals(to))
                                bannedEdges.add(e.getEdgeId());
                        }
                    }
                }

                for (String node : rootPath) {
                    if (!node.equals(nodeId)) bannedNodes.add(node);
                }

                PathObject spurPath = dijkstra(nodeId, bannedNodes, bannedEdges);
                if (spurPath != null) {
                    List<String> totalPath = new ArrayList<>(rootPath);
                    totalPath.addAll(spurPath.getNodes().subList(1, spurPath.getNodes().size()));

                    double totalCost = 0.0;
                    for (int j = 0; j < totalPath.size() - 1; j++) {
                        totalCost += findEdgeCost(totalPath.get(j), totalPath.get(j + 1));
                    }
                    candidates.offer(new PathObject(totalPath, totalCost));
                }
            }

            if (candidates.isEmpty()) break;
            PathObject nextPath = candidates.poll();

            boolean duplicate = false;
            for (PathObject po : kPaths) {
                if (po.getNodes().equals(nextPath.getNodes())) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate)
                kPaths.add(nextPath);
            else
                k--;
        }

        return kPaths;
    }

    /**
     * Metoda za brzi pronalazak tezine izmedju dva cvora.
     * @param from ID cvora polaska
     * @param to ID cvora dolaska
     * @return U zavisnosti od uspjesnosti: <ul>
     *     <li><code>[tezina]</code> - veza izmedju <code>from</code> i <code>to</code> postoji</li>
     *     <li><code>Double.POSITIVE_INFINITY</code> - veza izmedju <code>from</code> i <code>to</code> ne postoji (sa odgovarajucim usmjerenjem)</li>
     * </ul>
     * @author Nikola Markovic
     */
    private double findEdgeCost(String from, String to) {
        for (GraphEdge ge : adjacency.getOrDefault(from, Collections.emptyList())) {
            if (ge.getTo().equals(to)) {
                return ge.getCost();
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
