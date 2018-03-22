package CombinatorialAlgorithms.Lab;

public class Variations {

    private static String[] elements = new String[]{"A", "B", "C", "D", "E"};

    public static void main(String[] args) {
        iterative(3);
    }

    public static void iterative(int slots) {
        int k = slots;
        int[] vector = new int[k];
        while (true) {
            print(vector);
            int index = k - 1;
            while (index >= 0 && vector[index] == elements.length - 1) {
                index--;
            }
            if (index < 0) {
                break;
            }
            vector[index]++;
            for (int i = index + 1; i < vector.length; i++) {
                vector[i] = 0;
            }
        }
    }

    private static void print(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(elements[vector[i]] + " ");
        }
        System.out.println();
    }
}
