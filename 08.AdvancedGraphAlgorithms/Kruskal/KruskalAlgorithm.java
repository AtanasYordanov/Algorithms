package GraphAdvancedAlgorithms1.Kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        edges.sort(Comparator.naturalOrder());
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        List<Edge> spanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);
            if (startNodeRoot != endNodeRoot) {
                spanningTree.add(edge);
                parent[startNodeRoot] = endNodeRoot;
            }
        }
        return spanningTree;
    }

    public static int findRoot(int node, int[] parent) {
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
}
