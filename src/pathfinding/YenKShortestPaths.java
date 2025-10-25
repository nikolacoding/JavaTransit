package pathfinding;

import org.graphstream.graph.Graph;

import java.util.*;

public class YenKShortestPaths {

    static class GraphEdge {
        String to;
        double cost;
        String edgeId;

        GraphEdge(String to, double cost, String edgeId) {
            this.to = to;
            this.cost = cost;
            this.edgeId = edgeId;
        }
    }

    public static class PathObject implements Comparable<PathObject> {
        private final List<String> nodes;
        private final double totalCost;

        PathObject(List<String> nodes, double totalCost) {
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

    private final Map<String, List<GraphEdge>> adjacency;
    private final String source;
    private final String target;

    public YenKShortestPaths(Graph graph, String source, String target) {
        this.adjacency = buildAdjacencyMap(graph);
        this.source = source;
        this.target = target;
    }

    private Map<String, List<GraphEdge>> buildAdjacencyMap(Graph graph) {
        Map<String, List<GraphEdge>> map = new HashMap<>();

        graph.nodes().forEach(node -> {
            map.put(node.getId(), new ArrayList<>());
        });

        graph.edges().forEach(edge -> {
            String from = edge.getSourceNode().getId();
            String to = edge.getTargetNode().getId();

            double cost = 1.0;
            if (edge.hasAttribute("weight")) {
                Object w = edge.getAttribute("weight");
                if (w instanceof Number) {
                    cost = ((Number) w).doubleValue();
                }
            }

            map.get(from).add(new GraphEdge(to, cost, edge.getId()));
        });

        return map;
    }


    private PathObject dijkstra(String start, Set<String> bannedNodes, Set<String> bannedEdges) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));

        for (String node : adjacency.keySet()) dist.put(node, Double.POSITIVE_INFINITY);
        dist.put(start, 0.0);
        pq.offer(new AbstractMap.SimpleEntry<>(start, 0.0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Double> entry = pq.poll();
            String u = entry.getKey();
            double curDist = entry.getValue();
            if (curDist > dist.get(u)) continue;
            if (u.equals(target)) break;

            if (bannedNodes != null && bannedNodes.contains(u)) continue;

            for (GraphEdge e : adjacency.getOrDefault(u, Collections.emptyList())) {
                if (bannedEdges != null && bannedEdges.contains(e.edgeId)) continue;
                if (bannedNodes != null && bannedNodes.contains(e.to)) continue;

                double newDist = dist.get(u) + e.cost;
                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    prev.put(e.to, u);
                    pq.offer(new AbstractMap.SimpleEntry<>(e.to, newDist));
                }
            }
        }

        if (!dist.containsKey(target) || dist.get(target) == Double.POSITIVE_INFINITY)
            return null;

        LinkedList<String> path = new LinkedList<>();
        for (String at = target; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        return new PathObject(path, dist.get(target));
    }

    public List<PathObject> yen(int K) {
        List<PathObject> kPaths = new ArrayList<>();
        PriorityQueue<PathObject> candidates = new PriorityQueue<>();
        PathObject shortest = dijkstra(source, null, null);

        if (shortest == null) return Collections.emptyList();
        kPaths.add(shortest);

        for (int k = 1; k < K; k++) {
            PathObject lastPath = kPaths.get(k - 1);
            int pathSize = lastPath.nodes.size();

            for (int i = 0; i < pathSize - 1; i++) {
                String spurNode = lastPath.nodes.get(i);
                List<String> rootPath = lastPath.nodes.subList(0, i + 1);

                Set<String> bannedNodes = new HashSet<>();
                Set<String> bannedEdges = new HashSet<>();

                for (PathObject p : kPaths) {
                    if (p.nodes.size() > i && p.nodes.subList(0, i + 1).equals(rootPath)) {
                        String from = p.nodes.get(i);
                        String to = p.nodes.get(i + 1);
                        for (GraphEdge e : adjacency.getOrDefault(from, Collections.emptyList())) {
                            if (e.to.equals(to))
                                bannedEdges.add(e.edgeId);
                        }
                    }
                }

                for (String node : rootPath) {
                    if (!node.equals(spurNode)) bannedNodes.add(node);
                }

                PathObject spurPath = dijkstra(spurNode, bannedNodes, bannedEdges);
                if (spurPath != null) {
                    List<String> totalPath = new ArrayList<>(rootPath);
                    totalPath.addAll(spurPath.nodes.subList(1, spurPath.nodes.size()));

                    double totalCost = 0.0;
                    for (int j = 0; j < totalPath.size() - 1; j++) {
                        totalCost += findEdgeCost(totalPath.get(j), totalPath.get(j + 1));
                    }
                    candidates.offer(new PathObject(totalPath, totalCost));
                }
            }

            if (candidates.isEmpty()) break;
            PathObject nextPath = candidates.poll();

            // Avoid duplicates
            boolean duplicate = false;
            for (PathObject p : kPaths) {
                if (p.nodes.equals(nextPath.nodes)) {
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

    private double findEdgeCost(String from, String to) {
        for (GraphEdge e : adjacency.getOrDefault(from, Collections.emptyList())) {
            if (e.to.equals(to)) {
                return e.cost;
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
