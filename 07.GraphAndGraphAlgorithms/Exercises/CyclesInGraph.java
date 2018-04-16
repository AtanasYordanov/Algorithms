package GraphAndGraphAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInGraph {

    private static Map<String, List<String>> graph = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        readGraph();
        for (String vertex : graph.keySet()) {
            Set<String> visited = new HashSet<>();
            boolean isAcyclic = checkForCycles(visited, vertex, null);
            if (!isAcyclic) {
                System.out.println("Acyclic: No");
                return;
            }
        }
        System.out.println("Acyclic: Yes");
    }

    private static void readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        while (input != null && !input.equals("")) {
            String[] tokens = input.split("[-–]");
            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.putIfAbsent(tokens[1], new ArrayList<>());
            graph.get(tokens[0]).add(tokens[1]);
            graph.get(tokens[1]).add(tokens[0]);
            input = reader.readLine();
        }
    }

    private static boolean checkForCycles(Set<String> visited, String current, String previous) {
        if (visited.contains(current)) {
            return false;
        }
        visited.add(current);
        boolean isAcyclic = true;
        for (String child : graph.get(current)) {
            if (!child.equals(previous)) {
                isAcyclic &= checkForCycles(visited, child, current);
            }
        }
        return isAcyclic;
    }
}

