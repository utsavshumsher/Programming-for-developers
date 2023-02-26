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
        // Create a map of the graph where each node is mapped to a list of its edges
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (Edge edge : edges) {
            if (!graph.containsKey(edge.source)) {
                graph.put(edge.source, new ArrayList<>());
            }
            graph.get(edge.source).add(edge);
        }

        // Initialize the distance array with infinity
        int[] dist = new int[charges.length];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance from the source to itself is 0
        dist[source] = 0;

        // Initialize a priority queue with the source node, sorted by the charges
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> charges[a] - charges[b]);
        queue.offer(source);

        // Loop until the queue is empty or the destination has been reached
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == destination) {
                return dist[destination];
            }

            // Iterate through the edges of the current node
            if (graph.containsKey(curr)) {
                for (Edge edge : graph.get(curr)) {
                    int next = edge.destination;
                    int newDist = dist[curr] + charges[next] + edge.time;
                    // Check if the new distance is better than the current distance and is within the time constraint
                    if (newDist <= dist[next] && newDist <= timeConstraint) {
                        dist[next] = newDist;
                        queue.offer(next);
                    }
                }
            }
        }

        // If destination is not reached, return -1
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

        int cheapestRoute = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("The cheapest route is $" + cheapestRoute);
    }
}
