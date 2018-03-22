package CombinatorialAlgorithms.Lab;

public class Combinations {

    private static int k = 3;
    private static String[] elements = new String[]{"A", "B", "C", "D", "E"};
    private static String[] vector = new String[k];

    public static void main(String[] args) {
        generateWithoutRepetition(0, 0);
    }

    private static void generateWithoutRepetition(int index, int start) {
        if (index >= vector.length) {
            System.out.println(String.join(" ", vector));
            return;
        }
        for (int i = start; i < elements.length; i++) {
            vector[index] = elements[i];
            generateWithoutRepetition(index + 1, i + 1);
        }
    }

    private static void generateWithRepetition(int index, int start) {
        if (index >= vector.length) {
            System.out.println(String.join(" ", vector));
            return;
        }
        for (int i = start; i < elements.length; i++) {
            vector[index] = elements[i];
            generateWithoutRepetition(index + 1, i);
        }
    }
}
