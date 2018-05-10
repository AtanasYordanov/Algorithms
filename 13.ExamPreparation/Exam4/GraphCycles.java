package ExamPreparation.Exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GraphCycles {

    private static Set<Integer>[] graph;
    private static Set<Cycle> cycles = new TreeSet<>();
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        initialize(reader, n);
        for (int i = 0; i < n; i++) {
            findCycles(i, -1, -1);
        }
        printCycles();
    }

    private static void printCycles() {
        if (cycles.isEmpty()) {
            System.out.println("No cycles found");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Cycle cycle : cycles) {
                sb.append(cycle).append(System.lineSeparator());
            }
            System.out.print(sb);
        }
    }

    private static void findCycles(int vertex, int parent, int grandParent) {
        if (parent > -1 && grandParent > -1) {
            if (visited[grandParent][parent][vertex]) {
                return;
            }
            visited[grandParent][parent][vertex] = true;
        }
        for (Integer child : graph[vertex]) {
            if (child != vertex) {
                if (child == grandParent) {
                    Cycle cycle = new Cycle(vertex, parent, grandParent);
                    cycles.add(cycle);
                    return;
                }
                findCycles(child, vertex, parent);
            }
        }
    }

    private static void initialize(BufferedReader reader, int n) throws IOException {
        graph = new HashSet[n];
        visited = new boolean[n][n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" -> | ->| ");
            int from = Integer.parseInt(tokens[0]);
            for (int j = 1; j < tokens.length; j++) {
                graph[from].add(Integer.parseInt(tokens[j]));
            }
        }
    }

    private static class Cycle implements Comparable<Cycle> {
        int first;
        int second;
        int third;

        Cycle(int vertex, int parent, int grandParent) {
            this.first = vertex;
            this.second = parent;
            this.third = grandParent;
            this.order();
        }

        private void order() {
            if (this.first < this.second && this.first < this.third) {
                int temp = this.second;
                this.second = this.third;
                this.third = temp;
            } else if (this.second < this.first && this.second < this.third) {
                int temp = this.first;
                this.first = this.second;
                this.second = temp;

            } else {
                int temp = this.first;
                this.first = this.third;
                this.third = temp;
            }
        }

        @Override
        public String toString() {
            return String.format("{%d -> %d -> %d}", this.first, this.second, this.third);
        }

        @Override
        public int compareTo(Cycle o) {
            int comp = this.first - o.first;
            if (comp == 0) {
                comp = this.second - o.second;
                if (comp == 0) {
                    comp = this.third - o.third;
                }
            }
            return comp;
        }
    }
}
