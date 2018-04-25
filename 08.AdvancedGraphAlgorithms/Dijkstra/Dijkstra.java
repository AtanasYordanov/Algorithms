package GraphAdvancedAlgorithms1.Dijkstra;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int[] distance = new int[graph.length];
        int[] prev = new int[graph.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        distance[sourceNode] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distance[i]));
        queue.offer(sourceNode);
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            for (int i = 0; i < graph.length; i++) {
                int distanceToChild = graph[vertex][i];
                int totalDistance = distance[vertex] + distanceToChild;
                if (distanceToChild != 0 && distance[i] > totalDistance) {
                    queue.offer(i);
                    prev[i] = vertex;
                    distance[i] = totalDistance;
                }
            }
        }
        if (prev[destinationNode] == -1) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int current = destinationNode;
        path.addFirst(current);
        while (prev[current] != -1) {
            path.addFirst(prev[current]);
            current = prev[current];
        }
        return path;
    }
}
