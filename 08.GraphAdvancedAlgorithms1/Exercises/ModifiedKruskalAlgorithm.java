package GraphAdvancedAlgorithms1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ModifiedKruskalAlgorithm {

    private static List<List<Edge>> graph = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readGraph();
        int[] parents = setParents();
        solve(parents);
    }

    private static void solve(int[] parents) {
        edges.sort(Comparator.comparing(Edge::getCost));
        int totalWeight = 0;
        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.getFirstVertex(), parents);
            int endNodeRoot = findRoot(edge.getSecondVertex(), parents);
            if (startNodeRoot != endNodeRoot) {
                totalWeight += edge.getCost();
                parents[startNodeRoot] = endNodeRoot;
            }
        }
        System.out.printf("Minimum spanning forest weight: %d%n", totalWeight);
    }

    private static int[] setParents() {
        int[] parents = new int[graph.size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        return parents;
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (node != root) {
            int oldParent = parent[node];
            parent[node] = root;
            node = oldParent;
        }
        return root;
    }

    private static void readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().substring(7));
        int edgesCount = Integer.parseInt(reader.readLine().substring(7));
        for (int i = 0; i < nodesCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);
            Edge edge = new Edge(from, to, cost);
            graph.get(from).add(edge);
            graph.get(to).add(edge);
            edges.add(edge);
        }
    }

    private static class Edge {
        private int firstVertex;
        private int secondVertex;
        private int cost;

        Edge(int from, int to, int cost) {
            this.firstVertex = from;
            this.secondVertex = to;
            this.cost = cost;
        }

        int getFirstVertex() {
            return firstVertex;
        }

        int getSecondVertex() {
            return secondVertex;
        }

        int getCost() {
            return cost;
        }
    }
}
