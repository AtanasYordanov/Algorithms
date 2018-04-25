package GraphAdvancedAlgorithms2.Lab;

import java.util.ArrayDeque;
import java.util.Deque;

public class EdmondsKarp {

    private static int[][] graph;
    private static int[] parent;

    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;
        parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) parent[i] = -1;
        int maxFlow = 0;
        int start = 0;
        int end = graph.length - 1;
        while (BFS(start, end)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = end;
            while (currentNode != start) {
                int prevNode = parent[currentNode];
                int currentFlow = graph[prevNode][currentNode];
                if (currentFlow > 0 && currentFlow < pathFlow) {
                    pathFlow = currentFlow;
                }
                currentNode = prevNode;
            }
            maxFlow += pathFlow;
            currentNode = end;
            while (currentNode != start) {
                int prevNode = parent[currentNode];
                graph[prevNode][currentNode] -= pathFlow;
                graph[currentNode][prevNode] += pathFlow;
                currentNode = prevNode;
            }
        }
        return maxFlow;
    }

    private static boolean BFS(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    parent[child] = node;
                    visited[child] = true;
                }
            }
        }
        return visited[end];
    }
}
