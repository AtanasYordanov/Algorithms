package GraphAndGraphAlgorithms.Exercises;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistanceBetweenVertices {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
        int n = Integer.parseInt(reader.readLine());
        int p = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(":| ");
            int vertex = Integer.parseInt(tokens[0]);
            graph.putIfAbsent(vertex, new ArrayList<>());
            for (int j = 1; j < tokens.length; j++) {
                int edge = Integer.parseInt(tokens[j]);
                graph.get(vertex).add(edge);
            }
        }
        for (int i = 0; i < p; i++) {
            String[] tokens = reader.readLine().split("-");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int distance = findDistance(graph, from, to);
            System.out.printf("{%d, %d} -> %d%n", from, to, distance);
        }
    }

    private static int findDistance(Map<Integer, List<Integer>> graph, int from, int to) {
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(from, 0));
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty() && queue.peekFirst().getKey() != to){
            Pair<Integer, Integer> current = queue.poll();
            if (!visited.contains(current.getKey())){
                for (Integer child : graph.get(current.getKey())) {
                    queue.offer(new Pair<>(child, current.getValue() + 1));
                }
                visited.add(current.getKey());
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().getValue();
    }
}