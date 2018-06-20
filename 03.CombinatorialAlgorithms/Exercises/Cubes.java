package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Cubes {
    private static String[] cube;
    private static Set<String> used = new HashSet<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cube = reader.readLine().split(" ");
        generatePermutations(0);
        System.out.println(count);
    }

    private static void generatePermutations(int index) {
        if (index == cube.length) {
            String permutation = String.join("", cube);
            if (!used.contains(permutation)) {
                count++;
                addAllRotations();
            }
            return;
        }
        Set<String> swapped = new HashSet<>();
        for (int i = index; i < cube.length; i++) {
            if (!swapped.contains(cube[i])) {
                swap(index, i);
                generatePermutations(index + 1);
                swap(index, i);
                swapped.add(cube[i]);
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = cube[i];
        cube[i] = cube[j];
        cube[j] = temp;
    }

    private static void addAllRotations() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String rotation = String.join("", cube);
                used.add(rotation);
                rotateHorizontal();
            }
            rotateVertical();
        }
        rotateHorizontal();
        rotateVertical();
        for (int i = 0; i < 4; i++) {
            String rotation = String.join("", cube);
            used.add(rotation);
            rotateHorizontal();
        }
        rotateVertical();
        rotateVertical();
        for (int i = 0; i < 4; i++) {
            String rotation = String.join("", cube);
            used.add(rotation);
            rotateHorizontal();
        }
    }

    private static void rotateHorizontal() {
        String[] aux = new String[cube.length];
        aux[0] = cube[10];
        aux[1] = cube[4];
        aux[2] = cube[0];
        aux[3] = cube[9];
        aux[4] = cube[5];
        aux[5] = cube[6];
        aux[6] = cube[1];
        aux[7] = cube[2];
        aux[8] = cube[3];
        aux[9] = cube[11];
        aux[10] = cube[7];
        aux[11] = cube[8];
        cube = aux;
    }

    private static void rotateVertical() {
        String[] aux = new String[cube.length];
        aux[0] = cube[9];
        aux[1] = cube[3];
        aux[2] = cube[8];
        aux[3] = cube[11];
        aux[4] = cube[0];
        aux[5] = cube[1];
        aux[6] = cube[2];
        aux[7] = cube[6];
        aux[8] = cube[7];
        aux[9] = cube[10];
        aux[10] = cube[4];
        aux[11] = cube[5];
        cube = aux;
    }
}