package ProblemSolvingMethodology.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPathInMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        List<Node> shortestPath = aStarGetPath(new Node(0, 0), new Node(n - 1, m - 1), map);
        StringBuilder sb = new StringBuilder();
        long pathLen = 0;
        for (Node node : shortestPath) {
            int len = map[node.getRow()][node.getCol()];
            sb.append(len).append(" ");
            pathLen += len;
        }
        System.out.println("Length: " + pathLen);
        System.out.println("Path: " + sb.toString().trim());
    }

    private static int getH(Node current, Node goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    private static List<Node> aStarGetPath(Node start, Node goal, int[][] map) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Node, Node> nodeParent = new HashMap<>();
        Map<Node, Integer> nodeCost = new HashMap<>();
        nodeParent.put(start, null);
        nodeCost.put(start, 0);
        start.setF(getH(start, goal));
        queue.add(start);
        Node current;
        while (queue.size() > 0) {
            current = queue.poll();
            if (current.equals(goal)) {
                break;
            }
            for (int j = 1; j >= -1; j--) {
                for (int i = 1; i >= -1; i--) {
                    if (Math.abs(i) == Math.abs(j) || current.getRow() + i < 0 || current.getRow() + i >= map.length ||
                            current.getCol() + j < 0 || current.getCol() + j >= map[0].length) continue;

                    Node node = new Node(current.getRow() + i, current.getCol() + j);
                    if (!nodeCost.containsKey(node) || nodeCost.get(node).compareTo(nodeCost.get(current) + map[current.getRow()][current.getCol()]) >= 0) {
                        nodeParent.put(node, current);
                        nodeCost.put(node, nodeCost.get(current) + map[current.getRow()][current.getCol()]);
                        node.setF(nodeCost.get(node) + getH(node, goal));
                        queue.add(node);
                    }
                }
            }
        }
        List<Node> path = new LinkedList<>();
        while (nodeParent.get(goal) != null) {
            path.add(0, goal);
            goal = nodeParent.get(goal);
        }
        if (path.isEmpty()) {
            path.add(null);
            return path;
        }
        path.add(0, start);
        return path;
    }

    private static class Node implements Comparable<Node> {
        private int row;
        private int col;
        private int f;

        Node(int row, int col) {
            this.setRow(row);
            this.setCol(col);
        }

        int getRow() {
            return this.row;
        }

        void setRow(int row) {
            this.row = row;
        }

        int getCol() {
            return this.col;
        }

        void setCol(int col) {
            this.col = col;
        }

        void setF(int f) {
            this.f = f;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }

        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + ((Integer) this.row).hashCode();
            hash = 31 * hash + ((Integer) this.col).hashCode();
            return hash;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col;
        }
    }
}
