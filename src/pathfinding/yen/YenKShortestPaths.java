package pathfinding.yen;

import org.graphstream.graph.Graph;
import pathfinding.yen.types.GraphEdge;
import pathfinding.yen.types.PathObject;

import java.util.*;

public class YenKShortestPaths {
    private final Map<String, List<GraphEdge>> adjacency;
    private final String source;
    private final String target;

    public YenKShortestPaths(Graph graph, String source, String target) {
        this.adjacency = buildAdjacencyMap(graph);
        this.source = source;
        this.target = target;
    }

    private Map<String, List<GraphEdge>> buildAdjacencyMap(Graph graph) {
        Map<String, List<GraphEdge>> graphMap = new HashMap<>();

        graph.nodes().forEach(node -> {
            graphMap.put(node.getId(), new ArrayList<>());
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

            graphMap.get(from).add(new GraphEdge(to, cost, edge.getId()));
        });

        return graphMap;
    }


    private PathObject dijkstra(String start, Set<String> bannedNodes, Set<String> bannedEdges) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));

        for (String node : adjacency.keySet()) dist.put(node, Double.POSITIVE_INFINITY);
        dist.put(start, 0.0);
        priorityQueue.offer(new AbstractMap.SimpleEntry<>(start, 0.0));

        while (!priorityQueue.isEmpty()) {
            Map.Entry<String, Double> entry = priorityQueue.poll();
            String u = entry.getKey();
            double curDist = entry.getValue();

            if (curDist > dist.get(u)) continue;

            if (u.equals(target)) break;

            if (bannedNodes != null && bannedNodes.contains(u)) continue;

            for (GraphEdge e : adjacency.getOrDefault(u, Collections.emptyList())) {
                if (bannedEdges != null && bannedEdges.contains(e.getEdgeId())) continue;
                if (bannedNodes != null && bannedNodes.contains(e.getTo())) continue;

                double newDist = dist.get(u) + e.getCost();
                if (newDist < dist.get(e.getTo())) {
                    dist.put(e.getTo(), newDist);
                    prev.put(e.getTo(), u);
                    priorityQueue.offer(new AbstractMap.SimpleEntry<>(e.getTo(), newDist));
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

    private double findEdgeCost(String from, String to) {
        for (GraphEdge ge : adjacency.getOrDefault(from, Collections.emptyList())) {
            if (ge.getTo().equals(to)) {
                return ge.getCost();
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
