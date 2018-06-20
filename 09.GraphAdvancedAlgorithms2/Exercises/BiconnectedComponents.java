package GraphAdvancedAlgorithms2.Exercises;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BiconnectedComponents {

    private static boolean[] visited;
    private static int[] depth;
    private static int[] lowpoint;
    private static int[] parent;
    private static List<Integer>[] graph;
    private static List<List<Integer>> biconnectedComponents = new ArrayList<>();
    private static Deque<Pair<Integer, Integer>> component = new ArrayDeque<>();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        List<Integer>[] graph = new ArrayList[nodesCount];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            int parent = Integer.parseInt(tokens[0]);
            int child = Integer.parseInt(tokens[1]);
            graph[parent].add(child);
            graph[child].add(parent);
        }
        findArticulationPoints(graph);
        List<List<Integer>> foundComponents = getBiconnectedComponents();
        System.out.println("Number of bi-connected components: " + foundComponents.size());
    }

    private static void findArticulationPoints(List<Integer>[] targetGraph) {
        visited = new boolean[targetGraph.length];
        depth = new int[targetGraph.length];
        lowpoint = new int[targetGraph.length];
        parent = new int[targetGraph.length];
        graph = targetGraph;
        for (int i = 0; i < parent.length; i++) parent[i] = -1;
        findArticulationPointsDFS(0, 0);
    }

    private static void findArticulationPointsDFS(int node, int d) {
        visited[node] = true;
        depth[node] = d;
        lowpoint[node] = d;
        int childCount = 0;
        for (int childNode : graph[node]) {
            if (!visited[childNode]) {
                parent[childNode] = node;
                findArticulationPointsDFS(childNode, d + 1);
                childCount = childCount + 1;
                component.push(new Pair<>(node, childNode));
                if (lowpoint[childNode] >= depth[node]) {
                    if (!component.isEmpty()) {
                        List<Integer> newComponent = new ArrayList<>();
                        Pair<Integer, Integer> edge = component.peek();
                        newComponent.add(edge.getKey());
                        do {
                            edge = component.pop();
                            newComponent.add(edge.getValue());
                        } while (!component.isEmpty() && (edge.getKey() != node
                                || Objects.equals(component.peek().getKey(), edge.getValue())));
                        biconnectedComponents.add(newComponent);
                    }
                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[childNode]);
            } else if (childNode != parent[node]) {
                lowpoint[node] = Math.min(lowpoint[node], depth[childNode]);
            }
        }
    }

    private static List<List<Integer>> getBiconnectedComponents() {
        return biconnectedComponents;
    }
}
