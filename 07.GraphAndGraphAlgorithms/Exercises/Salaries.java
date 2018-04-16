package GraphAndGraphAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Salaries {

    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readGraph();
        System.out.println(calculateTotalSum());
    }

    private static long calculateTotalSum() {
        long[] salaries = new long[graph.size()];
        long totalSum = 0;
        for (int i = 0; i < graph.size(); i++) {
            totalSum += calculateSalary(salaries, i);
        }
        return totalSum;
    }

    private static long calculateSalary(long[] salaries, Integer member) {
        if (salaries[member] != 0) {
            return salaries[member];
        }
        long salary = 0;
        for (Integer employee : graph.get(member)) {
            salary += calculateSalary(salaries, employee);
        }
        salary = salary == 0 ? 1 : salary;
        salaries[member] = salary;
        return salary;
    }

    private static void readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            char[] employees = reader.readLine().toCharArray();
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (employees[j] == 'Y') {
                    graph.get(i).add(j);
                }
            }
        }
    }
}
