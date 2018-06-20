package GraphAdvancedAlgorithms1.Kruskal;

public class Edge implements Comparable<Edge> {

    private int startNode;
    private int endNode;
    private int weight;

    public Edge(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public int getStartNode() {
        return this.startNode;
    }

    public int getEndNode() {
        return this.endNode;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() {
        return String.format("(%s %s) -> %s", this.startNode, this.endNode, this.weight);
    }
}
