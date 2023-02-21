package Question1;



import java.util.*;

public class CheapestRouteWithTimeConstraint {
    static class Edge {
        int source;
        int destination;
        int time;

        Edge(int source, int destination, int time) {
            this.source = source;
            this.destination = destination;
            this.time = time;
        }
    }

    static int findCheapestRoute(List<Edge> edges, int[] charges, int source, int destination, int timeConstraint) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (Edge edge : edges) {
            if (!graph.containsKey(edge.source)) {
                graph.put(edge.source, new ArrayList<>());
            }
            graph.get(edge.source).add(edge);
        }

        int[] dist = new int[charges.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> charges[a] - charges[b]);
        queue.offer(source);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == destination) {
                return dist[destination];
            }

            if (graph.containsKey(curr)) {
                for (Edge edge : graph.get(curr)) {
                    int next = edge.destination;
                    int newDist = dist[curr] + charges[next] + edge.time;
                    if (newDist <= dist[next] && newDist <= timeConstraint) {
                        dist[next] = newDist;
                        queue.offer(next);
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 5),
                new Edge(0, 3, 2),
                new Edge(1, 2, 5),
                new Edge(3, 4, 5),
                new Edge(4, 5, 6),
                new Edge(2, 5, 5)
        );
        int[] charges = {10, 2, 3, 25, 25, 4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;

        System.out.println(findCheapestRoute(edges, charges, source, destination, timeConstraint));
    }
}
