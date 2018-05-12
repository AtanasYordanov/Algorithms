package ExamPreparation.Exam6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BlackMessup {

    private static Map<String, Atom> atomsByName = new HashMap<>();
    private static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int atoms = Integer.parseInt(reader.readLine());
        int connections = Integer.parseInt(reader.readLine());
        int maxDecay = 0;
        for (int i = 0; i < atoms; i++) {
            String[] tokens = reader.readLine().split(" ");
            String name = tokens[0];
            int mass = Integer.parseInt(tokens[1]);
            int decay = Integer.parseInt(tokens[2]);
            if (decay > maxDecay) {
                maxDecay = decay;
            }
            Atom atom = new Atom(name, mass, decay);
            atomsByName.putIfAbsent(name, atom);
            graph.put(name, new ArrayList<>());
        }
        for (int i = 0; i < connections; i++) {
            String[] tokens = reader.readLine().split(" ");
            String atom1 = tokens[0];
            String atom2 = tokens[1];
            graph.get(atom1).add(atom2);
            graph.get(atom2).add(atom1);
        }
        List<Set<Atom>> components = findConnectedComponents();

        System.out.println(getMaxMass(components));
    }

    private static List<Set<Atom>> findConnectedComponents() {
        List<Set<Atom>> components = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String atom : graph.keySet()) {
            if (!visited.contains(atom)) {
                Set<Atom> component = new TreeSet<>();
                DFS(atom, component, visited);
                components.add(component);
            }
        }
        return components;
    }

    private static void DFS(String atom, Set<Atom> component, Set<String> visited) {
        visited.add(atom);
        component.add(atomsByName.get(atom));
        for (String child : graph.get(atom)) {
            if (!visited.contains(child)) {
                DFS(child, component, visited);
            }
        }
    }

    private static long getMaxMass(List<Set<Atom>> components) {
        long maxMass = 0;
        for (Set<Atom> set : components) {
            int mass = 0;
            int maxExpiration = 1;
            List<Atom> taken = new ArrayList<>();
            for (Atom atom : set) {
                if (atom.getDecay() > maxExpiration) {
                    taken.add(atom);
                    maxExpiration = atom.getDecay();
                    mass += atom.getMass();
                } else if (maxExpiration > taken.size()) {
                    taken.add(atom);
                    mass += atom.getMass();
                }
            }
            if (mass > maxMass){
                maxMass = mass;
            }
        }
        return maxMass;
    }

    private static class Atom implements Comparable<Atom> {
        private String name;
        private int mass;
        private int decay;

        Atom(String name, int mass, int decay) {
            this.name = name;
            this.mass = mass;
            this.decay = decay;
        }

        public int getMass() {
            return mass;
        }

        public int getDecay() {
            return decay;
        }

        @Override
        public int compareTo(Atom o) {
            int comp = o.mass - this.mass;
            if (comp == 0) {
                comp = this.decay - o.decay;
                if (comp == 0) {
                    comp = this.name.compareTo(o.name);
                }
            }
            return comp;
        }
    }
}
