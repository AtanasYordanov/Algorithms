package GraphAdvancedAlgorithms1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MostReliablePath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().split(" ")[1]);
        String[] tokens = reader.readLine().split(" ");
        int start = Integer.parseInt(tokens[1]);
        int end = Integer.parseInt(tokens[3]);
        int edgesCount = Integer.parseInt(reader.readLine().split(" ")[1]);
        int[][] graph = new int[n][n];
        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int v1 = Integer.parseInt(edge[0]);
            int v2 = Integer.parseInt(edge[1]);
            int percentage = Integer.parseInt(edge[2]);
            graph[v1][v2] = -1 * percentage;
            graph[v2][v1] = (-1) * percentage;
        }
        List<Integer> shortestPath = dijkstraAlgorithm(graph, start, end);
        if (shortestPath == null) {
            throw new IllegalArgumentException();
        }
        double reliability = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(shortestPath.get(0)).append(" -> ");
        int previousCity = start;
        for (int i = 1; i < shortestPath.size(); i++) {
            int city = shortestPath.get(i);
            reliability *= (-1 * graph[previousCity][city]) * 0.01;
            sb.append(city).append(" -> ");
            previousCity = city;
        }
        sb.setLength(sb.length() - 4);
        System.out.printf("Most reliable path reliability: %.2f%%%n", reliability * 100);
        System.out.println(sb.toString());
    }

    private static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        List<Integer> shortestPath = new ArrayList<>();
        int[] distances = new int[graph.length];
        Integer[] prev = new Integer[graph.length];
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));
        for (int i = 0; i < distances.length; i++) {
            if (i == sourceNode) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }
        queue.add(sourceNode);
        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;
            if (distances[minNode] == Integer.MAX_VALUE) {
                break;
            }
            for (int i = 0; i < graph.length; i++) {
                if (graph[minNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDist = distances[minNode] + graph[minNode][i];
                    distances[i] = Math.min(distances[i], newDist);

                    if (newDist == distances[i]) {
                        prev[i] = minNode;
                        queue.remove(i);
                        queue.add(i);
                    }
                }
            }
        }
        if (distances[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }
        while (prev[destinationNode] != null) {
            shortestPath.add(0, destinationNode);
            destinationNode = prev[destinationNode];
        }
        shortestPath.add(0, sourceNode);
        return shortestPath;
    }
}