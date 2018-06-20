package ExamPreparation.Exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightning {

    private static List<Integer>[] graph;
    private static int[] vertexDamage;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int verticesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());
        int lightningsCount = Integer.parseInt(reader.readLine());
        Set<Edge> edges = getEdges(reader, edgesCount);
        List<Edge> spanningTree = kruskal(verticesCount, edges);
        buildGraph(verticesCount, spanningTree);
        vertexDamage = new int[verticesCount];
        for (int i = 0; i < lightningsCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            int target = Integer.parseInt(tokens[0]);
            int damage = Integer.parseInt(tokens[1]);
            boolean[] visited = new boolean[verticesCount];
            DFS(target, damage, visited);
        }
        System.out.println(getMaxDamage());
    }

    private static int getMaxDamage() {
        int maxDamage = 0;
        for (int damage : vertexDamage) {
            if (damage > maxDamage) {
                maxDamage = damage;
            }
        }
        return maxDamage;
    }

    private static void DFS(int target, int damage, boolean[] visited) {
        if (visited[target]) {
            return;
        }
        visited[target] = true;
        vertexDamage[target] += damage;
        for (Integer child : graph[target]) {
            DFS(child, damage / 2, visited);
        }
    }

    @SuppressWarnings("unchecked")
    private static void buildGraph(int verticesCount, List<Edge> edges) {
        graph = new ArrayList[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            graph[i] = new ArrayList<>();
        }
        for (Edge edge : edges) {
            int v1 = edge.getFrom();
            int v2 = edge.getTo();
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
    }

    private static Set<Edge> getEdges(BufferedReader reader, int edgesCount) throws IOException {
        Set<Edge> edges = new TreeSet<>();
        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int distance = Integer.parseInt(tokens[2]);
            Edge edge = new Edge(from, to, distance);
            edges.add(edge);
        }
        return edges;
    }

    private static List<Edge> kruskal(int numberOfVertices, Set<Edge> edges) {
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        List<Edge> spanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int startVertexRoot = findRoot(edge.getFrom(), parent);
            int endVertexRoot = findRoot(edge.getTo(), parent);
            if (startVertexRoot != endVertexRoot) {
                spanningTree.add(edge);
                parent[startVertexRoot] = endVertexRoot;
            }
        }
        return spanningTree;
    }

    private static int findRoot(int vertex, int[] parent) {
        int root = vertex;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (vertex != root) {
            int oldParent = parent[vertex];
            parent[vertex] = root;
            vertex = oldParent;
        }
        return root;
    }

    private static class Edge implements Comparable<Edge> {

        private int from;
        private int to;
        private int distance;

        Edge(int startNode, int endNode, int weight) {
            this.from = startNode;
            this.to = endNode;
            this.distance = weight;
        }

        int getFrom() {
            return from;
        }

        int getTo() {
            return to;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}